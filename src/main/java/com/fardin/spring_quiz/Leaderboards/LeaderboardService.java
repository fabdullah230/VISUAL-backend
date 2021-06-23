package com.fardin.spring_quiz.Leaderboards;

import com.fardin.spring_quiz.Players.Player;
import com.fardin.spring_quiz.Players.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository, PlayerRepository playerRepository) {
        this.leaderboardRepository = leaderboardRepository;
        this.playerRepository = playerRepository;
    }

    public void addNewScore(Leaderboard leaderboard){
        Optional<Player> player = playerRepository.findPlayerById(leaderboard.getPlayerId());
        if(player.isEmpty()){
            throw new IllegalStateException("player with id "+ leaderboard.getPlayerId() + " does not exist");
        }

        List<Leaderboard> leaderboards = leaderboardRepository.findLeaderboardByQuizId(leaderboard.getQuizId());
        for(Leaderboard l : leaderboards){
            if(l.getPlayerId().equals(leaderboard.getPlayerId())){
//              throw new IllegalStateException("player already exists in quiz leaderboards");
            }
        }

        leaderboardRepository.save(leaderboard);
    }

    //method to delete a particular score from quiz leaderboard, but pretty useless therefore not used
    public void deleteScore(Long quizId, Long playerId){
        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByQuizId(quizId);
        for(Leaderboard l : leaderboard){
            if(l.getPlayerId().equals(playerId)){
                leaderboardRepository.delete(l);
            }
        }
    }


    //to be used with deleteplayer
    public void deleteAllWithPlayerId(Long playerId){
        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByPlayerId(playerId);
        for(Leaderboard l : leaderboard){
            leaderboardRepository.delete(l);
        }
    }

    //to be used with delete quiz
    public void deleteAllWithQuizId(Long quizId){
        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByPlayerId(quizId);
        for(Leaderboard l : leaderboard){
            leaderboardRepository.delete(l);
        }
    }




    public List<Leaderboard> getAllScores(){
        return leaderboardRepository.findAll();
    }

    public List<Leaderboard> getAllWithPlayerId(Long playerId){
        return leaderboardRepository.findLeaderboardByPlayerId(playerId);
    }

    public List<Leaderboard> getAllWithQuizId(Long quizId){
        return leaderboardRepository.findLeaderboardByQuizId(quizId);
    }




    public Leaderboard getSelectedScore(Long quizId, Long playerId){
        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByQuizId(quizId);
        for(Leaderboard l : leaderboard){
            if(l.getPlayerId().equals(playerId)){
                return l;
            }
        }
        return null;
    }
    
    public boolean checkIfPlayerScoreExists(Long playerId){
        List<Leaderboard> l = leaderboardRepository.findLeaderboardByPlayerId(playerId);

        return !l.isEmpty();
    }


    @Transactional
    public void updateScore(Long quizId, Long playerId, Integer newScore){

        if(newScore == null){
            throw new IllegalStateException("Null is unacceptable value for newScore");
        }

        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByQuizId(quizId);

        for(Leaderboard l : leaderboard){
            if(l.getPlayerId().equals(playerId)){
                l.setScore(newScore);
                l.setQuestionsAttempted(l.getQuestionsAttempted()+1);
                break;
            }
        }

    }

    @Transactional
    public void updateName(Long playerId, String newName){

        if(newName == null){
            throw new IllegalStateException("Null is unacceptable value for newScore");
        }

        List<Leaderboard> leaderboard = leaderboardRepository.findLeaderboardByPlayerId(playerId);
        for(Leaderboard l : leaderboard){
                l.setName(newName);
        }

    }








}
