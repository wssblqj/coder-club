package com.itheima.subject.common.util;

import com.itheima.subject.common.context.LoginContextHolder;

/**
 * 登录工具类
 */
public class LoginUtil {
    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }
}
