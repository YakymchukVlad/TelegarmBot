package com.khnu.yakymchuk.utils.assertion;

import com.khnu.yakymchuk.utils.StringUtils;

/**
 * @author Vladyslav_Yakymchuk
 * Created at 22.11.2019
 */
public class Assert {

    public static void asserNotNull(Object o, String errorMessage) {
        if (o == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void asserHasText(String str, String errorMessage) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
