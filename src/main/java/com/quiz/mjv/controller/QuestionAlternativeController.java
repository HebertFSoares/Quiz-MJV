package com.quiz.mjv.controller;

import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.mapper.QuestionAlternativeMapper;
import com.quiz.mjv.service.QuestionAlternativeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class QuestionAlternativeController {
    @Autowired
    private QuestionAlternativeService questionAlternativeService;

    @Autowired
    private final QuestionAlternativeMapper questionAlternativeMapper;

    @Operation(summary = "Criar alternativas para uma pergunta", description = "Endpoint para criar alternativas para uma pergunta específica", responses = {
            @ApiResponse(responseCode = "201", description = "Alternativas criadas com sucesso")
    })
    @PostMapping("/question/{questionId}/alternatives")
    public ResponseEntity<Void> createQuestionAlternatives (@PathVariable Long questionId, @RequestBody List<QuestionAlternativeDTO> alternatives) {
        for (QuestionAlternativeDTO alternativeDTO : alternatives) {
            alternativeDTO.setQuestionId(questionId);
            questionAlternativeService.createdQuestionAlternative(questionAlternativeMapper.toEntity(alternativeDTO));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
