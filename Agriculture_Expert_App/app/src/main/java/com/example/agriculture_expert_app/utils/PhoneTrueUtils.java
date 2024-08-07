package com.example.agriculture_expert_app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//电话号码验证工具类
public class PhoneTrueUtils {
    public static boolean isPhone(String phone){
        Pattern pattern = Pattern.compile("^1([34578])\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}



