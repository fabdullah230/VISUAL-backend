package com.fardin.spring_quiz.Quizzes;

import com.fardin.spring_quiz.Leaderboards.Leaderboard;
import com.fardin.spring_quiz.Leaderboards.LeaderboardService;
import com.fardin.spring_quiz.Questions.Question;
import com.fardin.spring_quiz.Questions.QuestionRepository;
import com.fardin.spring_quiz.Questions.QuestionService;
import com.fardin.spring_quiz.QuizQuestionPair.Pair;
import com.fardin.spring_quiz.QuizQuestionPair.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "quiz")
public class QuizController {

    private final QuizService quizService;
    private final PairService pairService;
    private final LeaderboardService leaderboardService;

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizController(LeaderboardService leaderboardService, QuizService quizService, PairService pairService, QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizService = quizService;
        this.pairService = pairService;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public String quizPage(){
        return "You have arrived at the Quiz Page";
    }

    @GetMapping(path = "/all")
    public List<Quiz> allQuiz(){
        return quizService.getAllQuiz();
    }

    @GetMapping(path = "{quizId}")
    public Quiz getSelected(@PathVariable("quizId") Long quizId){
        return quizService.getQuizDetails(quizId);
    }

    @GetMapping(path = "{quizId}/all")
    public List<Question> getAllQuestions(@PathVariable("quizId") Long quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @GetMapping(path = "{quizId}/leaderboards")
    public List<Leaderboard> getQuizLeaderboards(@PathVariable("quizId") Long quizId){
        return leaderboardService.getAllWithQuizId(quizId);
    }

    @PostMapping
    public void addQuiz(@RequestBody Quiz quiz){
        quizService.addQuiz(quiz);
    }

    @DeleteMapping(path = "{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId){
        quizService.deleteQuiz(quizId);
        leaderboardService.deleteAllWithQuizId(quizId);
    }

    @DeleteMapping(path = "{quizId}/{questionId}")
    public void deletePair(@PathVariable("quizId") Long quizId, @PathVariable("questionId") Long questionId){
        pairService.deleteSpecificPair(quizId, questionId);
    }

    @PostMapping(path = "/newpair")
    public void addPair(@RequestBody Pair pair){
        Long quizId = pair.getQuizId();
        Long questionId = pair.getQuestionId();

        Optional<Quiz> quiz = quizRepository.findById(quizId);
        Optional<Question> question = questionRepository.findQuestionById(questionId);

        if(quiz.isEmpty() || question.isEmpty()){
            throw new IllegalStateException("Pair cannot be created with nonexistent quiz/question");
        }

        else{
            pairService.addPair(pair);
        }

    }

    @PutMapping(path = "{quizId}")
    public void updateQuizTitle(@PathVariable("quizId") Long quizId, @RequestParam(required = false) String title){
        quizService.updateQuizBody(quizId, title);
    }

    @PutMapping(path = "{quizId}/leaderboards/{playerId}")
    public void updatePlayerScore(@PathVariable("quizId") Long quizId, @PathVariable("playerId") Long playerId, @RequestParam(required = false) int newScore){
        leaderboardService.updateScore(quizId, playerId, newScore);
    }

    @GetMapping(path = "{quizId}/leaderboards/{playerId}")
    public Leaderboard getPlayerScore(@PathVariable("quizId") Long quizId, @PathVariable("playerId") Long playerId){
       return leaderboardService.getSelectedScore(quizId, playerId);
    }





}
