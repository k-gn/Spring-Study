package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main() {
        return "main.html";
    }

    // Page Controller 에서 Json 데이터 내려주는 방법
    //1. ResponseEntity
    //2. ResponseBody
    @ResponseBody
    @GetMapping("/user")
    public User user() {
        // var : 타입 추론
        var user = new User();
        user.setName("steve");
        user.setAddress("패스트 캠퍼스");
        return user;
    }
    
    // 반대로 Rest Controller 에서 페이지 내려주는 방법
    // -> ModelAndView 사용 시 뷰 리졸버 동작
    
    // @ModelAttribute 메소드 실행 결과로 리턴된 객체는 자동으로 Model에 저장
}
