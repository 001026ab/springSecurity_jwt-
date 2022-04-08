package com.zgr.security.jwt.springsecurity_jwt.controller;


import com.zgr.security.jwt.springsecurity_jwt.entity.User;
import com.zgr.security.jwt.springsecurity_jwt.returnResult.MyResult;
import com.zgr.security.jwt.springsecurity_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ufi
 * @www.codesheep.cn 20190312
 */
@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private  StringRedisTemplate redisTemplate;


    @GetMapping(value = "/authentication/test666")
    public void test666(){
        redisTemplate.convertAndSend("topic1","2020顺利度过---1");
        redisTemplate.convertAndSend("topic2","2020顺利度过---2");
        redisTemplate.convertAndSend("topic3","2020顺利度过---3");
    }
    @PostMapping(value = "/authentication/login")
    public MyResult<String> createToken(String username, String password) throws AuthenticationException {
        return authService.login(username, password);
    }


    @PostMapping(value = "/authentication/register")
    public MyResult<User> register(@RequestBody User addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }

    @PostMapping(value = "/authentication/test")
    public String test5() {
        User user = new User();
        user.setUsername("24211");
        return "ROLE_ADMIN  /two/test5555接口调用成功！" + user.getUsername();
    }


}
