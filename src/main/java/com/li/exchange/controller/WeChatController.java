package com.li.exchange.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.li.exchange.dto.WeixinDTO;
import com.li.exchange.utils.SecUtil;
import com.li.exchange.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LiMeiyuan on 2017/4/16.
 * description:
 */
@Controller
@RequestMapping("wechat")
public class WeChatController {
    private final String SECRET = "7179c6c4027c5c4713b57e609691fcd9";
    private final String APP_ID = "wx3d3983f770a1aafd";

    @RequestMapping("getSign")
    @ResponseBody
    public WeixinDTO getSign(@RequestParam String url, @RequestParam String randomStr,
                             @RequestParam String timestamp) {
        WeixinDTO dto = new WeixinDTO();
        dto.setAppId(APP_ID);
        dto.setRandomStr(randomStr);
        dto.setSecret(SECRET);
        dto.setTimestamp(timestamp);
        dto.setSign(null);
        //获取token
        String token = getToken();

        if (token == null) {
            return dto;
        }

        String ticket = getTicket(token);
        if (ticket == null) {
            return dto;
        }

        String secStr = "jsapi_ticket=" + ticket + "&noncestr=" + randomStr + "&timestamp=" + timestamp + "&url=" + url;
        String sign = SecUtil.getSHA1(secStr);
        dto.setSign(sign);
        return dto;
    }

    private String getToken() {
        String content = WebUtil.getHttpContent("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + SECRET, null);
        if (content != null) {
            JsonObject json = new JsonParser().parse(content).getAsJsonObject();
            if (json.get("access_token") == null) {
                return null;
            }
            return json.get("access_token").getAsString();
        }
        return null;
    }

    private String getTicket(String token) {
        String content = WebUtil.getHttpContent("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi", "GET");
        if (content != null) {
            JsonObject json = new JsonParser().parse(content).getAsJsonObject();
            if (json.get("ticket") == null) {
                return null;
            }
            return json.get("ticket").getAsString();
        }
        return null;
    }
}
