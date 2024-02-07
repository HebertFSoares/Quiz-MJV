package com.quiz.mjv.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "QuestionAlternatives")
public class QuestionAlternative implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "alternative", nullable = false, length = 250)
    private String alternative;
    @Column(name = "isCorrect", nullable = false, length = 6)
    private Boolean isCorrect;
    @Column(name = "reference", nullable = false, length = 80)
    private String reference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAlternative that = (QuestionAlternative) o;
        return Objects.equals(id, that.id) && Objects.equals(alternative, that.alternative) && Objects.equals(isCorrect, that.isCorrect) && Objects.equals(reference, that.reference) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alternative, isCorrect, reference, question);
    }

}
