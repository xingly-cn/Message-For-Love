package com.asugar.messageforlove.service;

import com.asugar.messageforlove.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
public interface UserService extends IService<User> {

    int add(String uid);
}
