package com.itheima.auth.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.itheima.auth.application.convert.AuthPermissionDTOConverter;
import com.itheima.auth.application.convert.AuthRolePermissionDTOConverter;
import com.itheima.auth.application.dto.AuthRolePermissionDTO;
import com.itheima.auth.common.entity.Result;
import com.itheima.auth.domain.entity.AuthPermissionBO;
import com.itheima.auth.domain.entity.AuthRolePermissionBO;
import com.itheima.auth.domain.service.AuthRolePermissionDomainService;
import com.itheima.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rolePermission")
@Slf4j
public class RolePermissionController {


    @Autowired
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto: {}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error: {}", e.getMessage());
            return Result.fail("新增权限角色关联失败");
        }
    }
}
