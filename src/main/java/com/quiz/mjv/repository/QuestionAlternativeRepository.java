package com.quiz.mjv.repository;

import com.quiz.mjv.entity.QuestionAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAlternativeRepository extends JpaRepository<QuestionAlternative, Long> {
}