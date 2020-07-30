package org.sergio.service;

import org.sergio.domain.Question;

import java.io.IOException;
import java.util.Map;

public interface AskQuestionService {
    void askQuestions() throws IOException;

    Map<Question, Integer> getResult();
}
