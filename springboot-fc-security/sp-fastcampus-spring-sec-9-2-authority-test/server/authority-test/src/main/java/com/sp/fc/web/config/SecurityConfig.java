package com.sp.fc.web.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Collection;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder()
                        .username("user1")
                        .password("1111")
                        .roles("USER")
                )
                ;
    }

    // FilterSecurityInterceptor 는 컨트롤러나 또는 서비스에 들어오는 요청을 가로채서 권한 검증
    // SecurityInterceptor 는 FilterSecurityInterceptor 와 MethodSecurityInterceptor 로 나뉨
    // 필터이긴 하지만 시큐리티 인터셉터에 기능에 더 가깝다.
    // SecurityConfig 에서 권한 정보를 가져와서 사용한다.
    // AccessDecisionManager 를 통해 권한여부를 판단하고 통과시켜주거나 Deny 한다.
    // 또한 SecurityMetadataSource 라는 인자를 받는다. 각 url에 매핑된 security를 판단하는 근거자료를 가지고 있다.
    // AOP 개념을 따르고 있다.
    // FilterInvocation : 호출한 필터와 인자 정보
    // FilterSecurityInterceptor 에 들어오면 바로 invoke 메소드로 FilterInvocation 를 인자로 받아 proxy 로 동작
    // beforeInvodation : Security Config 에서 설정한 접근 제한을 체크. (거의 애만 동작)
    // finallyInvocation : RunAs 권한을 제거.
    // afterInvocation : AfterInvocationManager 를 통해 체크가 필요한 사항을 체크.
    // 특별히 설정하지 않으면 AfterInvocationManager 는 null.
    FilterSecurityInterceptor filterSecurityInterceptor;

    // 근거 자료를 통해 AccessDecisionManager 하위에 voter 들이 request 가 통과할 수 있는지 없는지 위원회를 소집해서 투표를 한다.
    // 이런 로직은 FilterSecurityInterceptor 와 MethodSecurityInterceptor 둘다 동일하다.
    // 권한 체크가 필터에 있냐 메소드에 있냐의 차이
    // 필터단에서 처리할 수 있으면 처리하는게 좋아보임 (디테일한 권한은 컨트롤러나 서비스 안에서 처리)
    AccessDecisionManager filterAccessDecisionManager(){
        return new AccessDecisionManager() {
            @Override
            public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
                throw new AccessDeniedException("접근 금지");
//                return;
            }

            @Override
            public boolean supports(ConfigAttribute attribute) {
                return true;
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return FilterInvocation.class.isAssignableFrom(clazz);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests( // 이런 권한 설정들이 FilterSecurityInterceptor에서 조사하는 것들
                        authority->authority
                                .mvcMatchers("/greeting").hasRole("USER")
                                .anyRequest().authenticated()
//                        .accessDecisionManager(filterAccessDecisionManager())
                )
                ;
    }

    // ExceptionTranslationFilter
    // 권한 오류 발생을 받아 처리하는 필터
    // AuthenticationException(401)과 AccessDeniedException(403)만 처리
    // AuthenticationException 는 AuthenticationEntryPoint 가 처리하고
    // 기본적으로 LoginUrlAuthenticationEntryPoint 이 구현되어 있고, commence 메소드를 통해 로그인 페이지로 redirect 하거나 메시지롤 보낸다.
    // AccessDeniedException 는 AccessDeniedHandler 가 처리한다.
    // 기본적으로 AccessDeniedHandlerImpl 에서 에러페이지를 핸들링 해준다.
    // 직접 우리가 구현해서 사용할 수도 있다.

}
