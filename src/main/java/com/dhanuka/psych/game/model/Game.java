package com.dhanuka.psych.game.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    @Getter
    @Setter
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
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


}
