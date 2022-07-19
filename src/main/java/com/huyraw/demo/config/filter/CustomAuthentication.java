package com.huyraw.demo.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.huyraw.demo.config.filter.SecurityConstant.EXPIRATION_TIME;
import static com.huyraw.demo.config.filter.SecurityConstant.SECRET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAuthentication extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = "";
        String password = "";
        try {
            HashMap<String, String> body = getBody(request);
            username = body.get("username");
            password = body.get("password");
        } catch (IOException ex) {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        String access_token =
                JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(
                                "roles",
                                user.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .sign(algorithm);

        Map<String, String> token = new HashMap<>();
        token.put("access_token", access_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), token);
    }


    private HashMap<String, String> getBody(HttpServletRequest request) throws IOException {
        HashMap<String, String> body = new HashMap<>();
        try (BufferedReader reader = request.getReader()) {
            reader.readLine();
            getAttribute(body, reader);
            getAttribute(body, reader);
        }

        return body;
    }

    private void getAttribute(HashMap<String, String> body, BufferedReader reader)
            throws IOException {
        String line;
        if ((line = reader.readLine()) != null) {
            String rs = line.replaceAll("\"", "").trim();
            String[] temp = rs.split(":");
            body.put(temp[0], temp[1].replaceAll(",", ""));
        }
    }
}
