package com.asugar.messageforlove.controller;

import com.asugar.messageforlove.entity.UserRemote;
import com.asugar.messageforlove.result.Result;
import com.asugar.messageforlove.service.UserRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message/user/remote")
@Api(tags = "用户-商品模块")
public class UserRemoteController {

    @Resource
    private UserRemoteService userRemoteService;

    @GetMapping("/add")
    @ApiOperation("(购买)用户-商品")
    public Result<?> add(@RequestBody UserRemote userRemote) {
        return Result.success(userRemoteService.insert(userRemote));
    }

    @GetMapping("/info")
    @ApiOperation("用户-商品详情")
    public Result<?> detail(@RequestParam(value = "id") Long id) {
        return Result.success(userRemoteService.selectByPrimaryKey(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation("用户-商品删除")
    public Result<?> deleteUserRemote(@RequestParam(value = "id") Long id) {
        return Result.success(userRemoteService.deleteByPrimaryKey(id));
    }

    @PostMapping("/update")
    @ApiOperation("用户-商品修改")
    public Result<?> updateUserRemote(@RequestBody UserRemote remote) {
        return Result.success(userRemoteService.updateByPrimaryKey(remote));
    }

    @GetMapping("/page")
    @ApiOperation("获取用户-商品列表")
    public Result<?> getUserRemotePage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @RequestParam(value = "remoteId", required = false) Long remoteId)
    {
        return Result.success(userRemoteService.getUserRemotePage(page, size, userId,remoteId));
    }

    @PostMapping("/renew")
    @ApiOperation("用户-商品-续费")
    public Result<?> renew(@RequestBody UserRemote remote) {
        return Result.success(userRemoteService.renew(remote));
    }
}
