package com.mesi.dialogue.dialogues;

import com.mesi.MainZeldo;
import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;

import java.util.HashMap;

public class DialogueHello extends Dialogue {


    public DialogueHello() {

        initDialogue();

    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Bonjour aventurier allez voir le chef dans la tente");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);

        Question question1 = new Question(1,1);
        question1.setMessage("Etes vous allez voir le chef ?");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);

        Question question2 = new Question(2,1);
        question2.setMessage("Bonjour aventurier");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question2.setResponseList(responsesList);
        getQuestionsList().add(question2);


    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("END"))
        {
            setCurrentQuestion(1);
            hasNext = false;
            MainZeldo.mapList.get("MAP_1").getTileList().get("17,14").setTraversable(true);
            MainZeldo.mapList.get("MAP_1").getTileList().get("18,14").setTraversable(true);
        }

        else if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(1);
            hasNext = false;
        }

        else if(questionId == 2 && response.equals("END"))
        {
            hasNext = false;
        }

        return hasNext;
    }


}
