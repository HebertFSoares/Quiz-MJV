package com.quiz.mjv.controller;

import com.quiz.mjv.dto.GameRoomDTO;
import com.quiz.mjv.service.GameRoomService;
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

    @PostMapping("/start")
    public ResponseEntity<GameRoomDTO> startGame (@RequestBody Map<String, String> requestData) {
        String nickname = requestData.get("nickname");
        String email = requestData.get("email");
        GameRoomDTO createdGameRoom = gameRoomService.createGameRoom(nickname, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGameRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameRoomDTO> getGameRoomById (@PathVariable Long id) {
        GameRoomDTO gameRoomDTO = gameRoomService.getGameRoomById(id);
        if (gameRoomDTO != null) {
            return ResponseEntity.ok(gameRoomDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/join/{id}")
    public ResponseEntity<String> joinGameRoom(@PathVariable Long id, @RequestBody Map<String, String> requestData) {
        String nickname = requestData.get("nickname");
        String email = requestData.get("email");
        try {
            gameRoomService.joinGameRoom(id, nickname, email);
            return ResponseEntity.ok("Joined the game room successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
