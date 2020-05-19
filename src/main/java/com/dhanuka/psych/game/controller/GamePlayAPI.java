package com.dhanuka.psych.game.controller;

import com.dhanuka.psych.game.exceptions.InvalidGameActionException;
import com.dhanuka.psych.game.exceptions.NoSuchUserException;
import com.dhanuka.psych.game.model.Player;
import com.dhanuka.psych.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/play")
public class GamePlayAPI {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/")
    public Player play(Authentication authentication) throws NoSuchUserException {
        Player player = getCurrentPlayer(authentication);
        return player;
    }

    @GetMapping("/submit-answer/{answer}")
    public void submitAnswer(Authentication authentication, @PathVariable(name="answer") String answer) throws InvalidGameActionException, NoSuchUserException {
        Player player = getCurrentPlayer(authentication);
        player.getCurrentGame().submitAnswer(player, answer);
    }

    private Player getCurrentPlayer(Authentication authentication) throws NoSuchUserException {
        return playerRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NoSuchUserException("current player not found"));
    }
}
