package com.mesi.dialogue.dialogues;

import com.mesi.MainZeldo;
import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;

import java.util.HashMap;

public class DialoguePnjTente extends Dialogue {


    public DialoguePnjTente() {

        initDialogue();


    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Bonjour aventurier etes vous pret a vous lancer dans une expedition");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("A","OUI");
        responsesList.put("B","NON");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);


        Question question1 = new Question(1,1);
        question1.setMessage("Retournez voir le garde");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);


        Question question2 = new Question(2,1);
        question2.setMessage("Revenez me voir si vous changez d'avis");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question2.setResponseList(responsesList);
        getQuestionsList().add(question2);



    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("A"))
        {
            setCurrentQuestion(1);
        }

        else if(questionId == 0 && response.equals("B"))
        {
            setCurrentQuestion(2);
        }

        else if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(1);
            MainZeldo.pnjList.get("pnjTest").setDialogue(MainZeldo.dialogueList.get("test"));
            hasNext = false;
        }

        else if(questionId == 2 && response.equals("END"))
        {
            setCurrentQuestion(0);
            MainZeldo.pnjList.get("pnjTest").getDialogue().setCurrentQuestion(2);
            hasNext = false;
        }



        return hasNext;
    }


}
