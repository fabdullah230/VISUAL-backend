package com.fardin.spring_quiz.Leaderboards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    List<Leaderboard> findLeaderboardByPlayerId(Long playerId);
    List<Leaderboard> findLeaderboardByQuizId(Long quizId);
}
