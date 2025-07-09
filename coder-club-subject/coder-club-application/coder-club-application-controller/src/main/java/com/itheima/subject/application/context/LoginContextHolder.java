package com.itheima.subject.application.context;

import javax.security.auth.login.LoginContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录上下文
 */
public class LoginContextHolder {
    private static final ThreadLocal<Map<String, Object>> loginContextThreadLocal = new ThreadLocal<>();


    public static void set(String key, Object value) {
        Map<String, Object> map = getThreadLocalMap();
        map.put(key, value);
    }


    public static Object get(String key) {
        Map<String, Object> threadLocalMap = getThreadLocalMap();
        return threadLocalMap.get(key);
    }
    public static Map<String, Object> getThreadLocalMap() {
        Map<String, Object> map = loginContextThreadLocal.get();
        if(Objects.isNull(map)) {
            map = new ConcurrentHashMap<>();
            loginContextThreadLocal.set(map);
        }
        return map;
    }

    public static void remove() {
        loginContextThreadLocal.remove();
    }

    public static String getLoginId() {
        return (String) get("loginId");
    }
}
