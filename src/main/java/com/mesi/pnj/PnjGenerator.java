package com.mesi.pnj;

import com.mesi.MainZeldo;
import com.mesi.equipement.*;
import com.mesi.params.Hitbox;

public class PnjGenerator {


    public PnjGenerator() {

        PnjTest("pnjTest");

    }

    private void PnjTest(String name) {
        try {
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

            MainZeldo.pnjList.put(name, pnjTest);

        } catch (Exception e) {

        }

    }


}
