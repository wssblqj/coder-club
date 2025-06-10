package com.itheima.auth.domain.service;

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


}
