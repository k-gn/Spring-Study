package com.sp.fc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Security Config File
//@Order(1)
@EnableWebSecurity(debug = true) // 1. 스프링 시큐리티 사용을 위한 어노테이션 선언
@EnableGlobalMethodSecurity(prePostEnabled = true) // Pre, Post 로 권한 체크를 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 2. WebSecurityConfigurerAdapter 상속 => Security Filter


    // 스프링 시큐리티의 인증에 대한 지원을 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // 사용자 저장소 활성화 및 설정
                .withUser(User.builder() // 사용자 추가
                    .username("user2")
                    .password(passwordEncoder().encode("2222"))
                    .roles("USER"))
                .withUser(User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("3333"))
                    .roles("ADMIN"));
    }

    // Password Encoder (필수)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티의 설정을 담당
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 접근 요청 권한 설정
//        http.antMatcher("/api/**");
//        http.authorizeRequests((requests)
//                -> requests
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated());
//        http.formLogin();
//        http.httpBasic();

        http
                .headers().disable()
                .csrf().disable()
                .formLogin(login ->
                            login.defaultSuccessUrl("/", false)
                        )
                .logout().disable()
                .requestCache().disable();

    }
}
