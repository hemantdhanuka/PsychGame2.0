package com.dhanuka.psych.game.auth;

import com.dhanuka.psych.game.exceptions.NoSuchUserException;
import com.dhanuka.psych.game.model.User;
import com.dhanuka.psych.game.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new NoSuchUserException("No user registered with " + email);
        }
        return new CustomerUserDetails(user.get());
    }
}

