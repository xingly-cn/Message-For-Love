package com.asugar.messageforlove.controller;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: 方糖
 * @Date: 2022/8/10 11:30 PM
 */
@RestController
@RequestMapping("/message/remote")
@Api(tags = "商品模块")
@Slf4j
public class RemoteController {

    @Resource
    private RemoteMapper remoteMapper;

    @GetMapping("go")
    @ApiOperation("修改价格")
    public R editPrice(String price) {
        Remote remote = new Remote();
        remote.setPrice(price);
        remote.setId(1L);
        int i = remoteMapper.updateById(remote);
        return R.ok().data("statsu", i);
    }

}
