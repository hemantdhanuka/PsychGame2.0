package com.dhanuka.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Getter
    @NotBlank
    private String saltedHashedPassword;

    public void setSaltedHashedPassword(String saltedHashedPassword) {
        this.saltedHashedPassword=new BCryptPasswordEncoder(5).encode(saltedHashedPassword);
    }
}
