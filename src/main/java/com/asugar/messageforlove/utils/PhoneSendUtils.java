package com.asugar.messageforlove.utils;


import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class PhoneSendUtils {
    public static void send(String content) {
        // 成功推送
        String execute = HttpRequest.get("https://api2.pushdeer.com/message/push?pushkey=PDU1083TBCC4lGtxaJ1TaTDyOuAiHgRAPNjahORg&text=" + content + "&desp=&type=markdown").execute().body();
        log.info("发送短信返回结果：{}", execute);
    }

    public static void main(String[] args) {
        send("真的好遗憾啊，明明不想失去，可是却无能为力，说真的，那种想放弃又舍不得的滋味真的很折磨人，留着你的vx却不能联系，看着你的动态却没有消息，无数次梦到你找我了，醒来打开手机却一条消息都没有。一场相遇相知，留下一生的回忆，什么都可以删除拉黑，唯独记忆删除不了，王凯走了拜拜-来自微信小程序[风的一封来信]" + new Date());
    }
}
