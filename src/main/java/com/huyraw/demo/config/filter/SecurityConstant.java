package com.huyraw.demo.config.filter;

public class SecurityConstant {
    public static final String SECRET = "SECRET";
    public static final long EXPIRATION_TIME = 86400000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/api/user/login";
}
