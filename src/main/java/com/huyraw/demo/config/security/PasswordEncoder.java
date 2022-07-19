package com.huyraw.demo.config.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String encode(String value) {
        return passwordEncoder().encode(value);
    }

    public boolean matches(String raw, String pass) {
        return passwordEncoder().matches(raw, pass);
    }
}
