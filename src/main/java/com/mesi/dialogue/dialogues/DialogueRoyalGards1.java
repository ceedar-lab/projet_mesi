package com.mesi.dialogue.dialogues;

import com.mesi.MainZeldo;
import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;
import com.mesi.equipement.LeftHand;
import com.mesi.equipement.Legs;
import com.mesi.equipement.RightHand;
import com.mesi.equipement.Torso;
import com.mesi.panels.Game;
import com.mesi.params.Hitbox;

import java.util.ArrayList;
import java.util.HashMap;

public class DialogueRoyalGards1 extends Dialogue {


    public DialogueRoyalGards1() {

        initDialogue();


    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Halte la! ou pense tu aller comme ca?");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("A","Le roi m'a fait mander");
        responsesList.put("B","nul part");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);


        Question question1 = new Question(1,1);
        question1.setMessage("Tu n'es pas digne de te presenter devant le roi. Vas voir le forgeron qu'il te fabrique une epee");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);


        Question question2 = new Question(2,1);
        question2.setMessage("Tres bien noble aventurier, veuillez entrer le roi va vous recevoir ");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question2.setResponseList(responsesList);
        getQuestionsList().add(question2);


        Question question3 = new Question(3,1);
        question3.setMessage("Decare de la! vil manant!");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question3.setResponseList(responsesList);
        getQuestionsList().add(question3);




    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("A") && Game.getCharacter().getRightHand() != RightHand.SWORD)
        {
            setCurrentQuestion(1);
        }

        else if(questionId == 0 && response.equals("A") && Game.getCharacter().getRightHand() == RightHand.SWORD)
        {
            setCurrentQuestion(2);
        }

        else if(questionId == 0 && response.equals("B"))
        {
            setCurrentQuestion(3);
        }

        else if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(0);
            hasNext = false;
        }

        else if(questionId == 2 && response.equals("END"))
        {
            setCurrentQuestion(0);

            MainZeldo.mapList.get("MAP_3").getTileList().get("56,10").removeHitboxs();
            MainZeldo.mapList.get("MAP_3").getTileList().get("57,10").removeHitboxs();
            MainZeldo.mapList.get("MAP_3").getTileList().get("58,10").removeHitboxs();

            MainZeldo.mapList.get("MAP_3").getTileList().get("56,11").setTileEvent(null);
            MainZeldo.mapList.get("MAP_3").getTileList().get("57,11").setTileEvent(null);
            MainZeldo.mapList.get("MAP_3").getTileList().get("58,11").setTileEvent(null);

            hasNext = false;
        }

        else if(questionId == 3 && response.equals("END"))
        {
            setCurrentQuestion(0);
            hasNext = false;
        }

        return hasNext;
    }


    @Override
    public void checkChange(){
//        if(getCurrentQuestion() == 1 && Game.getCharacter().getRightHand() == RightHand.SWORD){
//            setCurrentQuestion(2);
//        }

    }

}
