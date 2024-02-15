package com.quiz.mjv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @Operation(summary = "Ping Pong", description = "Endpoint para verificar se o servidor está online", responses = {
            @ApiResponse(responseCode = "200", description = "Servidor está online", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @GetMapping
    public String ping(){
        return "pong";
    }
}
