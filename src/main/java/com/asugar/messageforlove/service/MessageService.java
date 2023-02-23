package com.asugar.messageforlove.service;

import com.asugar.messageforlove.entity.Message;
import com.asugar.messageforlove.entity.vo.MsgVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
public interface MessageService extends IService<Message> {

    int add(MsgVo msgVo, HttpServletRequest request);

    int withdraw(String id, HttpServletRequest request);
}
