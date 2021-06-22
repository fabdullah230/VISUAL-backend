package com.fardin.spring_quiz.Questions;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String explanation;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

    private int timeLimit;
    private Long creatorId;

    //if we want to populate questions with sql queries, then the query will be
    //insert into question values(id, "correctAnswer", "incorrectOne", "incorrectThree", "incorrectTwo", "questionBody")
    //the sequence is weird because it was randomly created by spring hibernate


    public Question() {
    }

    public Question(Long id, String questionBody, String correctAnswer, String incorrectOne, String incorrectTwo, String incorrectThree, String explanation, int timeLimit, Long creatorId) {
        this.id = id;
        this.questionBody = questionBody;
        this.correctAnswer = correctAnswer;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;
        this.explanation = explanation;
        this.creatorId = creatorId;
        this.createdOn = LocalDateTime.now();
        this.lastUpdatedOn = LocalDateTime.now();
        this.timeLimit = timeLimit;
    }

    public Question(String questionBody, String correctAnswer, String incorrectOne, String incorrectTwo, String incorrectThree, String explanation, int timeLimit, Long creatorId) {
        this.questionBody = questionBody;
        this.correctAnswer = correctAnswer;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;
        this.explanation = explanation;
        this.creatorId = creatorId;
        this.timeLimit = timeLimit;
    }



    //boilerplate code

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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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
                ", explanation='" + explanation + '\'' +
                ", createdOn=" + createdOn +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", timeLimit=" + timeLimit +
                ", creatorId=" + creatorId +
                '}';
    }
}
