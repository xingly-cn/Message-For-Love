package com.asugar.messageforlove.service;

import com.asugar.messageforlove.entity.UserRemote;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * @Author : 镜像
 * @create 2023/2/25 22:00
 */
public interface UserRemoteService {

    Boolean deleteByPrimaryKey(Long id);

    Boolean insert(UserRemote record);

    Boolean insertSelective(UserRemote record);

    UserRemote selectByPrimaryKey(Long id);

    Boolean updateByPrimaryKeySelective(UserRemote record);

    Boolean updateByPrimaryKey(UserRemote record);

    IPage<UserRemote> getUserRemotePage(Integer page, Integer size, Long userId, Long remoteId);

    Boolean renew(UserRemote remote);

    List<UserRemote> getUserRemoteList(HttpServletRequest request);
}
