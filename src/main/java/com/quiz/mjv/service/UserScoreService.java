package com.quiz.mjv.service;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.entity.UserScore;
import com.quiz.mjv.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserScoreService {
    @Autowired
    private UserScoreRepository userScoreRepository;

    public void updateScore (User user, int scoreChange) {
        if (user != null) {
            UserScore userScore = userScoreRepository.findByUser(user);
            if (userScore == null) {
                userScore = new UserScore();
                userScore.setUser(user);
            }
            userScore.setScore(userScore.getScore() + scoreChange);
            userScoreRepository.save(userScore);
        } else {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
    }

    public List<UserScore> getTopScores(int count) {
        return userScoreRepository.findTopNOrderByScoreDesc(count);
    }
}