package org.sergio.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answers = new ArrayList<>();
    private int rightAnswer;

    public Question() {
        question = "";
        rightAnswer = 0;
    }

    public Question(String question, List<String> answers, int rightAnswer) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public boolean isEmpty() {
        if (question == null || question.isEmpty()) return true;
        if (answers.size() == 0) return true;
        if (rightAnswer == 0 || rightAnswer >= answers.size())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
