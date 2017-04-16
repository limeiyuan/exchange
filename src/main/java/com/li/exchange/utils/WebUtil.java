package com.li.exchange.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LiMeiyuan on 2016/10/2 17:11.
 * description:
 */
public class WebUtil {
    private static Log logger = LogFactory.getLog(WebUtil.class);

    private final static String USER_AGENT = "Mozilla/5.0";

    public static String getHttpContent(String url, String type) {
        try {
            InputStream httpInputStream = getHttpInputStream(url, type);
            if (httpInputStream == null) {
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(httpInputStream));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    public static InputStream getHttpInputStream(String url, String type) {
        if (type == null) {
            type = "GET";
        }
        try {
            URL destiny = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) destiny.openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestMethod(type);
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}
