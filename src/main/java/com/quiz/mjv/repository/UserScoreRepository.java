package com.quiz.mjv.repository;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    UserScore findByUser(User user);
    List<UserScore> findTopNOrderByScoreDesc(int n);
}
