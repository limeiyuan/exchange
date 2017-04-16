package com.li.exchange.dto;

/**
 * Created by LiMeiyuan on 2016/10/11 10:05.
 * description:
 */
public class WeixinDTO {
    private String secret;//微信公众号secret
    private String appId;//微信公众号appId
    private String randomStr;//生成网页签名用随机字符串
    private String timestamp;//生成网页签名用时间戳
    private String sign;//微信网页签名

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRandomStr() {
        return randomStr;
    }

    public void setRandomStr(String randomStr) {
        this.randomStr = randomStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
