package com.example.hello.interceptor.config;

import com.example.hello.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // final 객체를 주입할 생성자 생성
public class MvnConfig implements WebMvcConfigurer {

//    @Autowired -> 순환참조가 발생할 수 있어서 요즘은 RequiredArgsConstructor를 사용한다.
    private final AuthInterceptor authInterceptor;

    // Interceptor 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 등록 순서에 따라 실행됨
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
    }
}
