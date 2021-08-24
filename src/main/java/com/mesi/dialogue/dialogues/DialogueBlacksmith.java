package com.mesi.dialogue.dialogues;

import com.mesi.MainZeldo;
import com.mesi.decor.collectable.CollectableItem;
import com.mesi.decor.collectable.Sword;
import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.Question;
import com.mesi.equipement.RightHand;
import com.mesi.panels.Game;

import java.util.HashMap;

public class DialogueBlacksmith extends Dialogue {


    public DialogueBlacksmith() {

        initDialogue();


    }


    private void initDialogue(){

        Question question0 = new Question(0,1);
        question0.setMessage("Bonjour aventurier que puis je pour toi?");
        HashMap<String,String> responsesList = new HashMap<>();
        responsesList.put("A","il me faut une epee");
        responsesList.put("B","rien merci");
        question0.setResponseList(responsesList);
        getQuestionsList().add(question0);


        Question question1 = new Question(1,1);
        question1.setMessage("J'en ai justement une en stock. Je te la vend 1000 pieces d'or");
        responsesList = new HashMap<>();
        responsesList.put("END","je n'ai pas une telle somme");
        question1.setResponseList(responsesList);
        getQuestionsList().add(question1);


        Question question2 = new Question(2,1);
        question2.setMessage("bon on peut s'arranger. fait moi de la pub aupres des habitants du village et je te donnerais l'epee  ");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question2.setResponseList(responsesList);
        getQuestionsList().add(question2);


        Question question3 = new Question(3,1);
        question3.setMessage("Tu as bien bosse voici ta recompense.");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question3.setResponseList(responsesList);
        getQuestionsList().add(question3);

        Question question4 = new Question(4,1);
        question4.setMessage("tu n'auras pas ton epee tant que n'auras pas parle a tout le monde.");
        responsesList = new HashMap<>();
        responsesList.put("END","fin");
        question4.setResponseList(responsesList);
        getQuestionsList().add(question4);


    }

    @Override
    public Boolean checkNext(Integer questionId, String response ){
        Boolean hasNext = true;

        if(questionId == 0 && response.equals("A") )
        {
            setCurrentQuestion(1);
        }

        else if(questionId == 0 && response.equals("B"))
        {
            hasNext = false;
        }

        else if(questionId == 1 && response.equals("END"))
        {
            setCurrentQuestion(2);
        }

        else if(questionId == 2 && response.equals("END"))
        {

            MainZeldo.pnjList.get("pnjPeasant").setDialogue(new DialoguePromo(MainZeldo.pnjList.get("pnjPeasant")));
            MainZeldo.pnjList.get("pnjFisherman").setDialogue(new DialoguePromo(MainZeldo.pnjList.get("pnjFisherman")));
            MainZeldo.pnjList.get("pnjFruitVendor").setDialogue(new DialoguePromo(MainZeldo.pnjList.get("pnjFruitVendor")));
            MainZeldo.pnjList.get("pnjHunter").setDialogue(new DialoguePromo(MainZeldo.pnjList.get("pnjHunter")));

            setCurrentQuestion(4);
            hasNext = false;
        }

        else if(questionId == 3 && response.equals("END"))
        {
            MainZeldo.pnjList.get("pnjBlacksmith").setDialogue(new DialogueBonjour());
            Game.getCharacter().weaponsList.add((CollectableItem) new Sword());
            hasNext = false;
        }

        else if(questionId == 4 && response.equals("END"))
        {
            hasNext = false;
        }

        return hasNext;
    }


    @Override
    public void checkChange(){
        if( getCurrentQuestion() == 4 &&
            MainZeldo.pnjList.get("pnjPeasant").getDialogue() instanceof DialogueBonjour &&
            MainZeldo.pnjList.get("pnjFisherman").getDialogue() instanceof DialogueBonjour &&
            MainZeldo.pnjList.get("pnjFruitVendor").getDialogue() instanceof DialogueBonjour &&
            MainZeldo.pnjList.get("pnjHunter").getDialogue() instanceof DialogueBonjour
        ){
            setCurrentQuestion(3);
        }

    }

}
