package com.mesi.pnj;

import com.mesi.MainZeldo;
import com.mesi.animation.Animation;
import com.mesi.animation.WhiteCharacterAnimation;
import com.mesi.equipement.*;
import com.mesi.params.Hitbox;

public class PnjGenerator {


    public PnjGenerator() {

        PnjTest();

    }

    private void PnjTest() {
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
                            "res/images/character-white_skin.png"
                    );

            pnjTest.setHitbox(Hitbox.FULL);

            MainZeldo.pnjList.put("pnjTest", pnjTest);

        } catch (Exception e) {

        }

    }


}
