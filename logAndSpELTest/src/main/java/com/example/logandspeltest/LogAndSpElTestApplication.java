package com.example.logandspeltest;

import com.example.logandspeltest.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LogAndSpElTestApplication {

    @Autowired
    private LogService logService;

    public static void main(String[] args) {
        SpringApplication.run(LogAndSpElTestApplication.class, args);
    }

    @GetMapping("/log")
    public String getLog() {
        logService.log();
        return "console log";
    }
}
/*
 - 로깅이란?
    - 정보를 제공하는 일련의 기록인 로그(log)를 생성하도록 시스템을 작성하는 활동.
    - 시스템 동작 시 시스템의 상태, 작동 정보를 시간의 경과에 따라 기록하는 것
    - 운영 중인 웹 어플리케이션이 문제가 발생했을 경우, 문제의 원인을 파악하려면 문제가 발생했을 때 당시의 정보가 필요하다.
      이런 정보를 얻기 위해서 Exception이 발생했거나, 중요 기능이 실행되는 부분에서는 적절한 로그를 남겨야한다. (디버깅)
    - 또한 분석 데이터로 활용도 가능하다.

 - 자바 오픈소스 로깅 프레임워크, SLF4J의 구현체
 - 스프링 부트의 기본으로 설정되어 있어서 사용시 별도로 라이브러리를 추가하지 않아도 된다.
 - log4j, log4j2 등과 성능을 비교했을 때에도 logback이 더 훌륭한 성능을 보여준다.


    Level	     의미
    1.ERROR	-    사용자 요청을 처리하는 중 문제가 발생함.
    2.WARN	-    처리 가능한 문제지만, 향후 시스템 에러의 원인이 될 수 있음.
    3.INFO	-    로그인이나 상태 변경과 같은 정보성 메시지
    4.DEBUG	-    개발 시 디버깅 목적으로 출력하는 메시지
    5.TRACE	-    DEBUG레벨보다 좀 더 상세한 메시지
*/

