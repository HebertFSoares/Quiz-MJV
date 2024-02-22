package com.quiz.mjv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

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

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public QuestionAlternativeDTO() {
    }

    public QuestionAlternativeDTO(String alternative, Boolean isCorrect, String reference, Long questionId) {
        this.alternative = alternative;
        this.isCorrect = isCorrect;
        this.reference = reference;
        this.questionId = questionId;
    }

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
