package com.fc.jpa.bookmanager.domain.listener;

import com.fc.jpa.bookmanager.domain.User;
import com.fc.jpa.bookmanager.domain.UserHistory;
import com.fc.jpa.bookmanager.repository.UserHistoryRepository;
import com.fc.jpa.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// 리스너 클래스는 자동 주입을 할 수 없다.
public class UserEntityListener {
//    @PrePersist
//    @PreUpdate
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
//        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
