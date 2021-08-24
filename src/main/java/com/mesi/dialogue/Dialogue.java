package com.mesi.dialogue;

import java.util.ArrayList;

public class Dialogue {

    private Integer id;
    private Integer currentQuestion = 0;
    private ArrayList<Question> questionsList = new ArrayList<>();


    public Dialogue() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Integer currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public Boolean checkNext(Integer questionId, String response ){
        return false;
    }

    public void checkChange(){};

}
