package com.quiz.mjv.service;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signup(User user){
        return userRepository.save(user);
    }
}
