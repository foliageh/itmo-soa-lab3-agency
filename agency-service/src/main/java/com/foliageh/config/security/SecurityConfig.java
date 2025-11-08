package com.foliageh.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(java.util.Arrays.asList("*"));
                    config.setAllowedMethods(java.util.Arrays.asList("*")); // Все методы (GET, POST и т.д.)
                    config.setAllowedHeaders(java.util.Arrays.asList("*")); // Все заголовки
                    config.setAllowCredentials(false); // Если не нужны куки
                    return config;
                }))
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
}
