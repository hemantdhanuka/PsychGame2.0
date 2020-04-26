package com.dhanuka.psych.game.repositories;

import com.dhanuka.psych.game.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
