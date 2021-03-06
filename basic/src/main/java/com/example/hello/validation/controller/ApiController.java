package com.example.hello.validation.controller;

import com.example.hello.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("apiController2")
@RequestMapping("/apiv")
public class ApiController {

    @PostMapping("/user")
    // @Valid 이 붙은 객체는 유효성 검증을 수행
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) {
        // BindingResult - @Valid를 통해 예외 발생 시 바로 예외가 발생하는게 아니라 결과가 BindingResult에 들어간다.
        // 따라서 BindingResult를 통해 예외처리를 할 수 있다.
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                // FieldError : 에러가 발생한 필드 정보를 가진다.
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                //System.out.println(field.getField());
                //System.out.println(message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        // custom validation

        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}
