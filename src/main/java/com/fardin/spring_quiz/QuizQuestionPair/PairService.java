package com.keep.visual.QuizQuestionPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PairService {

    private final PairRepository pairRepository;

    @Autowired
    public PairService(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    public void addPair(Pair pair){
        pairRepository.save(pair);
    }

    public void deleteQuizPair(Long quizId){
        for(Pair pair : pairRepository.findByQuizId(quizId)){
            pairRepository.delete(pair);
        }
    }

    public void deleteQuestionPair(Long questionId){
        for(Pair pair : pairRepository.findByQuestionId(questionId)){
            pairRepository.delete(pair);
        }
    }

    public void deleteSpecificPair(Long quizId, Long questionId){
        for(Pair pair : pairRepository.findByQuizId(quizId)){
            if(pair.getQuestionId().equals(questionId)){
                pairRepository.delete(pair);
            }
        }

    }

    public boolean pairExists(Long quizId, Long questionId){
        for(Pair pair : pairRepository.findByQuizId(quizId)){
            if(pair.getQuestionId().equals(questionId)){
                return true;
            }
        }
        return false;
    }

    public List<Pair> getAllWithQuizId(Long quizId){
        return pairRepository.findByQuizId(quizId);
    }

    public List<Pair> getAllWithQuestionId(Long questionId){
        return pairRepository.findByQuestionId(questionId);
    }


}
