package com.quiz.mjv.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "question", nullable = false, unique = true, length = 200)
    private String question;
    @Column(name = "response", nullable = false, length = 150)
    private String response;
    @Column(name = "theme", nullable = false, length = 50)
    private String theme;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionAlternative> questionAlternatives;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(response, question1.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, response);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
