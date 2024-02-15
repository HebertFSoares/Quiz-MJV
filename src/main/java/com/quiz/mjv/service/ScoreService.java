package com.quiz.mjv.service;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private UserRepository userRepository;

    public void updateUserScore(User user, boolean isCorrect) {
        int currentScore = user.getScore();
        int pointsToAdd = isCorrect ? 10 : -5;

        int newScore = currentScore + pointsToAdd;
        if (newScore < 0) {
            newScore = 0;
        }

        user.setScore(newScore);
        userRepository.save(user);
    }
}