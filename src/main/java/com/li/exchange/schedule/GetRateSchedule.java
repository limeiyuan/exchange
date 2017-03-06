package com.li.exchange.schedule;

import com.li.exchange.entity.Rate;
import com.li.exchange.service.RateService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

/**
 * Created by LiMeiyuan on 2017/3/4.
 * description:
 */
@Component
public class GetRateSchedule {
    @Resource
    private RateService rateService;

    private final static String APPKEY = "0a7af06fbeb3585450ba9b7559494446";

    @PostConstruct
//    @Scheduled(cron = "0 0 0/1 * * *")
    public void getRate() {
        String result;
        String url = "http://op.juhe.cn/onebox/exchange/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)

        try {
            result = net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if (object.getInt("error_code") == 0) {
                analyseData(object.getString("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    private static String net(String strUrl, Map<String, Object> params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        if (StringUtils.isEmpty(method)) {
            method = "GET";
        }
        try {
            StringBuilder sb = new StringBuilder();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlEncode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(60 * 1000);
            conn.setReadTimeout(60 * 1000);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlEncode(params));
                } catch (Exception e) {
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    private static String urlEncode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void analyseData(String data) {
        try {
            System.out.println(data);
            JSONObject object = new JSONObject(data);
            JSONArray array = object.getJSONArray("list");
            for (int index = 0; index < array.length(); index++) {
                JSONArray record = array.getJSONArray(index);
                if (record.length() == 6) {
                    Rate rate = new Rate();
                    rate.setName(record.getString(0));
                    System.out.println(rate.getName());
                    int count = record.getInt(1);
                    rate.setBought_in(getValue(record, 3, count));
                    rate.setBought_out(getValue(record, 4, count));
                    rate.setRate(getValue(record, 5, count));
                    if (rate.getRate() != null) {
                        rate.setRate(rate.getRate() + 0.168);
                    }
                    rate.setCreateTime(new Date());
                    rateService.save(rate);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Double getValue(JSONArray array, Integer index, Integer count) throws JSONException {
        String str = array.getString(index);
        try {
            double value = Double.valueOf(str);
            return (double) Math.round(value / count * 1000000) / 1000000;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
