package com.zgr.security.jwt.springsecurity_jwt.controller;

import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ufi
 * @www.codesheep.cn 20190312
 */
@RestController
@Api(tags = "测试")
public class TestController {

    /**
     * 注意hasAnyAuthority与hasAuthority的区别，前者可以同时配置多个权限角色，后者只能配置一个
     *
     * @return
     */


    /**
     * 测试普通权限
     *
     * @return
     */
    @ApiOperation(value = "测试权限'ROLE_NORMAL2','ROLE_ADMIN'")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL2','ROLE_ADMIN')")
    @RequestMapping(value = "/normal/test", method = RequestMethod.GET)
    public String test1() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    /**
     * 测试管理员权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }

    /**
     * 测试其他
     *
     * @return
     */

    @PreAuthorize("hasAuthority('ROLE_ELSE') OR hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/else/test", method = RequestMethod.GET)
    public String test3() {
        return "ROLE_ELSE OR ROLE_ADMIN  /else/test接口调用成功！";
    }

    /**
     * 测试其他
     *
     * @return
     */


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/two/test", method = RequestMethod.GET)
    public String test4() {
        return "ROLE_ADMIN  /two/test接口调用成功！";
    }

    /**
     * 测试其他
     *
     * @return
     */
    @RequestMapping(value = "/two5/test", method = RequestMethod.GET)
    public String test5() {
        User user = new User();
        user.setUsername("24211");
        return "ROLE_ADMIN  /two/test5555接口调用成功！" + user.getUsername();
    }
}
