package com.quiz.mjv.controller;

import com.quiz.mjv.dto.RankingDTO;
import com.quiz.mjv.entity.User;
import com.quiz.mjv.repository.UserRepository;
import com.quiz.mjv.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class ScoreController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public ScoreController(UserService userService,
                           UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Obter Ranking", description = "Endpoint para obter o ranking dos usuários", responses = {
            @ApiResponse(responseCode = "200", description = "Ranking obtido com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RankingDTO.class))))
    })
    @GetMapping
    public ResponseEntity<List<RankingDTO>> getRanking() {
        List<User> users = userRepository.findAllByOrderByScoreDesc();
        List<RankingDTO> rankingDTOs = new ArrayList<>();
        for (User user : users) {
            rankingDTOs.add(new RankingDTO(user.getNickname(), user.getScore()));
        }
        return ResponseEntity.ok(rankingDTOs);
    }
}
