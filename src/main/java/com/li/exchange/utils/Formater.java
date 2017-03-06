package com.li.exchange.utils;

/**
 * Created by LiMeiyuan on 2017/3/5.
 * description:
 */
public class Formater {
    public static Double formatDouble(Double source) {
        return formatDouble(source, 6);
    }

    public static Double formatDouble(Double source, int round){
        return (double) Math.round(source * Math.pow(10, round)) /  Math.pow(10, round);
    }
}
