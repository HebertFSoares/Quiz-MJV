package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionAlternativeDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("alternative")
    private String alternative;
    @JsonProperty("isCorrect")
    private Boolean isCorrect;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("questionID")
    private Long questionId;

    @Override
    public String toString() {
        return "QuestionAlternativeDTO{" +
                "id=" + id +
                ", alternative='" + alternative + '\'' +
                ", isCorrect=" + isCorrect +
                ", reference='" + reference + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
