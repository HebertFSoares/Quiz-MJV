package com.quiz.mjv.controller;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.User;
import com.quiz.mjv.mapper.UserMapper;
import com.quiz.mjv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> create (@RequestBody UserDTO newUserDTO){
        User newUser = userMapper.toEntity(newUserDTO);
        User createdUser = userService.signup(newUser);
        UserDTO createdUserDTO = userMapper.toDTO(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<User> users = userService.getAllUser();
        List<UserDTO> userDTOs = users.stream().map(userMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
    }

}
