package com.dhanuka.psych.game.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends Auditable {
    @NotBlank
    private String question;

    @NotBlank
    private String correctAnswer;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private Set<EllenAnswer> ellenAnswers = new HashSet();

    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    public Question() {
    }

    public Question(@NotNull String question, @NotNull String correctAnswer, @NotNull GameMode gameMode) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.gameMode = gameMode;
    }
}
