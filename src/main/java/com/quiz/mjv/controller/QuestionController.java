package com.quiz.mjv.controller;

import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.service.QuestionService;
import lombok.AllArgsConstructor;
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


    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById (@PathVariable Long questionId) {
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(questionDTO);
    }

    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion (@PathVariable Long questionId, @RequestBody QuestionDTO questionDTO) {
        QuestionDTO updatedQuestionDTO = questionService.updateQuestion(questionId, questionDTO);
        return ResponseEntity.ok(updatedQuestionDTO);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion (@PathVariable Long questionId) {
        boolean deleted = questionService.deleteQuestion(questionId);
        if (deleted) {
            return ResponseEntity.ok().body("Question with ID " + questionId + " deleted successfully.");
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + questionId + " not found.");
        }
    }



}
