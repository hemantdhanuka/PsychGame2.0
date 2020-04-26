package com.dhanuka.psych.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "playeranswers")
public class PlayerAnswer extends Auditable {
    @NotNull
    @ManyToOne
    @JsonBackReference
    private Round round;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Player player;

    @NotBlank
    private String answer;

}
