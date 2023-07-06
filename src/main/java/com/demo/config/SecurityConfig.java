package com.demo.config;

import com.demo.config.Auth.UserStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserStore userStore;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeHttpRequests()
                .antMatchers("/**/api1").authenticated()
                .antMatchers("/**/loggers/**").hasRole("MANAGER");

        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        userStore.getAll().stream().filter(Objects::nonNull).forEach(user -> {
            try {
                auth.inMemoryAuthentication()
                        .withUser(user.getName()).password(encoder().encode(user.getPass())).roles(user.getRoles());
            } catch (Exception e) {
                log.warn("Security starts without any users");
            }
        });
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
