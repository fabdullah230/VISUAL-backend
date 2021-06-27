package com.keep.visual;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "error")
public class Error {

    @GetMapping
    public String error(){
        return "unexpected error";
    }

}
