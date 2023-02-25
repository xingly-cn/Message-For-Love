package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.service.RemoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 19:31
 */
@Service
public class RemoteServiceImpl extends ServiceImpl<RemoteMapper, Remote> implements RemoteService {

    private static Logger logger = LoggerFactory.getLogger(RemoteServiceImpl.class);

    @Resource
    private RemoteMapper remoteMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return remoteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Remote record) {
        return remoteMapper.insert(record);
    }

    @Override
    public int insertSelective(Remote record) {
        return remoteMapper.insertSelective(record);
    }

    @Override
    public Remote selectByPrimaryKey(Long id) {
        return remoteMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Remote record) {
        return remoteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Remote record) {
        return remoteMapper.updateByPrimaryKey(record);
    }

}
