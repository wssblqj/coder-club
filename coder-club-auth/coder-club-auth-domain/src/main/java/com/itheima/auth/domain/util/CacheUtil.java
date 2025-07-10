package com.itheima.auth.domain.util;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class CacheUtil<K, V> {

    private Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build();

    public List<V> getResult(String cacheKey, Class<V> clazz, Function<String, List<V>> function) {
        List<V> list = new ArrayList<>();
        String content = localCache.getIfPresent(cacheKey);
        if (StringUtils.isBlank(content)) {
            list = function.apply(cacheKey);
            localCache.put(cacheKey, JSON.toJSONString(list));
        } else {
            list = JSON.parseArray(content, clazz);
        }
        return list;
    }

    public Map<K, V> getMapResult(String cacheKey, Class<K> keyClazz,
                               Class<V> valueClazz, Function<String, Map<K, V>> function) {
        Map<K, V> map = new HashMap<>();
        return new HashMap<>();
    }
}
