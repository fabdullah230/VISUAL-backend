package com.fardin.spring_quiz.Leaderboards;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "leaderboards")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public String leaderboardsPage(){
        return "these are the links to leaderboard pages: " +
                "/all for list of all players  | " +
                "/{playerId} for specific player score";
    }

    @GetMapping(path = "/all")
    public List<Leaderboard> allScores(){
        return leaderboardService.getAllScores();
    }



    @PostMapping
    public void addNewScore(@RequestBody Leaderboard leaderboard){
         leaderboardService.addNewScore(leaderboard);
    }


}
