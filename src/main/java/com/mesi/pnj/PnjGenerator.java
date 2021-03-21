package com.mesi.pnj;

import com.mesi.MainZeldo;
import com.mesi.equipement.*;
import com.mesi.params.Hitbox;

import java.io.IOException;

public class PnjGenerator {


    public PnjGenerator() throws IOException {

        pnjTest("pnjTest");

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
        pnjTest.setDialogue(MainZeldo.dialogueList.get("test"));

        MainZeldo.pnjList.put(name, pnjTest);
    }
}
