package com.fc.jpa.bookmanager.domain.listener;

import com.fc.jpa.bookmanager.domain.User;
import com.fc.jpa.bookmanager.domain.UserHistory;
import com.fc.jpa.bookmanager.repository.UserHistoryRepository;
import com.fc.jpa.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class UserEntityListener {
    // @PostLoad : select 이후 실행
//    @PrePersist
//    @PreUpdate
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) { // 감지된 오브젝트를 받음
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class); // 리스너 클래스는 자동 주입을 할 수 없어서 직접 불러와야함

        User user = (User) o;
        System.out.println("user : " + user);

        UserHistory userHistory = new UserHistory();
//        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user); // 연관관계는 참조하고 있어야 동작된다.

        userHistoryRepository.save(userHistory);
        user.getUserHistories().add(userHistory);
        System.out.println("userHistory : " + userHistory);
    }
}
