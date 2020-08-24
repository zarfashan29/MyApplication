package com.app.myapplication;

public class quizwork {
    private String question;
    private String option1;
    private String option2;
    private int answers;
    public quizwork(){}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public quizwork(String question, String option1, String option2, int answers) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answers = answers;
    }
}
