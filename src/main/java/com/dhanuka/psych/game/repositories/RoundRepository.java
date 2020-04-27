package com.dhanuka.psych.game.repositories;

import com.dhanuka.psych.game.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round,Long> {
    
}
