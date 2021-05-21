package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    // delete -> 리소스 삭제 200 ok
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, String account) {
        System.out.println(userId);
        System.out.println(account);
    }
}
