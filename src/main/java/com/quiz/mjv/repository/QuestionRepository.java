package com.quiz.mjv.repository;

import com.quiz.mjv.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByQuestion(String question);

    Question findFirstByOrderById();

}