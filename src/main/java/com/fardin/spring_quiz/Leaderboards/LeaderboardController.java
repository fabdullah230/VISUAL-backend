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
                "/allscores for list of all players  | " +
                "/{playerId} for specific player score";
    }

    @GetMapping(path = "/allscores")
    public List<Leaderboard> allScores(){
        return leaderboardService.getAllScores();
    }

    @GetMapping(path = "{playerId}")
    public Leaderboard getSelectedScore(@PathVariable Long playerId){
        return leaderboardService.getSelectedScore(playerId);
    }

    @PostMapping
    public void addNewScore(@RequestBody Leaderboard leaderboard){
         leaderboardService.addNewScore(leaderboard);
    }

    @DeleteMapping(path = "{playerId}")
    public void deleteScore(@PathVariable Long playerId){
        leaderboardService.deleteScore(playerId);
    }

    //send raw int from frontend
    @PutMapping(path = "{playerId}")
    public void updateScore(@PathVariable Long playerId, @RequestBody int newScore){
        leaderboardService.updateScore(playerId, newScore);
    }




}
