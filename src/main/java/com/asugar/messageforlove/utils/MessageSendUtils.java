package com.asugar.messageforlove.utils;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;

import java.util.HashMap;

/**
 * 微信推送组件
 * @author 肖念昕
 * @date 2022/3/25 20:28
 */
public class MessageSendUtils {
    private static final String url = "http://www.pushplus.plus/api/send";

    public static String sendMsg (String title,String content) {
        JSONObject data = new JSONObject();
        data.putAll(new HashMap<String, String>(){{
            put("channel","wechat");
            put("content",content);
            put("template","html");
            put("title",title);
            put("token","510188bc052847c7adb448f45ee39750");
        }});
        return HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .body(data.toJSONString(50))
                .execute().body();
    }
}
