package com.khnu.yakymchuk.utils;

/**
 * @author Vladyslav_Yakymchuk
 * Created at 22.11.2019
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }
}
