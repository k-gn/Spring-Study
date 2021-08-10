package com.fc.jpa.bookmanager.service;

import com.fc.jpa.bookmanager.domain.TestEntity;
import com.fc.jpa.bookmanager.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestEntity save(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }

    @Transactional
    public TestEntity find(Long id) {
        TestEntity testEntity = testRepository.findById(id).orElse(null); // 여기서 영속성 끝나고 준영속으로 됨
        System.out.println("testEntity : " + testEntity);
        testEntity.getTestList().forEach(System.out::println); // 준영속에서 찾으려 해서 no Session
        return testEntity;
    }

    @Transactional
    public void findall() {
        System.out.println(testRepository.findAll());
    }
}
