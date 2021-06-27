package com.keep.visual.Leaderboards;


import javax.persistence.*;

@Entity
@Table
public class Leaderboard {

    @Id
    @SequenceGenerator(
            name = "leaderboard_sequence",
            sequenceName = "leaderboard_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "leaderboard_sequence"
    )


    private Long id;
    private String name;
    private Long playerId;
    private int score;
    private Long quizId;
    //questions attempted
    private int questionsAttempted = 1;

    public Leaderboard() {
    }

    public Leaderboard(Long id, String name, Long playerId, int score, Long quizId, int questionsAttempted) {
        this.id = id;
        this.name = name;
        this.playerId = playerId;
        this.score = score;
        this.quizId = quizId;
        this.questionsAttempted = 1;
    }

    public Leaderboard(String name, Long playerId, int score, Long quizId) {
        this.quizId = quizId;
        this.name = name;
        this.playerId = playerId;
        this.score = score;
        this.questionsAttempted = 1;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public int getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(int questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
