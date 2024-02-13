package com.quiz.mjv.controller;

import com.quiz.mjv.dto.AnswerDTO;
import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.User;
import com.quiz.mjv.entity.UserScore;
import com.quiz.mjv.repository.UserRepository;
import com.quiz.mjv.service.QuestionService;
import com.quiz.mjv.service.UserScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/game")
public class GameController {
    private final QuestionService questionService;
    private final UserRepository userRepository;

    private final UserScoreService userScoreService;

    public GameController (QuestionService questionService,
                           UserRepository userRepository,
                           UserScoreService userScoreService) {
        this.questionService = questionService;
        this.userRepository = userRepository;
        this.userScoreService = userScoreService;
    }

    @GetMapping("/start")
    public ResponseEntity<QuestionDTO> startGame () {
        QuestionDTO firstQuestion = questionService.getRandomQuestion();
        if (firstQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(firstQuestion);
    }

    @PostMapping("/answer")
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerDTO answerDTO, @RequestParam Long userId, @RequestParam boolean correctAnswer) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        int scoreChange = correctAnswer ? 10 : -5;
        userScoreService.updateScore(user, scoreChange);

        return ResponseEntity.ok("Pontuação atualizada com sucesso.");
    }

    @GetMapping("/top-scores")
    public ResponseEntity<List<UserScore>> getTopScores(@RequestParam int count) {
        List<UserScore> topScores = userScoreService.getTopScores(count);
        return ResponseEntity.ok(topScores);
    }

    @PostMapping("/check-answer")
    public ResponseEntity<String> checkAnswer(@RequestBody AnswerDTO answerDTO) {
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