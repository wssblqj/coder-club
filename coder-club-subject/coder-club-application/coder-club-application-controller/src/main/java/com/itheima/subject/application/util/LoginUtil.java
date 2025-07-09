package com.itheima.subject.application.util;


import com.itheima.subject.application.context.LoginContextHolder;

/**
 * 登录工具类
 */
public class LoginUtil {
    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }
}
