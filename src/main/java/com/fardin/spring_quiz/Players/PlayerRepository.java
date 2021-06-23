package com.fardin.spring_quiz.Players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerByEmail(String email);
    Optional<Player> findPlayerById(Long id);
}
