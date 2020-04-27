package com.dhanuka.psych.game.repositories;

import com.dhanuka.psych.game.model.GameMode;
import com.dhanuka.psych.game.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    //TODO to make it more optimise
    @Query(value="SELECT * FROM questions WHERE gameMode=:gameMode ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question getRandomQuestion(GameMode gameMode);
}
