package com.fardin.spring_quiz.Questions;

import com.fardin.spring_quiz.QuizQuestionPair.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final PairService pairService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, PairService pairService) {
        this.pairService = pairService;
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question){
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long questionId){
        if(!questionRepository.existsById(questionId)){
            throw new IllegalStateException("question with id " + questionId + " not found");
        }

        questionRepository.deleteById(questionId);
        pairService.deleteQuestionPair(questionId);
    }

    public Question getSelectedQuestion(Long questionId){
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalStateException("question with id " + questionId + " doesnt exist"));
    }

    @Transactional
    public void updateQuestionBody(Long questionId, String newBody){
        if(!questionRepository.existsById(questionId)){
            throw new IllegalStateException("Question with id " + questionId + " does not exist");
        }
        Question q = questionRepository.findById(questionId).orElseThrow(() -> new IllegalStateException("Question with id " + questionId + " doesnt exist"));
        if(newBody == null){
            throw new IllegalStateException("Null is unacceptable value for questionBody");
        }


        q.setQuestionBody(newBody);

    }

    @Transactional
    public void updateCorrectAnswer(Long questionId, String newAnswer){
        if(!questionRepository.existsById(questionId)){
            throw new IllegalStateException("Question with id " + questionId + " does not exist");
        }
        Question q = questionRepository.findById(questionId).orElseThrow(() -> new IllegalStateException("question with id " + questionId + " doesnt exist"));
        if(newAnswer == null){
            throw new IllegalStateException("Null is unacceptable value for correctAnswer");
        }

        q.setCorrectAnswer(newAnswer);

    }

    @Transactional
    public void updateIncorrectAnswers(Long questionId, String newIncorrectOne, String newIncorrectTwo, String newIncorrectThree){
        if(!questionRepository.existsById(questionId)){
            throw new IllegalStateException("Question with id " + questionId + " does not exist");
        }
        Question q = questionRepository.findById(questionId).orElseThrow(() -> new IllegalStateException("question with id " + questionId + " doesnt exist"));

        if(newIncorrectOne != null) {
            q.setIncorrectOne(newIncorrectOne);
        }
        if(newIncorrectTwo != null) {
            q.setIncorrectTwo(newIncorrectTwo);
        }
        if(newIncorrectThree != null) {
            q.setIncorrectThree(newIncorrectThree);
        }

    }




}
