package com.asugar.messageforlove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.asugar.messageforlove.entity.Message;
import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.entity.vo.MsgVo;
import com.asugar.messageforlove.mapper.MessageMapper;
import com.asugar.messageforlove.service.MessageService;
import com.asugar.messageforlove.service.UserService;
import com.asugar.messageforlove.utils.PhoneSendUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private final UserService userService;

    public MessageServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
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
            message.setSendtime(new Date());
            int insert = baseMapper.insert(message);
            if (insert != 1) {
                return 0;
            }
            return 1;
        }

        // 定时发送
        message.setStatus(false);
        int insert = baseMapper.insert(message);
        return insert;
    }

    @Override
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
        if (message.getStatus() == true) {
            return 0;
        }

        int delete = baseMapper.deleteById(id);
        return delete;
    }
}