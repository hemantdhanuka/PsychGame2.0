package com.dhanuka.psych.game.controller;

import com.dhanuka.psych.game.exceptions.InvalidGameActionException;
import com.dhanuka.psych.game.model.Player;
import com.dhanuka.psych.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/play")
public class GamePlayController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/")
    public String play(Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/submit-answer/{answer}")
    public void submitAnswer(Authentication authentication, @PathVariable(name="answer") String answer) throws InvalidGameActionException {
        Player player = playerRepository.findByEmail(authentication.getName()).get();  //todo else throw exception if null
        player.getCurrentGame().submitAnswer(player, answer);
    }
}
