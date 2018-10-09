package com.example.chenwf.myapplication;

import android.text.TextUtils;

public class Main {

    public static final String GiveString = "level";

    /* 判断字符串是否为回文 level*/
    public static boolean checkIsCallBack(String str) {
        if (null != str) {
            if (str.length() == GiveString.length()) {
                return contains(str, GiveString);
            }
        }
        return false;
    }

    /*判断字符串是否包含特定子串*/
    public static boolean contains(String inputStr, String giveStr) {
        if (inputStr == null || giveStr == null)
            return false;
        if (inputStr.length() < giveStr.length()) {
            return false;
        }
        for (int i = 0; i < inputStr.length(); i++) {
            for (int j = 0; j < giveStr.length(); j++) {
                if (inputStr.length() - i < giveStr.length() -j ) {
                    break;
                }
                if (Integer.valueOf(inputStr.charAt(i + j)) != Integer.valueOf(giveStr.charAt(j))) {
                    break;
                }
                if (j == giveStr.length() - 1) {
                    return true;
                }
            }
        }
        return false;
    }


}