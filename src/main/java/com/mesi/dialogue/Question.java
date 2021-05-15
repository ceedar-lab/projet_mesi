package com.mesi.dialogue;

import java.util.HashMap;

public class Question {

    private Integer id;
    private Integer dialogueId;
    private String message;
    private HashMap<String,String> responseList;

    public Question() {
    }

    public Question(Integer id, Integer dialogueId) {
        this.id = id;
        this.dialogueId = dialogueId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDialogueId() {
        return dialogueId;
    }

    public void setDialogueId(Integer dialogueId) {
        this.dialogueId = dialogueId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, String> getResponseList() {
        return responseList;
    }

    public void setResponseList(HashMap<String, String> responseList) {
        this.responseList = responseList;
    }

}
