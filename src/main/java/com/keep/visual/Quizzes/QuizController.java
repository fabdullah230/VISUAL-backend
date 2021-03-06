package com.keep.visual.Quizzes;

import com.keep.visual.Leaderboards.Leaderboard;
import com.keep.visual.Leaderboards.LeaderboardService;
import com.keep.visual.Questions.Question;
import com.keep.visual.Questions.QuestionRepository;
import com.keep.visual.QuizQuestionPair.Pair;
import com.keep.visual.QuizQuestionPair.PairService;
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

        //force updating quiz to update last modification time
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(quiz1 -> {
            String temp = quiz1.getTitle();
            quizService.updateQuizBody(quiz1.getId(),"temp");
            quizService.updateQuizBody(quiz1.getId(), temp);
        });
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

        if(pairService.pairExists(quizId, questionId)){
            throw new IllegalStateException("Pair already exists");
        }

        pairService.addPair(pair);

        //force updating quiz to update last modification time
        quiz.ifPresent(quiz1 -> {
            String temp = quiz1.getTitle();
            quizService.updateQuizBody(quiz1.getId(),"temp");
            quizService.updateQuizBody(quiz1.getId(), temp);
        });


    }

    @PostMapping(path = "/{quizId}/quickadd")
    public void addPairQuick(@RequestBody List<Long> list, @PathVariable("quizId") Long quizId){
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if(quiz.isEmpty()){
            throw new IllegalStateException("Quiz with id " + quizId + " does not exist");
        }

        for(Long questionId : list){
            Optional<Question> question = questionRepository.findQuestionById(questionId);
            if(question.isEmpty()){
                throw new IllegalStateException("Question with id " + questionId + " does not exist");
            }

            if(pairService.pairExists(quizId, questionId)){
                throw new IllegalStateException("Pair already exists");
            }

            Pair p = new Pair(questionId, quizId);
            pairService.addPair(p);

            //force updating quiz to update last modification time
            quiz.ifPresent(quiz1 -> {
                String temp = quiz1.getTitle();
                quizService.updateQuizBody(quiz1.getId(),"temp");
                quizService.updateQuizBody(quiz1.getId(), temp);
            });

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
