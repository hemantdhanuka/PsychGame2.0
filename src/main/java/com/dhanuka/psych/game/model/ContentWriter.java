package com.dhanuka.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "contentwriters")
public class ContentWriter extends Employee {

}
