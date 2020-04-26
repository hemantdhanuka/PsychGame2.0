package com.dhanuka.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends Auditable {
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String description;


}
