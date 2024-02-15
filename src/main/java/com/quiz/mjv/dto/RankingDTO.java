package com.quiz.mjv.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RankingDTO {
    private String nickname;
    private int score;

    public RankingDTO(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }
}
