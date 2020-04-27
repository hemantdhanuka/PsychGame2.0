package com.dhanuka.psych.game.repositories;

import com.dhanuka.psych.game.model.EllenAnswer;
import com.dhanuka.psych.game.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EllenAnswerRepository extends JpaRepository<EllenAnswer,Long> {
    @Query(value="SELECT * FROM questions ORDER BY RAND() LIMIT 1", nativeQuery = true) //todo
    EllenAnswer getRandomAnswer(Question question);
}
