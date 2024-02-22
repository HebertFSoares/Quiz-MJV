package com.quiz.mjv.repository;

import com.quiz.mjv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByNickname(String nickname);

    List<User> findAllByOrderByScoreDesc();
    User findByNicknameAndEmail(String nickname, String email);
}