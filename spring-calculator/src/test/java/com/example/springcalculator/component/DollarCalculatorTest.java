package com.example.springcalculator.component;

import com.example.springcalculator.dto.Req;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;


@SpringBootTest // 스프링 테스트 어노테이션, 모든 빈이 등록된다.
// 필요한 api를 import (add bean)
public class DollarCalculatorTest {

    // Spring Boot Container가 테스트 시에 필요하고, Bean이 Container에 존재한다면 @MockBean을 사용하고 아닌 경우에는 @Mock을 사용
    // @MockBean은 스프링 컨텍스트에 mock객체를 등록하게 되고 스프링 컨텍스트에 의해 @Autowired가 동작할 때 등록된 mock객체를 사용할 수 있도록 동작
    // spring 에선 bean 으로 관리하고 있기 때문에 Mocking 처리할 객체는 @MockBean 으로 선언
    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator dollarCalculator;

    @Test
    public void dollarCalculatorTest() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
        int sum = dollarCalculator.sum(10, 10);

        Assertions.assertEquals(60000, sum);
    }

}
