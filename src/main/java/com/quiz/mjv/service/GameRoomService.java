package com.quiz.mjv.service;

import com.quiz.mjv.dto.GameRoomDTO;
import com.quiz.mjv.entity.GameRoom;
import com.quiz.mjv.entity.User;
import com.quiz.mjv.exception.UserNotFoundException;
import com.quiz.mjv.repository.GameRoomRepository;
import com.quiz.mjv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameRoomService {
    private static final int DEFAULT_MAX_PLAYERS = 4;
    private final GameRoomRepository gameRoomRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public GameRoomService(GameRoomRepository gameRoomRepository, UserRepository userRepository, UserService userService) {
        this.gameRoomRepository = gameRoomRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public GameRoomDTO createGameRoom(String nickname, String email) {
        User user = userRepository.findByNicknameAndEmail(nickname, email);
        if (user == null) {
            throw new UserNotFoundException("Usuário não encontrado");
        }

        GameRoom gameRoom = new GameRoom(nickname, DEFAULT_MAX_PLAYERS);
        if (!gameRoom.getPlayers().contains(nickname)) {
            gameRoom.getPlayers().add(nickname);
        }
        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);
        return convertToDTO(savedGameRoom);
    }



    public void joinGameRoom(Long roomId, String nickname, String email) {
        GameRoom gameRoom = gameRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Sala não encontrada."));

        if (gameRoom.getPlayers().size() >= gameRoom.getMaxPlayers()) {
            throw new IllegalArgumentException("A sala já está cheia.");
        }

        if (gameRoom.getPlayers().contains(nickname)) {
            throw new IllegalArgumentException("Você já está na sala.");
        }

        User existingUser = userRepository.findByNicknameAndEmail(nickname, email);
        if (existingUser == null) {
            throw new IllegalArgumentException("Usuário não encontrados.");
        }

        gameRoom.getPlayers().add(nickname);
        gameRoomRepository.save(gameRoom);
    }



    public GameRoomDTO getGameRoomById(Long id) {
        GameRoom gameRoom = gameRoomRepository.findById(id).orElse(null);
        if (gameRoom != null) {
            return convertToDTO(gameRoom);
        } else {
            return null;
        }
    }



    private Long generateRandomRoomId() {
        Random random = new Random();
        return (long) (10000 + random.nextInt(90000));
    }

    public GameRoomDTO convertToDTO(GameRoom entity) {
        GameRoomDTO dto = new GameRoomDTO();
        dto.setId(entity.getRoomId().toString());
        dto.setMaxPlayers(entity.getMaxPlayers());

        List<String> playerNicknames = new ArrayList<>();
        for (String player : entity.getPlayers()) {
            playerNicknames.add(player);
        }
        dto.setPlayers(playerNicknames);

        return dto;
    }

}