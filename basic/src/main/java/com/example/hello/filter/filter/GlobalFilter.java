package com.example.hello.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Filter
// Web Application 에서 관리된다.
// Client 로 부터 오는 요청/응답에 대해 최초/최종 단계의 위치한다.
// 요청/응답의 정보를 변경하거나 순수한 정보를 확인할 수 있다.
// request, response의 Logging 용도나 인증관련 Logic 등을 처리할 수 있다.
// 선/후 처리 함으로써 business logic과 분리시킨다.
@Slf4j
@WebFilter("/apif/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리
        // Reader로 read를 하게 되면 안에 내용을 다 읽게 되고 그 이후엔 더 이상 읽을 수 없게 된다.
        // 누구든 read를 한번 하게 되면 내용을 더 이상 읽을 수 없다.
        // 이때 스프링에서 캐시를 이용하면 몇 번이든 읽을 수 있다.
        // ContentCaching ~ 클래스를 사용
        // 단. 후처리(chain.doFilter 이 후) 때 내용을 읽을 수 있음 (전처리에선 길이만 읽어온다.)
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        chain.doFilter(httpServletRequest, httpServletResponse);

        String url = httpServletRequest.getRequestURI();
        // 후처리
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, requestBody : {}", url, reqContent);

        //여기서 내용을 다 빼버리기 때문에 밑에 copyBodyToResponse() 사용하여 읽은 내용만큼 다시 복사를 해준다.!
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);

    }

}
