package com.keep.visual;



import com.keep.visual.QuizQuestionPair.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "test")
public class TestController {

    private final PairRepository pairRepository ;

    @Autowired
    public TestController(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    @GetMapping
    public String home(){
        return "this route is just for testing new features";
    }

    @PostMapping
    public void testList(@RequestBody List<Integer> list){


    }

}
