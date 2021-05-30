package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
