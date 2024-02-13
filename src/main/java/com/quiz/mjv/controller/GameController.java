package com.quiz.mjv.controller;

import com.quiz.mjv.dto.AnswerDTO;
import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/game")
public class GameController {
    private final QuestionService questionService;

    public GameController (QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/start")
    public ResponseEntity<QuestionDTO> startGame () {
        QuestionDTO firstQuestion = questionService.getRandomQuestion();
        if (firstQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(firstQuestion);
    }

    @PostMapping("/start")
    public ResponseEntity<String> submitAnswer (@RequestBody AnswerDTO answerDTO) {
        List<QuestionDTO> questionDTOList = questionService.getAllQuestions();

        boolean isAnswerCorrect = checkAnswer(answerDTO, questionDTOList);

        if (isAnswerCorrect) {
            return ResponseEntity.ok("Resposta correta! Avance para a próxima pergunta.");
        } else {
            return ResponseEntity.ok("Resposta incorreta! Tente novamente.");
        }
    }

    private boolean checkAnswer (AnswerDTO answerDTO, List<QuestionDTO> questionDTOList) {
        Long questionId = answerDTO.getQuestionId();
        String playerAnswer = answerDTO.getPlayerAnswer();

        for (QuestionDTO questionDTO : questionDTOList) {
            if (questionDTO.getId().equals(questionId)) {
                for (QuestionAlternativeDTO alternativeDTO : questionDTO.getQuestionAlternativeDTOS()) {
                    if (alternativeDTO.getReference().equalsIgnoreCase(playerAnswer)) {
                        return alternativeDTO.isCorrect();
                    }
                }
            }
        }
        
        return false;
    }



}




