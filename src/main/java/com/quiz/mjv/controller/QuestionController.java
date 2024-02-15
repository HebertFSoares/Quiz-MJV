package com.quiz.mjv.controller;

import com.quiz.mjv.dto.QuestionDTO;

import com.quiz.mjv.entity.Question;
import com.quiz.mjv.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Criar uma nova pergunta", description = "Endpoint para criar uma nova pergunta", responses = {
            @ApiResponse(responseCode = "201", description = "Pergunta criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class)))
    })
    @PostMapping
    public ResponseEntity<QuestionDTO> createdQuestion (@RequestBody QuestionDTO questionDTO) {
        QuestionDTO savedQuestionDTO = questionService.createdQuestion(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestionDTO);
    }

    @Operation(summary = "Obter uma pergunta aleatória", description = "Endpoint para obter uma pergunta aleatória do banco de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Pergunta aleatória retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma pergunta disponível no momento", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/random")
    public ResponseEntity<QuestionDTO> getRandomQuestion (){
        QuestionDTO randomQuestionDTO = questionService.getRandomQuestion();
        if(randomQuestionDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(randomQuestionDTO);
    }
<<<<<<< Updated upstream
=======

    @Operation(summary = "Obter uma pergunta por ID", description = "Endpoint para obter uma pergunta específica pelo seu ID", responses = {
            @ApiResponse(responseCode = "200", description = "Pergunta encontrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class)))
    })
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById (@PathVariable Long questionId) {
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(questionDTO);
    }

    @Operation(summary = "Atualizar uma pergunta", description = "Endpoint para atualizar uma pergunta existente pelo seu ID", responses = {
            @ApiResponse(responseCode = "200", description = "Pergunta atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class)))
    })
    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion (@PathVariable Long questionId, @RequestBody QuestionDTO questionDTO) {
        QuestionDTO updatedQuestionDTO = questionService.updateQuestion(questionId, questionDTO);
        return ResponseEntity.ok(updatedQuestionDTO);
    }

    @Operation(summary = "Deletar uma pergunta", description = "Endpoint para deletar uma pergunta pelo seu ID", responses = {
            @ApiResponse(responseCode = "200", description = "Pergunta deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion (@PathVariable Long questionId) {
        boolean deleted = questionService.deleteQuestion(questionId);
        if (deleted) {
            return ResponseEntity.ok().body("Question with ID " + questionId + " deleted successfully.");
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + questionId + " not found.");
        }
    }



>>>>>>> Stashed changes
}
