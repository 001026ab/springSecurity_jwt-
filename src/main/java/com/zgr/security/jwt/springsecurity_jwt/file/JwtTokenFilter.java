package com.zgr.security.jwt.springsecurity_jwt.file;

import com.zgr.security.jwt.springsecurity_jwt.comm.Const;
import com.zgr.security.jwt.springsecurity_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/28 15:18
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(Const.HEADER_STRING);
       // System.out.println("token：" + authHeader);
        //System.out.println("访问路径：" + request.getServletPath() + ";;;;" + request.getRequestURI() + ":::+");
        //System.out.println("账号密码：" + request.getParameter("username") + "::::::" + request.getParameter("password"));
        //  System.out.println("token是否包含某前缀：" + authHeader.startsWith(Const.TOKEN_PREFIX));
        if (authHeader != null) {
            String username = jwtUtil.getUsernameFromToken(authHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //token验证
                if (jwtUtil.validateToken(authHeader, userDetails)) {
                    //权限校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
