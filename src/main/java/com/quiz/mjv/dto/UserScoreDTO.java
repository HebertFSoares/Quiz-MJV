package com.quiz.mjv.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserScoreDTO {
    private String nickname;
    private int score;
}
