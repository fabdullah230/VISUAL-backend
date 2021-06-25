package com.keep.visual.QuizQuestionPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "pair")
public class PairController {

    private final PairService pairService;
    private final PairRepository pairRepository;

    @Autowired
    public PairController(PairService pairService, PairRepository pairRepository) {
        this.pairService = pairService;
        this.pairRepository = pairRepository;
    }

    @GetMapping
    public List<Pair> getAll(){
        return pairRepository.findAll();
    }

    @GetMapping(path = "/question/{questionId}")
    public List<Pair> getAllWithQuestionId(@PathVariable("questionId") Long questionId){
        return pairService.getAllWithQuestionId(questionId);
    }

    @GetMapping(path = "/quiz/{quizId}")
    public List<Pair> getAllWithQuizId(@PathVariable("quizId") Long quizId){
        return pairService.getAllWithQuizId(quizId);
    }






}
