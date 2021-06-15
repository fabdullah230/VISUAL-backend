package com.fardin.spring_quiz.Quizzes;


import javax.persistence.*;

@Entity
@Table
public class Quiz {

    @Id
    @SequenceGenerator(
            name = "quiz_sequence",
            sequenceName = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quiz_sequence"
    )

    private Long id;
    private String title;

    public Quiz() {
    }

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz(Long id, String title) {
        this.id = id;
        this.title = title;
    }


    //boilerplate code
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
