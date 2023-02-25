package com.asugar.messageforlove.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.result.Result;
import com.asugar.messageforlove.service.MessageService;
import com.asugar.messageforlove.service.UserService;
import com.asugar.messageforlove.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 肖念昕
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/message/user")
@Api(tags = "用户模块")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("add")
    @ApiOperation("添加用户")
    public Result<?> add(HttpServletRequest request) {
        String uid = request.getHeader("uid");
        int f = userService.add(uid);
        return Result.success(f);
    }

    @GetMapping("info")
    @ApiOperation("用户详情")
    public Result<?> info(HttpServletRequest request) {
        String uid = request.getHeader("uid");
        if (uid == null || uid.isEmpty()) {
            return Result.notLogin();
        }
        return Result.success(userService.getOne(new QueryWrapper<User>()
                .eq("uid", uid)));
    }

    @GetMapping("getMsgList")
    @ApiOperation("发送列表")
    public Result<?> msgList(HttpServletRequest request, String flag, long cur, long size) {
        return Result.success(messageService.getMessagePage(request,flag,cur,size));
    }

    @GetMapping("login")
    @ApiOperation("登录")
    public Result<?> login(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxb87355b683322bcb&secret=076cdd3e7e5483b9b262ca2c2e9eff94&js_code=" + code + "&grant_type=authorization_code";
        String body = HttpRequest.get(url).execute().body();
        log.info(body);
        Object openid = JSONUtil.parseObj(body).get("openid");
        return Result.success(openid);
    }

    @GetMapping("yiyan")
    @ApiOperation("每日一言")
    public Result<?> yilian() {
        String body = HttpRequest.get("https://v1.hitokoto.cn/").execute().body();
        log.info(body);
        Object openid = JSONUtil.parseObj(body).get("hitokoto");
        return Result.success(openid);
    }

    @GetMapping("refresh")
    @ApiOperation("刷新token")
    public Result<?> refresh() {
        redisUtil.del("access_token");
        String body = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb87355b683322bcb&secret=076cdd3e7e5483b9b262ca2c2e9eff94").execute().body();
        String access_token = (String) JSONUtil.parseObj(body).get("access_token");
        redisUtil.set("access_token", access_token, 7200);
        return Result.success();
    }

    @GetMapping("getToken")
    @ApiOperation("获取token")
    public Result<?> getToken() {
        Object access_token = redisUtil.get("access_token");
        return Result.success(access_token);
    }
}

