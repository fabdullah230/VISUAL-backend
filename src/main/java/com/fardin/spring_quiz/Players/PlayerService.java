package com.fardin.spring_quiz.Players;

import com.fardin.spring_quiz.Leaderboards.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final LeaderboardService leaderboardService;

    //dependency injection
    @Autowired
    public PlayerService(PlayerRepository playerRepository, LeaderboardService leaderboardService) {
        this.playerRepository = playerRepository;
        this.leaderboardService = leaderboardService;
    }


    //adding new players but validating email duplicacy
    public void addNewPlayer(Player player){
        Optional<Player> playerOptional = playerRepository.findPlayerByEmail(player.getEmail());

        if(playerOptional.isPresent()){
            throw new IllegalStateException("Email already in use");
        }

        playerRepository.save(player);

    }

    //deleting existing player by id
    public void deletePlayer(Long playerId) {
        if(!playerRepository.existsById(playerId)){
            throw new IllegalStateException("student with id " + playerId + " not found");
        }

        playerRepository.deleteById(playerId);
    }

    //return all players
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    //specific player
    public Player getSelectedPlayer(Long playerId){
        Player p = playerRepository.findById(playerId).orElseThrow(() -> new IllegalStateException("player with id " + playerId + " doesnt exist"));
        return p;
    }

    //update player name
    @Transactional
    public void updatePlayerName(Long playerId, String name){
        if(!playerRepository.existsById(playerId)){
            throw new IllegalStateException("Player with id " + playerId + " does not exist");
        }
        Player p = playerRepository.findById(playerId).orElseThrow(() -> new IllegalStateException("player with id " + playerId + " doesnt exist"));
        if(name == null){
            throw new IllegalStateException("Null is unacceptable value for name");
        }



        p.setName(name);
        leaderboardService.updateName(playerId, name);

    }



}
