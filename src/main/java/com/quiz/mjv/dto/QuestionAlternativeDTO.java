package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionAlternativeDTO {
    @NotBlank
    @JsonProperty("alternative")
    private String alternative;
    @NotBlank
    @JsonProperty("isCorrect")
    private Boolean isCorrect;
    @NotBlank
    @JsonProperty("reference")
    private String reference;
    @NotBlank
    @JsonProperty("questionID")
    private Long questionId;

    @Override
    public String toString() {
        return "QuestionAlternativeDTO{" +
                ", alternative='" + alternative + '\'' +
                ", isCorrect=" + isCorrect +
                ", reference='" + reference + '\'' +
                ", questionId=" + questionId +
                '}';
    }
    public boolean isCorrect() {
        return this.isCorrect;
    }

}
