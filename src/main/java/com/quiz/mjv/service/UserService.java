package com.quiz.mjv.service;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< Updated upstream
=======
import java.util.List;

>>>>>>> Stashed changes
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signup(User user){
        return userRepository.save(user);
    }
<<<<<<< Updated upstream
=======

    @Transactional
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

>>>>>>> Stashed changes
}
