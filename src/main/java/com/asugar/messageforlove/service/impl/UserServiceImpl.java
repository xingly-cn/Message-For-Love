package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.mapper.UserMapper;
import com.asugar.messageforlove.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
