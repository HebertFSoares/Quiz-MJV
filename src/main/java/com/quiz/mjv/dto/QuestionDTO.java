package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String question, String response, String theme, ArrayList<QuestionAlternativeDTO> questionAlternativeDTOS) {
        this.id = id;
        this.question = question;
        this.response = response;
        this.theme = theme;
        this.questionAlternativeDTOS = questionAlternativeDTOS;
    }

    public ArrayList<QuestionAlternativeDTO> getQuestionAlternativeDTOS() {
        return questionAlternativeDTOS;
    }

    public void setQuestionAlternativeDTOS(ArrayList<QuestionAlternativeDTO> questionAlternativeDTOS) {
        this.questionAlternativeDTOS = questionAlternativeDTOS;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                ", theme='" + theme + '\'' +
                ", questionAlternativeDTOS=" + questionAlternativeDTOS +
                '}';
    }
}
