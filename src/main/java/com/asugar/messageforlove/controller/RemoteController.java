package com.asugar.messageforlove.controller;

import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.result.Result;
import com.asugar.messageforlove.service.RemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private RemoteService remoteService;

    @GetMapping("go")
    @ApiOperation("修改价格")
    public Result<?> editPrice(String price) {
        Remote remote = new Remote();
        remote.setPrice(price);
        remote.setId(1L);
        return Result.success(remoteService.updateByPrimaryKey(remote));
    }

    @GetMapping("/info")
    @ApiOperation("商品详情")
    public Result<?> detail(@RequestParam(value = "id")Long id) {
        return Result.success(remoteService.selectByPrimaryKey(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除商品")
    public Result<?> deleteRemote(@RequestParam(value = "id")Long id)
    {
        return Result.success(remoteService.deleteByPrimaryKey(id));
    }

    @PostMapping("/update")
    @ApiOperation("修改商品")
    public Result<?> updateRemote(@RequestBody Remote remote)
    {
        return Result.success(remoteService.updateByPrimaryKey(remote));
    }

    @GetMapping("/list")
    @ApiOperation("获取商品列表")
    public Result<?> getRemotePage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size,
                                   @RequestParam(value = "name",required = false)String name)
    {
        return Result.success(remoteService.getRemotePage(page, size,name));
    }
}
