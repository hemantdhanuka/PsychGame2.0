package com.dhanuka.psych.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends User {
    @NotBlank
    private String alias;

    @URL
    private String psychFaceURL;

    @URL
    private String picURL;

    @OneToOne(cascade = CascadeType.ALL)
    private Stat stat = new Stat();

    @JsonBackReference
    @ManyToMany(mappedBy = "players")
    private Set<Game> games = new HashSet<>();


    public Game getCurrentGame() {
        // TODO
        Game currentGame=null;
        return currentGame;
    }
}
