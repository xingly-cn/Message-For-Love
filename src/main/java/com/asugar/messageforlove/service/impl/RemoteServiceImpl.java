package com.asugar.messageforlove.service.impl;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.service.RemoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 19:31
 */
@Service
public class RemoteServiceImpl extends ServiceImpl<RemoteMapper, Remote> implements RemoteService {

    private static Logger logger = LoggerFactory.getLogger(RemoteServiceImpl.class);



}
