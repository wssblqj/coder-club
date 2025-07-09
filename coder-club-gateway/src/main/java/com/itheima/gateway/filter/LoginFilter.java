package com.itheima.gateway.filter;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登录拦截器
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter {
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        SaReactorSyncHolder.setContext(exchange);
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        if (log.isInfoEnabled()) {
            log.info("LoginFilter.filter.url：{}", url);
        }
        if (url.equals("/user/doLogin")) {
            return chain.filter(exchange);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        log.info("LoginFilter.filter.url:{}", new Gson().toJson(tokenInfo));
        String loginId = (String) tokenInfo.getLoginId();
        if(StringUtils.isBlank(loginId)) {
            throw new RuntimeException("未获取用户信息");
        }
        mutate.header("loginId", loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
