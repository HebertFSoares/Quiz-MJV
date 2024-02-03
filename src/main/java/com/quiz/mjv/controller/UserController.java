package com.quiz.mjv.controller;

import com.quiz.mjv.entity.User;
import com.quiz.mjv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create (@RequestBody User newUser){
        User createdUser  = userService.signup(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
