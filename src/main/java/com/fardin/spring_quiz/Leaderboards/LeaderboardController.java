package com.fardin.spring_quiz.Leaderboards;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "leaderboards")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    private final LeaderboardRepository leaderboardRepository;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService, LeaderboardRepository leaderboardRepository) {
        this.leaderboardService = leaderboardService;
        this.leaderboardRepository = leaderboardRepository;
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

        //inefficient, but this allows updating the player score if already there thru the POST request
        List<Leaderboard> byPlayer = leaderboardRepository.findLeaderboardByPlayerId(leaderboard.getPlayerId());
        List<Leaderboard> byQuiz = leaderboardRepository.findLeaderboardByQuizId(leaderboard.getQuizId());

        for(Leaderboard i : byPlayer){
            for(Leaderboard j : byQuiz){
                if(i.getPlayerId().equals(leaderboard.getPlayerId()) && j.getQuizId().equals(leaderboard.getQuizId())){
                    leaderboardService.updateScore(leaderboard.getQuizId(), leaderboard.getPlayerId(), leaderboard.getScore());
                    return;
                }
            }
        }

         leaderboardService.addNewScore(leaderboard);
    }

    @GetMapping(path = "/get/{playerId}")
    public List<Long> quizDoneByPlayer(@PathVariable("playerId") Long playerId){
        List<Long> list = new ArrayList<Long>();
        List<Leaderboard> byPlayer = leaderboardRepository.findLeaderboardByPlayerId(playerId);

        for(Leaderboard l : byPlayer){
            list.add(l.getQuizId());
        }

        return list;

    }


}
