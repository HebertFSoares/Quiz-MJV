package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @JsonProperty("question")
    private String question;
    @NotBlank
    @JsonProperty("response")
    private String response;
    @NotBlank
    @JsonProperty("theme")
    private String theme;
    @JsonProperty("questionAlternativeDTO")
    private ArrayList<QuestionAlternativeDTO> questionAlternativeDTOS = new ArrayList<>();

    @Override
    public String toString() {
        return "QuestionDTO{" +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                ", theme='" + theme + '\'' +
                ", questionAlternativeDTOS=" + questionAlternativeDTOS +
                '}';
    }


    public ArrayList<QuestionAlternativeDTO> getQuestionAlternativeDTOS() {
        return questionAlternativeDTOS;
    }

    public void setQuestionAlternativeDTOS(ArrayList<QuestionAlternativeDTO> questionAlternativeDTOS) {
        this.questionAlternativeDTOS = questionAlternativeDTOS;
    }
}
