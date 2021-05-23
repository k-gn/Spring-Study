package com.example.springcalculator.component;

import com.example.springcalculator.dto.Req;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;


@SpringBootTest
// 필요한 api를 import (add bean)
//@Import({MarketApi.class, DollarCalculator.class})
public class DollarCalculatorTest {

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
