package com.quiz.mjv.service;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.User;
import com.quiz.mjv.exception.UsernameUniqueViolationException;
import com.quiz.mjv.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User signup(User user){
        try {
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException exception){
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", user.getNickname()));
        }

    }
    @Transactional
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Transactional
    public UserDTO findByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user != null) {
            return mapUserToDTO(user);
        } else {
            return null;
        }
    }

    @Transactional
    public void updateScore(UserDTO userDTO) {
        User user = userRepository.findByNickname(userDTO.getNickname());
        if (user != null) {
            user.setScore(userDTO.getScore());
            userRepository.save(user);
        }
    }

    private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setScore(user.getScore());
        return userDTO;
    }
}
