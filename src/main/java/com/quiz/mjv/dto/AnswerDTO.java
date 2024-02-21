package com.quiz.mjv.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AnswerDTO {
    private Long questionId;
    private String playerAnswer;
    private String nickname;

    public Long getQuestionId() {
        return questionId;
    }

}
