package com.asugar.messageforlove.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.asugar.messageforlove.entity.Remote;
import com.asugar.messageforlove.mapper.RemoteMapper;
import com.asugar.messageforlove.result.R;
import com.asugar.messageforlove.utils.WxUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/jsapi/pay")
@Api(tags = "支付模块")
@Slf4j
public class JsApiPayController {

    @Resource
    private RemoteMapper remoteMapper;


    @GetMapping("/findNo")
    @ApiOperation("查询订单")
    public R findNo(String out_trade_no) {


        return R.ok();
    }

    @GetMapping("/createNo")
    @ApiOperation("JSAPI下单")
    public R createPay(HttpServletRequest request) throws IOException, GeneralSecurityException, HttpCodeException, NotFoundException {
        String openId = request.getHeader("uid");
        if (openId == null || openId.isEmpty()) {
            return R.notLogin();
        }

        // 自动获取微信证书, 第一次获取证书绕过鉴权
        CertificatesManager instance = CertificatesManager.getInstance();

        String merchantId = "1603622321";
        String serialNumber = "763A20D4E6B149FBE22A0C9B09E9D7969BDE1EB6";
        String apiV3Key = "4680BBFA2DC33AB4F5D657658156A075";
        String wechatAppId = "wxb87355b683322bcb";

        instance.putMerchant(merchantId, new WechatPay2Credentials(merchantId,
                        new PrivateKeySigner(serialNumber,
                                WxUtils.getPrivateKey())),
                apiV3Key.getBytes(StandardCharsets.UTF_8));

        Verifier verifier = instance.getVerifier(merchantId);

        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId, serialNumber,
                        WxUtils.getPrivateKey()).withValidator(new WechatPay2Validator(verifier));

        CloseableHttpClient httpClient = builder.build();

        // 构建订单数据
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();


        Remote remote = remoteMapper.selectById("1");

        int price = 59;

        price = Integer.parseInt(remote.getPrice());

        ObjectNode rootNode = objectMapper.createObjectNode();
        String wx_no = UUID.randomUUID().toString().replaceAll("-", "");
        rootNode.put("mchid", "1603622321")
                .put("appid", "wxb87355b683322bcb")
                .put("description", "「风的一封来信」给Ta的一封信 稳定版")
                .put("notify_url", "http://127.0.0.1:8100/jsapi/pay/notify")
                .put("out_trade_no", wx_no);
        rootNode.putObject("amount")
                .put("total", price);
        rootNode.putObject("payer")
                .put("openid", openId);

        objectMapper.writeValue(bos, rootNode);

        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String res = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(res);
        return R.ok().data("prepay_id", jsonObject.get("prepay_id"));
    }


    @ApiOperation("paySign")
    @GetMapping("wakePay")
    public R wakePay(String prepay_id) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        long time = DateUtil.currentSeconds();
        String nonstr = RandomUtil.randomString(20);
        String pack = "prepay_id=" + prepay_id;
        String t = "wxb87355b683322bcb\n";
        t += +time + "\n";
        t += nonstr + "\n";
        t += pack + "\n";

        // 签名
        PrivateKey privateKey = WxUtils.getPrivateKey();
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(t.getBytes(StandardCharsets.UTF_8));
        byte[] sign = rsa.sign();

        String paySign = Base64.getEncoder().encodeToString(sign);
        return R.ok().data("paySign", paySign).data("timeStamp", "" + time)
                .data("nonceStr", nonstr)
                .data("package", pack)
                .data("signType", "RSA")
                .data("nonceStr", nonstr);
    }
}
