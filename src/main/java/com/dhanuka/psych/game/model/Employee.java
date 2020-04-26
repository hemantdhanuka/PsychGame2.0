package com.dhanuka.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee extends User {

    @NotBlank
    private String Name;

    @NotBlank
    private String Address;

    @NotBlank
    private String phoneNumber;


}
