package com.fardin.spring_quiz.Quizzes;


import com.fardin.spring_quiz.Questions.Question;
import com.fardin.spring_quiz.Questions.QuestionRepository;
import com.fardin.spring_quiz.QuizQuestionPair.Pair;
import com.fardin.spring_quiz.QuizQuestionPair.PairRepository;
import com.fardin.spring_quiz.QuizQuestionPair.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final PairRepository pairRepository;
    private final QuestionRepository questionRepository;
    private final PairService pairService;

    @Autowired
    public QuizService(QuizRepository quizRepository, PairRepository pairRepository, QuestionRepository questionRepository, PairService pairService) {
        this.pairRepository = pairRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.pairService = pairService;
    }

    public void addQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    public void deleteQuiz(Long quizId){
        if(!quizRepository.existsById(quizId)){
            throw new IllegalStateException("quiz with id " + quizId + " not found");
        }

        quizRepository.deleteById(quizId);
        pairService.deleteQuizPair(quizId);


    }

    public Quiz getQuizDetails(Long quizId){
        return quizRepository.findById(quizId).orElseThrow(() -> new IllegalStateException("quiz with id " + quizId + " does not exist" ));
    }

    public List<Question> getQuizQuestions(Long quizId){
        List<Pair> pair = pairRepository.findByQuizId(quizId);
        List<Question> questions = new ArrayList<Question>();

        for(Pair p : pair){
            Question q = questionRepository.findQuestionById(p.getQuestionId()).orElseThrow(() -> new IllegalStateException("question not found"));
            questions.add(q);
        }


        return questions;
    }

    @Transactional
    public void updateQuizBody(Long quizId, String newBody){
        Quiz q = quizRepository.findById(quizId).orElseThrow(() -> new IllegalStateException("Quiz with id " + quizId + " doesnt exist"));
        if(newBody == null){
            throw new IllegalStateException("Null is unacceptable value for title");
        }

        q.setTitle(newBody);
    }











}
