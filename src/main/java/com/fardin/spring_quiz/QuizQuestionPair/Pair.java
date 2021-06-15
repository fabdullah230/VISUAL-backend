package com.fardin.spring_quiz.QuizQuestionPair;

import javax.persistence.*;

@Entity
@Table
public class Pair {


    @Id
    @SequenceGenerator(
            name = "pair_sequence",
            sequenceName = "pair_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pair_sequence"
    )

    private Long id;

    private Long questionId; //references question.id
    private Long quizId; //references quiz.id


    public Pair() {
    }

    public Pair(Long questionId, Long quizId) {
        this.questionId = questionId;
        this.quizId = quizId;
    }

    public Pair(Long id, Long questionId, Long quizId) {
        this.id = id;
        this.questionId = questionId;
        this.quizId = quizId;
    }

    //boilerplate code

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", quizId=" + quizId +
                '}';
    }
}
