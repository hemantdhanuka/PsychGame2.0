package com.dhanuka.psych.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/dev-test"))
public class HelloController {

    @GetMapping(value = "/")
    public String HelloWorld(){
        return "hello world";
    }

}
