package com.dhanuka.psych.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GamePlayService {    //to keep html view related calls

    @GetMapping("")
    public String index(){
        return "index.html";
    }
}
