package com.keep.visual.Players;


import javax.persistence.*;

@Entity
@Table
public class Player {

    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )


    private Long id;
    private String name;
    private String email;
    private Long keepId;

    //sql query to insert is insert into players values (id, "email", "name")

    public Player(){

    }

    public Player(Long id, String name, String email, Long keepId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.keepId = keepId;
    }

    public Player(String name, String email, Long keepId) {
        this.name = name;
        this.email = email;
        this.keepId = keepId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Long getKeepId() {
        return keepId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", keepId=" + keepId +
                '}';
    }
}
