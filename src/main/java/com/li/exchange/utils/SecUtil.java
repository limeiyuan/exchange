package com.li.exchange.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LiMeiyuan on 2016/10/2 18:04.
 * description:
 */
public class SecUtil {
    public static String getSHA1(String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return String.format("%x", new java.math.BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
