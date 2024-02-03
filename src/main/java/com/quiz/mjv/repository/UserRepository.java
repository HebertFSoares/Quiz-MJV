package com.quiz.mjv.repository;

import com.quiz.mjv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}