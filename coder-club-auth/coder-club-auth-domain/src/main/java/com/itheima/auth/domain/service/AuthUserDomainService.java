package com.itheima.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.itheima.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {

    /**
     * 注册
     *
     * @param authUserBO
     * @return
     */
    Boolean register(AuthUserBO authUserBO);


    /**
     * 修改
     * @param authUserBO
     * @return
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 删除
     * @param authUserBO
     * @return
     */
    Boolean delete(AuthUserBO authUserBO);


    /**
     * 登录
     * @param validateCode
     * @return
     */
    SaTokenInfo doLogin(String validateCode);

    /**
     * 获取用户信息
     * @param authUserBO
     * @return
     */
    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
