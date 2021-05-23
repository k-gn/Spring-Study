package com.example.hello.client.controller;

import com.example.hello.client.dto.Req;
import com.example.hello.client.dto.UserResponse;
import com.example.hello.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("")
    public Req<UserResponse> getHello() {
//        return restTemplateService.hello();
//        return restTemplateService.exchange();
        return restTemplateService.genericExchange();
    }

    @PostMapping("")
    public UserResponse post() {
        return restTemplateService.post();
    }
}
