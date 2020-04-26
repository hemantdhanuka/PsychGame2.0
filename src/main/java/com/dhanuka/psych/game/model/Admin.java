package com.dhanuka.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends Employee {

}
