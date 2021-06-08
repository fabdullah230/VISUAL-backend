package com.fardin.spring_quiz.Leaderboards;


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

    public Leaderboard() {
    }

    public Leaderboard(Long id, String name, Long playerId, int score) {
        this.id = id;
        this.name = name;
        this.playerId = playerId;
        this.score = score;
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



    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
