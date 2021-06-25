package com.keep.visual.Players;


import com.keep.visual.Leaderboards.Leaderboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;


    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;

    }

    @GetMapping
    public String playerPage(){
        return "these are the links to player pages: " +
                "/allplayers for list of all players  | " +
                "/{playerId} for specific player";
    }

    @GetMapping(path = "{playerId}")
    public Player getSelected(@PathVariable("playerId") Long playerId){
        return playerService.getSelectedPlayer(playerId);
    }

    @GetMapping(path = "/allplayers")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PostMapping
    public void addPlayer(@RequestBody Player player){
        playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long playerId){
        playerService.deletePlayer(playerId);
    }

    //send raw text from frontend
    @PutMapping(path = "{playerId}")
    public void updatePlayerName(@PathVariable("playerId") Long playerId, @RequestBody String name){
        playerService.updatePlayerName(playerId, name);
    }

    //getmapping to get ALL scores
    @GetMapping(path = "{playerId}/scores")
    public List<Leaderboard> playerScores(@PathVariable("playerId") Long playerId){
        return playerService.allScores(playerId);
    }






}
