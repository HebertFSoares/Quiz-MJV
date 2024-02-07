package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("question")
    private String question;
    @JsonProperty("response")
    private String response;
    @JsonProperty("theme")
    private String theme;
    @JsonProperty("questionAlternativeDTO")
    private ArrayList<QuestionAlternativeDTO> questionAlternativeDTOS = new ArrayList<>();

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                ", theme='" + theme + '\'' +
                ", questionAlternativeDTOS=" + questionAlternativeDTOS +
                '}';
    }
}
