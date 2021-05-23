package com.example.hello.interceptor.interceptor;

import com.example.hello.interceptor.annotation.Auth;
import com.example.hello.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

// Interceptor
// Filter와 유사한 형태로 존재하지만, 차이점은 Spring Context에 등록된다.
// 따라서 스프링에 모든 Bean에 접근이 가능하다.
// 주로 인증 단계 처리나, Logging 하는데 사용한다.
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();

        // UriComponentsBuilder
        // 여러 개의 파라미터들을 연결하여 URL 형태로 만들어 주는 기능
        // Spring에서 URI와 관련된 작업을 조금 더 쉽게 할 수 있도록 도와주는 클래스
        URI uri = UriComponentsBuilder.fromUriString(url).query(request.getQueryString()).build().toUri();

        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has Annotation : {}", hasAnnotation);

        if(hasAnnotation) {
            // 권한 체크
            String query = uri.getQuery();
            if(query.equals("name=steve")) { // 특정 name 체크
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {

        if(handler instanceof ResourceHttpRequestHandler) { // JS, HTML ...
            return true;
        }

        // has Auth annotation
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotation(clazz) != null) {
            return true;
        }

        return false;
    }
}
