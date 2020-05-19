package com.dhanuka.psych.game.model;

import com.dhanuka.psych.game.exceptions.InvalidGameActionException;
import com.dhanuka.psych.game.utils.Utils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable {
    @Getter
    @Setter
    @ManyToMany
    @JsonManagedReference
    private Set<Player> players = new HashSet<>();

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private GameMode gameMode;

    @Getter
    @Setter
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
    @OrderBy(value = "round_number asc")
    private List<Round> rounds = new ArrayList();

    @Getter
    @Setter
    private int numRounds = 10;

    @Getter
    @Setter
    private boolean hasEllen = false;

    @Getter
    @Setter
    @ManyToOne
    @NotNull
    private Player leader;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, Stat> playerStats = new HashMap<>();

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Getter
    @Setter
    @ManyToMany
    private Set<Player> readyPlayers = new HashSet<>();

    public Game() {
    }

    public Game(@NotNull GameMode gameMode, int numRounds, boolean hasEllen, @NotNull Player leader, GameStatus gameStatus) {
        this.gameMode = gameMode;
        this.numRounds = numRounds;
        this.hasEllen = hasEllen;
        this.leader = leader;
        this.gameStatus = gameStatus;
        try {
            addPlayer(leader);
        } catch (InvalidGameActionException ignored) {
            //never gonna come here but majboori
        }
    }

    public void addPlayer(Player player) throws InvalidGameActionException {
        if (!gameStatus.equals(GameStatus.PLAYERS_JOINING))
            throw new InvalidGameActionException("Can't join after the game has started");
        players.add(player);
        player.setCurrentGame(this);
    }

    public void removePlayer(Player player) throws InvalidGameActionException {
        if (!players.contains(player))
            throw new InvalidGameActionException("No such player was in the game.");
        players.remove(player);
        if (player.getCurrentGame().equals(this))
            player.setCurrentGame(null);
        if (players.size() == 0 || (players.size() == 1 && !gameStatus.equals(GameStatus.PLAYERS_JOINING)))
            endGame();
    }

    private void endGame() {
        //TODO adding stats to player stats //<-- me
        gameStatus = GameStatus.ENDED;
        for (Player player : players){
            if (player.getCurrentGame().equals(this)){
                player.setCurrentGame(null);
            }
        }
    }

    public void startGame(Player player) throws InvalidGameActionException {
        if (!gameStatus.equals(GameStatus.PLAYERS_JOINING))
            throw new InvalidGameActionException("The game has already started");
        if (players.size() < 2)
            throw new InvalidGameActionException("Can't start a game with a single player");
        if (!player.equals(leader))
            throw new InvalidGameActionException("Only the leader can start the game");

        startNewRound();
    }

    private void startNewRound() {
        gameStatus = GameStatus.SUBMITTING_ANSWERS;
        Question question = Utils.getRandomQuestion(gameMode);
        Round round = new Round(this, question, rounds.size() + 1);
        if (hasEllen)
            round.setEllenAnswer(Utils.getRandomEllenAnswer(question));
        rounds.add(new Round());
    }


    public void submitAnswer(Player player, String answer) throws InvalidGameActionException {
        if (Strings.isBlank(answer))
            throw new InvalidGameActionException("Answer cannot be empty or null");
        if (!players.contains(player))
            throw new InvalidGameActionException("No such player was in the game.");
        if (!gameStatus.equals(GameStatus.SUBMITTING_ANSWERS))
            throw new InvalidGameActionException("Game is not accepting answers at present");
        Round currentRound = getCurrentRound();
        currentRound.submitAnswer(player, answer);
        if (currentRound.allAnswersSubmitted(players.size()))
            gameStatus = GameStatus.SELECTING_ANSWERS;
    }

    private Round getCurrentRound() throws InvalidGameActionException {
        if (rounds.size() == 0)
            throw new InvalidGameActionException("The game has not started");
        return rounds.get(rounds.size() - 1);
    }

    public void selectAnswer(Player player, PlayerAnswer selectedAnswer) throws InvalidGameActionException {
        if (!players.contains(player))
            throw new InvalidGameActionException("No such player was in the game.");
        if (!gameStatus.equals(GameStatus.SELECTING_ANSWERS))
            throw new InvalidGameActionException("Game is not selecting answers at present");
        Round currentRound = getCurrentRound();
        currentRound.selectAnswer(player, selectedAnswer);

        if (currentRound.allAnswersSelected(players.size())) {
            if (rounds.size() < numRounds)
                gameStatus = GameStatus.WAITING_FOR_READY;
            else
                endGame();
        }
    }


    public void playerIsReady(Player player) throws InvalidGameActionException {
        if (!players.contains(player))
            throw new InvalidGameActionException("No such player was in the game.");
        if (!gameStatus.equals(GameStatus.WAITING_FOR_READY))
            throw new InvalidGameActionException("Game is not waiting for players to be ready");
        readyPlayers.add(player);
        if (readyPlayers.size() == players.size())
            startNewRound();
    }

    public void playerIsNotReady(Player player) throws InvalidGameActionException {
        if (!players.contains(player))
            throw new InvalidGameActionException("No such player was in the game.");
        if (!gameStatus.equals(GameStatus.WAITING_FOR_READY))
            throw new InvalidGameActionException("Game is not waiting for players to be ready");
        readyPlayers.remove(player);
    }


    public String getGameState() {
        // todo
        return "some string here which will have all the data that the frontend needs";
    }

}
