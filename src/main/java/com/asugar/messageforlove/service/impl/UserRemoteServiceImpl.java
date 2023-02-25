package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.entity.UserRemote;
import com.asugar.messageforlove.exception.ServiceException;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.mapper.UserMapper;
import com.asugar.messageforlove.mapper.UserRemoteMapper;
import com.asugar.messageforlove.service.UserRemoteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 22:00
 */
@Service
public class UserRemoteServiceImpl implements UserRemoteService {

    @Resource
    private UserRemoteMapper userRemoteMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RemoteMapper remoteMapper;


    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return userRemoteMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean insert(UserRemote record) {
        if (record.getUserId()==null){
            throw new ServiceException(100,"购买用户不可以为空");
        }
        userRemoteMapper.insert(record);
        return updateUserMessageAmount(record);
    }

    @Override
    public Boolean insertSelective(UserRemote record) {
        return userRemoteMapper.insertSelective(record) > 0;
    }

    @Override
    public UserRemote selectByPrimaryKey(Long id) {
        return userRemoteMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateByPrimaryKeySelective(UserRemote record) {
        return userRemoteMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public Boolean updateByPrimaryKey(UserRemote record) {
        return userRemoteMapper.updateByPrimaryKey(record) > 0;
    }

    @Override
    public IPage<UserRemote> getUserRemotePage(Integer page, Integer size, Long userId, Long remoteId) {
        return userRemoteMapper.selectPage(new Page<>(page, size),
                new QueryWrapper<UserRemote>()
                        .eq("user_id", userId)
                        .eq("remote_id", remoteId));
    }

    @Override
    public Boolean renew(UserRemote userRemote) {
        if (userRemote.getUserId()==null){
            throw new ServiceException(100,"续费用户不可以为空");
        }
        userRemoteMapper.renew(userRemote);
        return updateUserMessageAmount(userRemote);
    }

    @Override
    public List<UserRemote> getUserRemoteList(HttpServletRequest request) {
        String uid = request.getHeader("uid");
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("uid", uid));
        return userRemoteMapper.selectList(new QueryWrapper<UserRemote>().eq("user_id", user.getId()));
    }

    private Boolean updateUserMessageAmount(UserRemote userRemote){
        //数量相加 被使用
        User user = userMapper.selectById(userRemote.getUserId());
        Remote remote = remoteMapper.selectById(userRemote.getRemoteId());
        user.setAmount(user.getAmount()+remote.getAmount());
        return userMapper.updateById(user)>0;
    }
}
