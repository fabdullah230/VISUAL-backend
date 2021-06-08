package com.fardin.spring_quiz.Questions;


import javax.persistence.*;

@Entity
@Table
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )

    private Long id;
    private String questionBody;
    private String correctAnswer;
    private String incorrectOne;
    private String incorrectTwo;
    private String incorrectThree;

    //if we want to populate questions with sql queries, then the query will be
    //insert into question values(id, "correctAnswer", "incorrectOne", "incorrectThree", "incorrectTwo", "questionBody")
    //the sequence is weird because it was randomly created by spring hibernate


    public Question() {
    }

    public Question(Long id, String questionBody, String correctAnswer, String incorrectOne, String incorrectTwo, String incorrectThree) {
        this.id = id;
        this.questionBody = questionBody;
        this.correctAnswer = correctAnswer;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;
    }

    public Question(String questionBody, String correctAnswer, String incorrectOne, String incorrectTwo, String incorrectThree) {
        this.questionBody = questionBody;
        this.correctAnswer = correctAnswer;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectOne() {
        return incorrectOne;
    }

    public void setIncorrectOne(String incorrectOne) {
        this.incorrectOne = incorrectOne;
    }

    public String getIncorrectTwo() {
        return incorrectTwo;
    }

    public void setIncorrectTwo(String incorrectTwo) {
        this.incorrectTwo = incorrectTwo;
    }

    public String getIncorrectThree() {
        return incorrectThree;
    }

    public void setIncorrectThree(String incorrectThree) {
        this.incorrectThree = incorrectThree;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionBody='" + questionBody + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectOne='" + incorrectOne + '\'' +
                ", incorrectTwo='" + incorrectTwo + '\'' +
                ", incorrectThree='" + incorrectThree + '\'' +
                '}';
    }
}
