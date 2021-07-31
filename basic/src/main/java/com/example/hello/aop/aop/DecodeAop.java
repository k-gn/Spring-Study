package com.example.hello.aop.aop;

import com.example.hello.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/*
    암호화된 값 변환등에서
    필터나 인터셉터 에서 변환하려고 하면 톰캣 자체에서 한번 body 를 읽으면 더이상 읽을 수 없도록 막아둠
    aop 에선 값 자체가 객체화 되었기 때문에 값을 변환하거나 추가해줄 수 있다.
    따라서 암호화된 필드가 들어올 때 aop 로 복호화가 완료된 상태에서 들어오게 할 수 있고, 반대로 내보낼 때 변경시켜 내보낼 수 있다.
*/
@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.hello.aop.controller..*.*(..))")
    public void cut(){}

    @Pointcut("@annotation(com.example.hello.aop.annotation.Decode)")
    private void enableDecode() {}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            if(arg instanceof User) {
                User user = User.class.cast(arg); // 형 변환
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8"); // 디코딩
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("returnObj : " + returnObj);
        if(returnObj instanceof User) {
            User user = User.class.cast(returnObj); // 형 변환
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes()); // 인코딩
            user.setEmail(base64Email);
        }
    }
}
