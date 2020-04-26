package com.dhanuka.psych.game.auth;

import com.dhanuka.psych.game.model.Role;
import com.dhanuka.psych.game.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerUserDetails implements UserDetails {
    private String email;
    private String saltedHashedPassword;
    private Set<Role> roles;

    public CustomerUserDetails(User user) {
        this.email = user.getEmail();
        this.saltedHashedPassword = user.getSaltedHashedPassword();
        this.roles = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())
        ).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.saltedHashedPassword;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
