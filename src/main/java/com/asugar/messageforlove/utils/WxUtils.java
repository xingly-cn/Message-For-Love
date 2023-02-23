package com.asugar.messageforlove.utils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * @Description:
 * @Author: 方糖
 * @Date: 2022/8/10 8:42 PM
 */
public class WxUtils {

    public static final String file = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRzl46662HRmCr\n" +
            "YkWLSvZUDx+NYcmNErTmtzcfj2q98/2r+v9xNotshQSfVQSr5eCEoyOdiZb2rhRX\n" +
            "oqylBznOpSJ752CgyQNqrN/zDsh5DAWvsnzYpuQRiOwN5vsG+6oloeW4lwOniUoY\n" +
            "GWqrnkoQWDpRQMM1JRUF9TCUIwtslPUQRUqPVyNaDditEgikWP0SDVsidwAMrpUU\n" +
            "DOG3b4RCdPCRSnzPt2oN8zlPUcq2dP4bh+Zo68BHgTvgEtBMlsVj7jl+IB0c0fGf\n" +
            "MUHUtYP57ZWRaAgrp3V/kGKIcLGVZNR5jLMDA5dFh9wv4z8j8LHcB7Eyqy+Dc3KS\n" +
            "ZbnSPyGzAgMBAAECggEBAMjmZn7fHGwTbQ/Pay5lmpiFXr5ALAJlLkpamHMlHp5D\n" +
            "291p3F5pT3qbqQB1JaMA3UvE956rmJ4ftSTxhYYE9Eq2/91LgtCTiy0D2vJgboDS\n" +
            "jUNfk4LV1EAiL39kdW2LYLLmYxWtrXco1IVdGTK6wMsD4NvbOtFR796jmV/QaukN\n" +
            "waM3lP9HmXZMqkWVvgWTTy77JZ0gV+P4GzHl/u9vv+mtOxytLJkLA7cTgywLhp2c\n" +
            "BQxFTkuaL/Fj46Kx7YExMOdKxfBhLf1+kEYwr9H5wnusecFbQEtLq8j0+kcJGDKA\n" +
            "5bTE99dJw/ku5TqvtnaxeVpezkzVFPQRTMwoW3tfL4kCgYEA8V1tz4G/vU1FqgFM\n" +
            "aDH2+xCRIGu0e5EgEJW1Dxtz9BGMRlZnRSE97QaZ2vPYQQQDXVV84x25WehaX3Cv\n" +
            "zZaeF6SI49A4QUAISlEtRYAOrd0Pv7M2C5/yeKTu4hjDQxbwTCw7OtmlhFwigLnm\n" +
            "zsg8PJ1zj68VnveZ83bFt4tG68UCgYEA3ocRv9PYfnUGetskMzd3qXuK1tRqu9it\n" +
            "Gpj1x7YqTqJRhYdHzynZHh43j9uoU01nXaJmdikeuyij2jnNV59EVo/JeD46j+wo\n" +
            "DgtZ09gtCo8sonTdwu9RHM0NOWw5jW72H+Nf5k+y3tGLYiLsJ0uYhbnGSKtMnlNj\n" +
            "6N0Eve3AVxcCgYADq/0wq/OjyJontO9ZonD0btMWYC0VuCZ5uh1QBoXkD6RHqabW\n" +
            "iWPsyC3tA8qlao82i0dkGR6pvHwreYZjJUhwaaNfe9pvO68kmkmLBiCzCmrj6rEG\n" +
            "yj8hELlFnUTTL3UmY4HfKlJ3SgOIW8ukF6266lZIuEwm5/NincryON8yVQKBgQCt\n" +
            "qCqp0OD6jsjnx2yQq5CuqsLGtC43Ofic8RGdMQQDKZkE43WercHKRzqFAZ5yo80u\n" +
            "vTXIsH5TAHweKU2eO6RRCRToyWdaOalojGMld2Zu/xyOCzWkkq5yE3NmhJbFUPqy\n" +
            "F/cW/MtSJ6TmiOpSICR+92NOAqBgTiOB7jltHsUjMQKBgH24eXcSPftLvzEGCFLY\n" +
            "5d4Tq+TuWKdAzavoXI8YneNT66aXrX2ovgvyROQ4WK3OiDph7ySdcHy6kBczeezi\n" +
            "VngdsorDcjQd6naO6sleS4pmpFtRBM9d/rMMA3ZWWy409qzDd3Nyv8k++mctUUWd\n" +
            "7jDut5ydE4XVTKU3WPHlpDqc\n" +
            "-----END PRIVATE KEY-----\n";

    public static PrivateKey getPrivateKey() throws IOException {
        try {
            String privateKey = file.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }
}
