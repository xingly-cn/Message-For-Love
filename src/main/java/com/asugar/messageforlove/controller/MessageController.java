package com.asugar.messageforlove.controller;


import com.asugar.messageforlove.entity.Message;
import com.asugar.messageforlove.entity.vo.MsgVo;
import com.asugar.messageforlove.result.R;
import com.asugar.messageforlove.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/message/message")
@Api(tags = "短信模块")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("add")
    @ApiOperation("添加短信")
    public R add (@RequestBody MsgVo msgVo, HttpServletRequest request) {
        int f = messageService.add(msgVo,request);
        return f == 1 ? R.ok().message("发送短信成功") : R.notLogin();
    }

    @GetMapping("withdraw")
    @ApiOperation("撤回短信")
    public R withdraw (String id, HttpServletRequest request) {
        int f = messageService.withdraw(id,request);
        return f == 1 ? R.ok().message("撤回短信成功") : R.notLogin();
    }

    @GetMapping("box")
    @ApiOperation("留言板")
    public R box (String num) {
        List<Message> list = messageService.list(new QueryWrapper<Message>().orderByDesc("createtime").last("limit " + num));
        return R.ok().data("list",list);
    }

}

