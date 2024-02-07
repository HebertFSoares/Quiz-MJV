package com.quiz.mjv.controller;

import com.quiz.mjv.dto.QuestionDTO;

import com.quiz.mjv.entity.Question;
import com.quiz.mjv.service.QuestionService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/question")
public class QuestionController {
    @Autowired
    private final QuestionService questionService;
    

    @PostMapping
    public ResponseEntity<QuestionDTO> createdQuestion (@RequestBody QuestionDTO questionDTO) {
        QuestionDTO savedQuestionDTO = questionService.createdQuestion(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestionDTO);
    }

    @GetMapping("/random")
    public ResponseEntity<QuestionDTO> getRandomQuestion (){
        QuestionDTO randomQuestionDTO = questionService.getRandomQuestion();
        if(randomQuestionDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(randomQuestionDTO);
    }
}
