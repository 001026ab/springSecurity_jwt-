package com.zgr.security.jwt.springsecurity_jwt.config;


import com.zgr.security.jwt.springsecurity_jwt.exception.JWTAccessDeniedHandler;
import com.zgr.security.jwt.springsecurity_jwt.exception.JWTAuthenticationEntryPoint;
import com.zgr.security.jwt.springsecurity_jwt.file.JwtTokenFilter;
import com.zgr.security.jwt.springsecurity_jwt.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ufi
 * @www.codesheep.cn 20190312
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //options全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //登录和注册的接口放行，其他接口全部接受验证
                .antMatchers(HttpMethod.POST, "/authentication/**").permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.PUT).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
                .antMatchers(HttpMethod.GET).authenticated()
                // 任意访问,放行swagger
                /* .antMatchers("/swagger-ui.html").permitAll()
                 .antMatchers("/swagger-resources/**").permitAll()
                 .antMatchers("/webjars/**").permitAll()
                 .antMatchers("/v2/**").permitAll()
                 .antMatchers("/api/**").permitAll()*/
                //.antMatchers("/*/api-docs").permitAll()
                //那两个异常文件只需要在这里配置就可以，在and后面
                .and()
                //token异常或过期，无token等异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                //添加无权限时的处理
                .accessDeniedHandler(new JWTAccessDeniedHandler());
        //使用前文定义的token过滤器
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

    /**
     * 放行swagger3.0
     * 注意不同版本的swagger需要放行的资源路径不一样
     *
     * @param web 忽略拦截url或静态资源文件夹 - web.ignoring(): 会直接过滤该url - 将不会经过Spring Security过滤器链
     *            http.permitAll(): 不会绕开springsecurity验证，相当于是允许该路径通过
     */
    @Override
    public void configure(WebSecurity web) {
        //放过所有get请求
        //web.ignoring().antMatchers(HttpMethod.GET);
        //登陆相关
        //web.ignoring().antMatchers("/app/login/**","/app/password");
        //swagger3相关接口 ,放行swagger3
        web.ignoring().antMatchers("/v3/**", "/doc.html", "/webjars/**", "/swagger**/**");
    }

}
