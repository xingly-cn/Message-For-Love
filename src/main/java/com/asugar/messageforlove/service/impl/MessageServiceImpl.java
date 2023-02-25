package com.asugar.messageforlove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.asugar.messageforlove.entity.Message;
import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.entity.vo.MsgVo;
import com.asugar.messageforlove.exception.ServiceException;
import com.asugar.messageforlove.mapper.MessageMapper;
import com.asugar.messageforlove.service.MessageService;
import com.asugar.messageforlove.service.UserService;
import com.asugar.messageforlove.utils.PhoneSendUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(MsgVo msgVo, HttpServletRequest request) {
        String uid = request.getHeader("uid");
        // 未登录直接退出
        if (uid == null || uid.isEmpty()) {
            return 0;
        }

        // 获取用户信息
        User user = userService.getOne(new QueryWrapper<User>().eq("uid", uid));

        // 复制对象
        Message message = new Message();
        BeanUtil.copyProperties(msgVo, message);
        message.setUid(uid);

        // 发送短信
        PhoneSendUtils.send(msgVo.getContent() + "-来自微信小程序[风的一封来信]");
        PhoneSendUtils.send(msgVo.getPhone());
        PhoneSendUtils.send("——————————————");

        // 立刻发送
        if (msgVo.getSendtime() == null || msgVo.getSendtime().equals("")) {
            message.setStatus(true);
            message.setSendTime(new Date());
            int insert = baseMapper.insert(message);
            if (insert != 1) {
                return 0;
            }
            return 1;
        }

        // 定时发送
        message.setStatus(false);
        //发送次数减
        if (user.getAmount() < 0) {
            throw new ServiceException(100, "次数不足，请及时进行充值");
        }
        user.setAmount(user.getAmount() - 1);
        userService.updateById(user);
        return baseMapper.insert(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int withdraw(String id, HttpServletRequest request) {
        String uid = request.getHeader("uid");
        // 未登录直接退出
        if (uid == null || uid.isEmpty()) {
            return 0;
        }
        Message message = baseMapper.selectById(id);
        String u = message.getUid();

        // 判断是否是自己的消息
        if (!u.equals(uid)) {
            return 0;
        }

        // 判断是否已经发送
        if (message.getStatus()) {
            return 0;
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public IPage<Message> getMessagePage(HttpServletRequest request, String flag, long cur, long size) {
        String uid = request.getHeader("uid");
        QueryWrapper<Message> wrapper = new QueryWrapper<Message>().eq("uid", uid)
                .orderByDesc("create_time");
        if (flag.equals("1")) {
            wrapper.eq("status", 0);
        } else if (flag.equals("2")) {
            wrapper.eq("status", 1);
        }
        return messageMapper.selectPage(new Page<>(cur, size), wrapper);
    }
}
