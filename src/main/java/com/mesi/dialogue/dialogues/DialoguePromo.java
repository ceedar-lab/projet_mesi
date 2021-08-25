package com.mesi.dialogue.dialogues;

import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;
import com.mesi.pnj.Pnj;

import java.util.HashMap;

public class DialoguePromo extends Dialogue {


    public DialoguePromo(Pnj pnj) {

        setPnj(pnj);
        initDialogue();

    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Bonjour");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("END","avez vous entendu parler du nouveau forgeron?");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);

        Question question1 = new Question(1,1);
        question1.setMessage("non je ne savais pas que nous avions un forgeron en ville");
        responsesList = new HashMap<>();
        responsesList.put("END","je vous le recommande il fait du bon travail ");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);


        Question question2 = new Question(2,1);
        question2.setMessage("merci je vais aller voir ce qu'il propose");
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
        }
        if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(2);
        }
        if(questionId == 2 && response.equals("END"))
        {
            getPnj().setDialogue(new DialogueBonjour());
            hasNext = false;
        }

        return hasNext;
    }

    @Override
    public void checkChange(){


    }

}
