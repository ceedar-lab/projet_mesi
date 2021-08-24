package com.mesi.dialogue.dialogues;

import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;

import java.util.HashMap;

public class DialogueRoyalGards2 extends Dialogue {


    public DialogueRoyalGards2() {

        initDialogue();


    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("$#%!#!");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);


        Question question1 = new Question(1,1);
        question1.setMessage("(on ne comprend rien avec ce casque, mais il n'a pas l'air content)");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);


    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("END"))
        {
            setCurrentQuestion(1);
        }

        else if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(0);
            hasNext = false;
        }


        return hasNext;
    }


}
