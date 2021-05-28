package com.example.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Swagger : 개발한 REST API 를 편리하게 문서화 해주고, 관리 및 제 3자가 편리하게 API를 호출하고 테스트 할 수 있는 프로젝트
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
