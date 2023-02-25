package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.exception.ServiceException;
import com.asugar.messageforlove.mapper.UserMapper;
import com.asugar.messageforlove.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public int add(String uid) {
        User oo = baseMapper.selectOne(new QueryWrapper<User>().eq("uid", uid));
        if (oo != null) {
            return 0;
        }
        User user = new User();
        user.setUid(uid);
        return baseMapper.insert(user);
    }
}
