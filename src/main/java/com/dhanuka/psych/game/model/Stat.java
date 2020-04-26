package com.dhanuka.psych.game.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "stats")
public class Stat extends Auditable {
    private long gotPsychCount;
    private long psychOthersCount;
    private long correctAnswerCount;

}
