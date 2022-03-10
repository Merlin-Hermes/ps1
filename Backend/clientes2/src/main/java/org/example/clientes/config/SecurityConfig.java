package org.example.clientes.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/usuarios").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clientes/**", "/api/quarto/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

}
