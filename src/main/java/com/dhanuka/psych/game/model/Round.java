package com.dhanuka.psych.game.model;

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
    private Map<Player, PlayerAnswer> playerAnswers = new HashMap();

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap();

    @ManyToOne
    private EllenAnswer ellenAnswer;

    private int roundNumber;

}
