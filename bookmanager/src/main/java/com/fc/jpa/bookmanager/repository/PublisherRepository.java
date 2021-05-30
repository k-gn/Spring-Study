package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Publisher;
import com.fc.jpa.bookmanager.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
