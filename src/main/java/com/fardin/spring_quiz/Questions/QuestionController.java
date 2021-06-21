package com.fardin.spring_quiz.Questions;


import com.fardin.spring_quiz.Players.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String questionPage(){
        return "these are the links to Question pages: " +
                "/all for list of all Questions  | " +
                "/{questionId} for specific question";
    }

    @GetMapping(path = "{questionId}")
    public Question getSelected(@PathVariable("questionId") Long questionId){
        return questionService.getSelectedQuestion(questionId);
    }

    @GetMapping(path = "/all")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping
    public void addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
    }

    @DeleteMapping(path = "{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId){
        questionService.deleteQuestion(questionId);
    }

    @PutMapping(path = "{questionId}")
    public void updateQuestionBody(@PathVariable Long questionId,
                                   @RequestParam(required = false) String questionBody){
        questionService.updateQuestionBody(questionId, questionBody);
    }

    //send formdata with key questionBody
    @PutMapping(path = "{questionId}/corans")
    public void updateCorrectAnswer(@PathVariable Long questionId,
                                    @RequestParam(required = false) String correctAnswer){
        questionService.updateCorrectAnswer(questionId, correctAnswer);
    }

    //send formdata with keys
    @PutMapping(path = "{questionId}/incans")
    public void updateIncorrectAnswers(@PathVariable Long questionId,
                                       @RequestParam(required = false) String incorrectOne,
                                       @RequestParam(required = false) String incorrectTwo,
                                       @RequestParam(required = false) String incorrectThree){
        questionService.updateIncorrectAnswers(questionId, incorrectOne, incorrectTwo, incorrectThree );
    }



}
