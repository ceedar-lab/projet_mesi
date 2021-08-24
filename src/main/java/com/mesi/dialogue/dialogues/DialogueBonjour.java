package com.mesi.dialogue.dialogues;

import com.mesi.MainZeldo;
import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;
import com.mesi.equipement.Legs;
import com.mesi.equipement.Torso;
import com.mesi.panels.Game;

import java.util.HashMap;

public class DialogueBonjour extends Dialogue {


    public DialogueBonjour() {

        initDialogue();

    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Bonjour");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);


    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("END"))
        {
            hasNext = false;
        }


        return hasNext;
    }

    @Override
    public void checkChange(){


    }

}
