package com.dhanuka.psych.game.model;

import com.dhanuka.psych.game.exceptions.InvalidGameActionException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "rounds")
public class Round extends Auditable {
    @JsonBackReference
    @ManyToOne
    @NotNull
    private Game game;

    @ManyToOne
    @NotNull
    private Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, PlayerAnswer> submittedAnswers = new HashMap();

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap();

    @ManyToOne
    private EllenAnswer ellenAnswer;

    private int roundNumber;


    public Round() {
    }

    public Round(@NotNull Game game, @NotNull Question question, int roundNumber) {
        this.game = game;
        this.question = question;
        this.roundNumber = roundNumber;
    }


    public void submitAnswer(Player player, String answer) throws InvalidGameActionException {
        if (submittedAnswers.containsKey(player))
            throw new InvalidGameActionException("Player has already submitted an answer for this round");
        for (PlayerAnswer existingAnswer : submittedAnswers.values())
            if (answer.equals(existingAnswer.getAnswer()))
                throw new InvalidGameActionException("Duplicate Answer!");
        submittedAnswers.put(player, new PlayerAnswer(this, player, answer));
    }

    public boolean allAnswersSubmitted(int numPlayers) {
        return submittedAnswers.size() == numPlayers;
    }

    public void selectAnswer(Player player, PlayerAnswer selectedAnswer) throws InvalidGameActionException {
        if (selectedAnswers.containsKey(player))
            throw new InvalidGameActionException("Player has already selected an answer for this round");
        if(selectedAnswer.getPlayer().equals(player))
            throw new InvalidGameActionException("Can't select your own answer");
        if (!selectedAnswer.getRound().equals(this))
            throw new InvalidGameActionException("No such answer was submitted in this round");
        selectedAnswers.put(player, selectedAnswer);
    }

    public boolean allAnswersSelected(int numPlayers) {
        return selectedAnswers.size() == numPlayers;
    }
}
