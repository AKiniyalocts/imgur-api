package com.akiniyalocts.imgur_api;

import android.text.TextUtils;

/**
 * Created by chja on 30.07.15.
 */
class Util {

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isNullOrEmpty(String str) {
        try {
            return TextUtils.isEmpty(str);
        } catch (RuntimeException e) {
            //junit cant handle android classes...
            return isNullOrEmptyFallack(str);
        }
    }

    private static boolean isNullOrEmptyFallack(String str) {
        return str == null || str.isEmpty();
    }
}
