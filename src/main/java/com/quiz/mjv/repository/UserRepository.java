package com.quiz.mjv.repository;

import com.quiz.mjv.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    List<Users> findAllByOrderByScoreDesc();

    @Query("select u.role from Users u where u.email like :email")
    Users.Role findRoleByEmail(String email);
}
