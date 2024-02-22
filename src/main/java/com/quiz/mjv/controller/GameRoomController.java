package com.quiz.mjv.controller;

import com.quiz.mjv.dto.GameRoomDTO;
import com.quiz.mjv.service.GameRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/game")
@RestController
public class GameRoomController {
    private final GameRoomService gameRoomService;

    @Autowired
    public GameRoomController(GameRoomService gameRoomService) {
        this.gameRoomService = gameRoomService;
    }

    @Operation(summary = "Iniciar sala de jogo", description = "Endpoint para iniciar uma nova sala de jogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala de jogo criada com sucesso")
    })
    @PostMapping("/start")
    public ResponseEntity<GameRoomDTO> startGame (@RequestBody Map<String, String> requestData) {
        String nickname = requestData.get("nickname");
        String email = requestData.get("email");
        GameRoomDTO createdGameRoom = gameRoomService.createGameRoom(nickname, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGameRoom);
    }

    @Operation(summary = "Obter sala de jogo por ID", description = "Endpoint para obter uma sala de jogo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala de jogo encontrada"),
            @ApiResponse(responseCode = "404", description = "Sala de jogo não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GameRoomDTO> getGameRoomById (@PathVariable Long id) {
        GameRoomDTO gameRoomDTO = gameRoomService.getGameRoomById(id);
        if (gameRoomDTO != null) {
            return ResponseEntity.ok(gameRoomDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Participar de uma sala de jogo", description = "Endpoint para participar de uma sala de jogo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrou na sala de jogo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    })
    @PostMapping("/join/{id}")
    public ResponseEntity<String> joinGameRoom(@PathVariable Long id, @RequestBody Map<String, String> requestData) {
        String nickname = requestData.get("nickname");
        String email = requestData.get("email");
        try {
            gameRoomService.joinGameRoom(id, nickname, email);
            return ResponseEntity.ok("Entrou na sala com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
