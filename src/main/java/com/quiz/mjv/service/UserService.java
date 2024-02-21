package com.quiz.mjv.service;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.Users;
import com.quiz.mjv.exception.EntityNotFoundException;
import com.quiz.mjv.exception.UsernameUniqueViolationException;
import com.quiz.mjv.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Users signup(Users user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Email '%s' já cadastrado", user.getEmail()));
        }

    }
    @Transactional
    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    @Transactional
    public UserDTO findByNickname(String nickname) {
        Optional<Users> userOptional = userRepository.findByEmail(nickname);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return mapUserToDTO(user);
        } else {
            return null;
        }
    }

    @Transactional
    public void updateScore(UserDTO userDTO) {
        Optional<Users> userOptional = userRepository.findByEmail(userDTO.getNickname());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setScore(userDTO.getScore());
            userRepository.save(user);
        }
    }

    private UserDTO mapUserToDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setScore(user.getScore());
        return userDTO;
    }
    @Transactional
    public Users buscarPorUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", email))
        );
    }

    @Transactional
    public Users.Role buscarRolePorUsername(String email) {
        return userRepository.findRoleByEmail(email);
    }
}
