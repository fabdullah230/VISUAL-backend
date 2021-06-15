package com.fardin.spring_quiz.QuizQuestionPair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {

    List<Pair> findByQuizId(Long quizId);
    List<Pair> findByQuestionId(Long questionId);

}
