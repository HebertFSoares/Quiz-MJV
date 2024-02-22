package com.quiz.mjv.service;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String nickname, String password) {
        User user = userRepository.findByNickname(nickname);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
