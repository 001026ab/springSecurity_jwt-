package com.zgr.security.jwt.springsecurity_jwt.controller;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ufi
 * @www.codesheep.cn
 * 20190312
 */
@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public String createToken( String username,String password ) throws AuthenticationException {
        return authService.login( username, password );
    }


    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public User register( @RequestBody User addedUser ) throws AuthenticationException {
        return authService.register(addedUser);
    }

    @RequestMapping(value = "/authentication/test", method = RequestMethod.POST)
    public String test5() {
        User user = new User();
        user.setUsername("24211");
        return "ROLE_ADMIN  /two/test5555接口调用成功！"+user.getUsername();
    }
}
