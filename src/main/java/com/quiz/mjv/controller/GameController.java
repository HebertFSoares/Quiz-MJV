package com.quiz.mjv.controller;

import com.quiz.mjv.dto.AnswerDTO;
import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.repository.UserRepository;
import com.quiz.mjv.service.QuestionService;
import com.quiz.mjv.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final QuestionService questionService;
    private final UserService userService;

    public GameController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @Operation(summary = "Iniciar o jogo", description = "Endpoint para iniciar o jogo, retornando a primeira pergunta aleatória", responses = {
            @ApiResponse(responseCode = "200", description = "Pergunta obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma pergunta encontrada")
    })
    @GetMapping("/start")
    public ResponseEntity<QuestionDTO> startGame () {
        QuestionDTO firstQuestion = questionService.getRandomQuestion();
        if (firstQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(firstQuestion);
    }

    @Operation(summary = "Verificar resposta", description = "Endpoint para verificar a resposta de uma pergunta e atualizar o score do usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Resposta verificada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    @PostMapping("/check-response")
    public ResponseEntity<String> checkAnswer (@RequestBody AnswerDTO answerDTO) {
        List<QuestionDTO> questionDTOList = questionService.getAllQuestions();

        boolean isAnswerCorrect = checkAnswer(answerDTO, questionDTOList);

        String currentUserNickname = answerDTO.getNickname();
        UserDTO userDTO = userService.findByNickname(currentUserNickname);

        if (userDTO != null) {
            if (isAnswerCorrect) {
                userDTO.setScore(userDTO.getScore() + 10);
                userService.updateScore(userDTO);
                return ResponseEntity.ok("Resposta correta! Avance para a próxima pergunta.");
            } else {
                userDTO.setScore(userDTO.getScore() - 5);
                if (userDTO.getScore() < 0) {
                    userDTO.setScore(0);
                }
                userService.updateScore(userDTO);
                return ResponseEntity.ok("Resposta incorreta! Tente novamente.");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
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