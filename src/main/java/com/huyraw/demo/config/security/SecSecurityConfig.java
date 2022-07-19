package com.huyraw.demo.config.security;



import com.huyraw.demo.config.filter.CustomAuthentication;
import com.huyraw.demo.config.filter.CustomAuthorization;
import com.huyraw.demo.util.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.huyraw.demo.config.filter.SecurityConstant.LOGIN_URL;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    private  final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;



    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder.passwordEncoder());
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        CustomAuthentication customAuthenticationFilter =
                new CustomAuthentication(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl(LOGIN_URL);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/login")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/**")
                .hasAuthority(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/**")
                .hasAuthority(UserRole.ADMIN.toString())
                .anyRequest()
                .permitAll();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(
                new CustomAuthorization(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the
        // wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("" +
                "/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
