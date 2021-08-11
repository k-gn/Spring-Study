package com.fc.jpa.bookmanager.domain.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepoTest {

    @Autowired
    private MemberRepo memberRepo;

    @Test
    void test() {
        System.out.println(memberRepo.findAll());
    }
}