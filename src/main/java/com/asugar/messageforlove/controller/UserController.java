package com.asugar.messageforlove.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.asugar.messageforlove.entity.Message;
import com.asugar.messageforlove.entity.User;
import com.asugar.messageforlove.result.R;
import com.asugar.messageforlove.service.MessageService;
import com.asugar.messageforlove.service.UserService;
import com.asugar.messageforlove.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    private final UserService userService;
    private final MessageService messageService;

    private final RedisUtil redisUtil;

    @PostMapping("add")
    @ApiOperation("添加用户")
    public R add(HttpServletRequest request) {
        String uid = request.getHeader("uid");
        int f = userService.add(uid);
        return R.ok().data("添加状态", f);
    }

    @GetMapping("info")
    @ApiOperation("用户详情")
    public R info(HttpServletRequest request) {
        String uid = request.getHeader("uid");
        if (uid == null || uid.isEmpty()) {
            return R.notLogin();
        }
        return R.ok().data("info", userService.getOne(new QueryWrapper<User>().eq("uid", uid)));
    }

    @GetMapping("getMsgList")
    @ApiOperation("发送列表")
    public R msgList(HttpServletRequest request, String flag, long cur, long size) {
        String uid = request.getHeader("uid");
        QueryWrapper<Message> wrapper = new QueryWrapper<Message>().eq("uid", uid).orderByDesc("createtime");
        if (flag.equals("1")) {
            wrapper.eq("status", 0);
        } else if (flag.equals("2")) {
            wrapper.eq("status", 1);
        }
        IPage<Message> page = new Page<>(cur, size);
        messageService.page(page, wrapper);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @GetMapping("login")
    @ApiOperation("登录")
    public R login(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxb87355b683322bcb&secret=076cdd3e7e5483b9b262ca2c2e9eff94&js_code=" + code + "&grant_type=authorization_code";
        String body = HttpRequest.get(url).execute().body();
        log.info(body);
        Object openid = JSONUtil.parseObj(body).get("openid");
        return R.ok().data("openid", openid);
    }

    @GetMapping("yiyan")
    @ApiOperation("每日一言")
    public R yilian() {
        String body = HttpRequest.get("https://v1.hitokoto.cn/").execute().body();
        log.info(body);
        Object openid = JSONUtil.parseObj(body).get("hitokoto");
        return R.ok().data("content", openid);
    }

    @GetMapping("refresh")
    @ApiOperation("刷新token")
    public R refresh() {
        redisUtil.del("access_token");
        String body = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb87355b683322bcb&secret=076cdd3e7e5483b9b262ca2c2e9eff94").execute().body();
        String access_token = (String) JSONUtil.parseObj(body).get("access_token");
        redisUtil.set("access_token", access_token, 7200);
        return R.ok();
    }

    @GetMapping("getToken")
    @ApiOperation("获取token")
    public R getToken() {
        Object access_token = redisUtil.get("access_token");
        return R.ok().data("access_token", access_token);
    }


    public UserController(UserService userService, MessageService messageService, RedisUtil redisUtil) {
        this.userService = userService;
        this.messageService = messageService;
        this.redisUtil = redisUtil;
    }
}

