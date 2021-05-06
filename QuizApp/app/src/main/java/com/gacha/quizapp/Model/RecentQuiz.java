package com.gacha.quizapp.Model;

public class RecentQuiz {
    private String quizName;
    private String quizDescription;
    private int quizId;

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public RecentQuiz(String quizName, String quizDescription) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
    }

    public RecentQuiz(String quizName, String quizDescription, int quizId) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizId = quizId;
    }
}
