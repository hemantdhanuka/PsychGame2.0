package com.dhanuka.psych.game.utils;

import com.dhanuka.psych.game.config.ApplicationContextProvider;
import com.dhanuka.psych.game.model.EllenAnswer;
import com.dhanuka.psych.game.model.GameMode;
import com.dhanuka.psych.game.model.Question;
import com.dhanuka.psych.game.repositories.EllenAnswerRepository;
import com.dhanuka.psych.game.repositories.QuestionRepository;

public class Utils {
    //here if choose to not mark it @service or @component then we have to initilise it using that static block context() approach
    private static QuestionRepository questionRepository;
    private static EllenAnswerRepository ellenAnswerRepository;

    static {
        questionRepository = (QuestionRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("questionRepository");
        ellenAnswerRepository = (EllenAnswerRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("ellenAnswerRepository");
    }

    public static Question getRandomQuestion(GameMode gameMode) {
        return questionRepository.getRandomQuestion(gameMode);
    }

    public static EllenAnswer getRandomEllenAnswer(Question question) {
        return ellenAnswerRepository.getRandomAnswer(question);
    }
}
