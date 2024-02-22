package com.quiz.mjv.entity;

import com.quiz.mjv.dto.AnswerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nickname", nullable = false, unique = true, length = 40)
    private String nickname;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "score", nullable = false)
    private int score;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> userAnswers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_room_id")
    private GameRoom gameRoom;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, email, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

    public User(Long id, String nickname, String email, String password, int score, List<Answer> userAnswers, GameRoom gameRoom) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.score = score;
        this.userAnswers = userAnswers;
        this.gameRoom = gameRoom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '}';
    }
}
