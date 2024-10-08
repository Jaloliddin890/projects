package uz.pdp.springsecurity.controllers.securityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }
}
