package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.exception.ServiceException;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.service.RemoteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = ServiceException.class)
    public Boolean deleteByPrimaryKey(Long id) {
        return remoteMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Boolean insert(Remote record) {
        return remoteMapper.insert(record) > 0;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public int insertSelective(Remote record) {
        return remoteMapper.insertSelective(record);
    }

    @Override
    public Remote selectByPrimaryKey(Long id) {
        return remoteMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public int updateByPrimaryKeySelective(Remote record) {
        return remoteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Boolean updateByPrimaryKey(Remote record) {
        return remoteMapper.updateByPrimaryKey(record) > 0;
    }

    @Override
    public IPage<Remote> getRemotePage(Integer page, Integer size, String name) {
        return remoteMapper.selectPage(new Page<>(page, size), new QueryWrapper<Remote>()
                .like(StringUtils.isNotEmpty(name),"name", name));
    }

}
