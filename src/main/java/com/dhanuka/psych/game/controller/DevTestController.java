package com.dhanuka.psych.game.controller;

import com.dhanuka.psych.game.model.Game;
import com.dhanuka.psych.game.model.GameMode;
import com.dhanuka.psych.game.model.Player;
import com.dhanuka.psych.game.model.Question;
import com.dhanuka.psych.game.repositories.GameRepository;
import com.dhanuka.psych.game.repositories.PlayerRepository;
import com.dhanuka.psych.game.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/dev-test"))
public class DevTestController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(value = "/")
    public String HelloWorld() {
        return "hello world";
    }


    @GetMapping("/populate")
    public String populateDB() {

        Game game = new Game();
        game.setGameMode(GameMode.IS_THIS_A_FACT);


        Player luffy = new Player();
        luffy.setAlias("luffy");
        luffy.setEmail("abc@gmail.com");
        luffy.setSaltedHashedPassword("hashedPassword");

        Player hemant = new Player();
        hemant.setAlias("dhanuka");
        hemant.setEmail("dhanuka@gmail.com");
        hemant.setSaltedHashedPassword("hashedPassword");

        game.getPlayers().add(luffy);
        game.getPlayers().add(hemant);
        game.setLeader(luffy);
        luffy.getGames().add(game);
        hemant.getGames().add(game);
        playerRepository.save(luffy);
        playerRepository.save(hemant);
        gameRepository.save(game);
/*

        Player newHemant = playerRepository.findById(hemant.getId()).get();
        Game game1=new Game();
        game1.setGameMode(GameMode.MOVIE_BUFF);
        game1.getPlayers().add(newHemant);
        game1.setLeader(newHemant);
        newHemant.getGames().add(game1);
        playerRepository.saveAndFlush(newHemant);
        gameRepository.saveAndFlush(game1);

*/
        return "populated";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable(name = "id") Long id) {
        return questionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable(name = "id") Long id) {
        return playerRepository.findById(id).orElseThrow(ArithmeticException::new);
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable(name = "id") Long id) {
        return gameRepository.findById(id).orElseThrow(ArithmeticException::new);
    }


}
