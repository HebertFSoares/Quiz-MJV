package com.quiz.mjv.dto;

public class AnswerDTO {
    private Long questionId;
    private String playerAnswer;
    private String nickname;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getPlayerAnswer() {
        return playerAnswer;
    }

    public void setPlayerAnswer(String playerAnswer) {
        this.playerAnswer = playerAnswer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AnswerDTO() {
    }

    public AnswerDTO(Long questionId, String playerAnswer, String nickname) {
        this.questionId = questionId;
        this.playerAnswer = playerAnswer;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "questionId=" + questionId +
                ", playerAnswer='" + playerAnswer + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
