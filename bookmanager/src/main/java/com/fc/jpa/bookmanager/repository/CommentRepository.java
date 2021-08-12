package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
