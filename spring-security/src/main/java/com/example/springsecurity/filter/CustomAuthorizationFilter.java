package com.example.springsecurity.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.springsecurity.filter.SecurityConstant.*;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals(LOGIN_URL)) {
            filterChain.doFilter(request, response);
        }else {
            String authorizationHeader = request.getHeader(HEADER_STRING);
            if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
                String token = authorizationHeader.substring(TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
            }
        }
    }
}
