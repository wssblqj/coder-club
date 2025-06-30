package com.itheima.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.itheima.auth.application.convert.AuthUserDTOConverter;
import com.itheima.auth.application.dto.AuthUserDTO;
import com.itheima.auth.common.entity.Result;
import com.itheima.auth.domain.convert.AuthUserBOConverter;
import com.itheima.auth.domain.entity.AuthUserBO;
import com.itheima.auth.domain.service.AuthUserDomainService;
import com.itheima.auth.infra.basic.entity.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;


    /**
     * 注册
     *
     * @param authUserDTO
     * @return
     */
    @PostMapping("/register")
    public Result<Boolean> add(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.add.dto: {}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.error: {}", e.getMessage());
            return Result.fail(e.getMessage());

        }
    }

    private static void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkNotNull(authUserDTO.getUserName(), "用户名不能为空");
        Preconditions.checkNotNull(authUserDTO.getEmail(), "邮件地址不能为空");
        Preconditions.checkNotNull(authUserDTO.getPassword(), "密码不能为空");
    }

    /**
     * 修改
     *
     * @param authUserDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto: {}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "用户ID不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error: {}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto: {}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "用户ID不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error: {}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto: {}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "用户ID不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus.error: {}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 登录
     * @param validCode
     * @return
     */
    @RequestMapping("/doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            SaTokenInfo tokenInfo = authUserDomainService.doLogin(validCode);
            return Result.ok(tokenInfo);
        } catch (Exception e) {
            log.error("UserController.doLogin.error: {}", e.getMessage());
            return Result.fail("用户登陆失败");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            Preconditions.checkNotNull(authUserDTO.getUserName(), "用户名称不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.error("UserController.getUserInfo.error: {}", e.getMessage());
            return Result.fail("查询用户信息失败");
        }
    }

    @RequestMapping("/logOut")
    public Result<AuthUserDTO> logOut(@RequestBody String userName) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名称不能为空");
            StpUtil.logout(userName);
            return Result.ok("用户登出成功");
        } catch (Exception e) {
            log.error("UserController.logOut.error: {}", e.getMessage());
            return Result.fail("用户登出失败");
        }
    }
    
}
