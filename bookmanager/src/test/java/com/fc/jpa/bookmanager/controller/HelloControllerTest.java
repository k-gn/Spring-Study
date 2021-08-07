package com.fc.jpa.bookmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 현재 @EnableJpaAuditing이 해당 클래스에 등록되어 있어서 모든 테스트들이 항상 JPA 관련 Bean들을 필요로 하고 있는 상태
// @WebMvcTest같은 슬라이스 테스트는 JPA 관련 Bean들을 로드하지 않기 때문에 에러가 발생
@WebMvcTest
//@MockBean(JpaMetamodelMappingContext.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("hello world!"));
    }
}