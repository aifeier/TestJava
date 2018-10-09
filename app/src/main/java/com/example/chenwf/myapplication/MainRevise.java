package com.example.chenwf.myapplication;

import android.text.TextUtils;

public class MainRevise {

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
				// 修订要求：这个版本不需要在最里面的循环
                if (inputStr.length() - i < giveStr.length() -j ) {
                    break;
                }
                if (Integer.valueOf(inputStr.charAt(i + j)) != Integer.valueOf(giveStr.charAt(j))) {
                    break;
                }
				// 修订要求：这个版本不需要在最里面的循环
                if (j == giveStr.length() - 1) {
                    return true;
                }
            }
			// 修订如下：-->
			int j = 0;
			if (inputStr.length() - i < giveStr.length()) {
				break;
			}
			while (j < giveStr.length())
			{
				if (Integer.valueOf(inputStr.charAt(i + j)) != Integer.valueOf(giveStr.charAt(j))) {
                    break;
                }
				j++;
			}
			if (j == giveStr.length())
			{
				return true;
			}		
        }
		
        return false;
    }


}