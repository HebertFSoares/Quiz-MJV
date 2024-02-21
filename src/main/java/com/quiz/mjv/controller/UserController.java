package com.quiz.mjv.controller;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.Users;
import com.quiz.mjv.mapper.UserMapper;
import com.quiz.mjv.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.ErrorManager;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuário" ,responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso" ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado no sistema", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorManager.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorManager.class)))

    })
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> create (@Valid @RequestBody UserDTO createDTO){
        Users user = userService.signup(UserMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
    }

    @Operation(summary = "Obter todos os usuários", description = "Endpoint para obter todos os usuários cadastrados no sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser
            (){
        List<Users> users = userService.getAllUser();
        List<UserDTO> userDTOs = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
    }

}
