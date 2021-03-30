package com.mesi.pnj;

import com.mesi.MainZeldo;
import com.mesi.equipement.*;
import com.mesi.params.Hitbox;

import java.io.IOException;

public class PnjGenerator {


    public PnjGenerator() throws IOException {

        pnjTest("pnjTest");
        pnjTest2("pnjTest2");

    }

    private void pnjTest(String name) throws IOException {
        Pnj pnjTest = new Pnj
                (
                        Hair.BLOND,
                        Head.NONE,
                        Torso.TSHIRT,
                        Hands.NONE,
                        Legs.LEATHER_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjTest.setName(name);
        pnjTest.setHitbox(Hitbox.FULL);
        pnjTest.setDialogue(MainZeldo.dialogueList.get("hello"));

        MainZeldo.pnjList.put(name, pnjTest);
    }


    private void pnjTest2(String name) throws IOException {
        Pnj pnjTest2 = new Pnj
                (
                        Hair.BLOND,
                        Head.NONE,
                        Torso.TSHIRT,
                        Hands.NONE,
                        Legs.LEATHER_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjTest2.setName(name);
        pnjTest2.setHitbox(Hitbox.FULL);
        pnjTest2.setDialogue(MainZeldo.dialogueList.get("tente"));

        MainZeldo.pnjList.put(name, pnjTest2);
    }







}
