package com.mesi.pnj;

import com.mesi.MainZeldo;
import com.mesi.equipement.*;
import com.mesi.params.Hitbox;

import java.io.IOException;

public class PnjGenerator {


    public PnjGenerator() throws IOException {

        pnjTest("pnjTest");
//        pnjTest2("pnjTest2");
        pnjRoyalGards1("pnjRoyalGards1");
        pnjRoyalGards2("pnjRoyalGards2");
        pnjFisherman("pnjFisherman");
        pnjBlacksmith("pnjBlacksmith");
        pnjPeasant("pnjPeasant");
        pnjFruitVendor("pnjFruitVendor");
        pnjHunter("pnjHunter");

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


//    private void pnjTest2(String name) throws IOException {
//        Pnj pnjTest2 = new Pnj
//                (
//                        Hair.BLOND,
//                        Head.NONE,
//                        Torso.TSHIRT,
//                        Hands.NONE,
//                        Legs.LEATHER_PANTS,
//                        Feet.LEATHER_BOOTS,
//                        RightHand.NONE,
//                        LeftHand.NONE,
//                        "src/main/resources/images/sprites/character-white_skin.png"
//                );
//        pnjTest2.setName(name);
//        pnjTest2.setHitbox(Hitbox.FULL);
//        pnjTest2.setDialogue(MainZeldo.dialogueList.get("tente"));
//
//        MainZeldo.pnjList.put(name, pnjTest2);
//    }


    private void pnjRoyalGards1(String name) throws IOException {
        Pnj pnjRoyalGards1 = new Pnj
                (
                        Hair.BLOND,
                        Head.CHAIN_HOOD,
                        Torso.METAL_ARMOR,
                        Hands.METAL_GLOVES,
                        Legs.METAL_PANTS,
                        Feet.METAL_BOOTS,
                        RightHand.SPEAR,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjRoyalGards1.setName(name);
        pnjRoyalGards1.setHitbox(Hitbox.FULL);
        pnjRoyalGards1.setDialogue(MainZeldo.dialogueList.get("royalGard1"));

        MainZeldo.pnjList.put(name, pnjRoyalGards1);
    }

    private void pnjRoyalGards2(String name) throws IOException {
        Pnj pnjRoyalGards2 = new Pnj
                (
                        Hair.BLOND,
                        Head.METAL_HELMET,
                        Torso.METAL_ARMOR,
                        Hands.METAL_GLOVES,
                        Legs.METAL_PANTS,
                        Feet.METAL_BOOTS,
                        RightHand.SPEAR,
                        LeftHand.SHIELD,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjRoyalGards2.setName(name);
        pnjRoyalGards2.setHitbox(Hitbox.FULL);
        pnjRoyalGards2.setDialogue(MainZeldo.dialogueList.get("royalGard2"));

        MainZeldo.pnjList.put(name, pnjRoyalGards2);
    }

    private void pnjFisherman(String name) throws IOException {
        Pnj pnjFisherman = new Pnj
                (
                        Hair.BROWN,
                        Head.NONE,
                        Torso.TSHIRT,
                        Hands.NONE,
                        Legs.LEATHER_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjFisherman.setName(name);
        pnjFisherman.setHitbox(Hitbox.FULL);
        pnjFisherman.setDialogue(MainZeldo.dialogueList.get("bonjour"));

        MainZeldo.pnjList.put(name, pnjFisherman);
    }

    private void pnjBlacksmith(String name) throws IOException {
        Pnj pnjFisherman = new Pnj
                (
                        Hair.BLACK,
                        Head.NONE,
                        Torso.LEATHER_ARMOR,
                        Hands.NONE,
                        Legs.LEATHER_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjFisherman.setName(name);
        pnjFisherman.setHitbox(Hitbox.FULL);
        pnjFisherman.setDialogue(MainZeldo.dialogueList.get("bonjour"));

        MainZeldo.pnjList.put(name, pnjFisherman);
    }


    private void pnjPeasant(String name) throws IOException {
        Pnj pnjPeasant = new Pnj
                (
                        Hair.BLOND,
                        Head.NONE,
                        Torso.TSHIRT,
                        Hands.NONE,
                        Legs.BLUE_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjPeasant.setName(name);
        pnjPeasant.setHitbox(Hitbox.FULL);
        pnjPeasant.setDialogue(MainZeldo.dialogueList.get("bonjour"));

        MainZeldo.pnjList.put(name, pnjPeasant);
    }


    private void pnjFruitVendor(String name) throws IOException {
        Pnj pnjFruitVendor = new Pnj
                (
                        Hair.BLACK,
                        Head.NONE,
                        Torso.TSHIRT_GREEN,
                        Hands.NONE,
                        Legs.BLUE_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjFruitVendor.setName(name);
        pnjFruitVendor.setHitbox(Hitbox.FULL);
        pnjFruitVendor.setDialogue(MainZeldo.dialogueList.get("bonjour"));

        MainZeldo.pnjList.put(name, pnjFruitVendor);
    }

    private void pnjHunter(String name) throws IOException {
        Pnj pnjHunter = new Pnj
                (
                        Hair.BLOND,
                        Head.LEATHER_HAT,
                        Torso.TSHIRT_GREEN,
                        Hands.NONE,
                        Legs.LEATHER_PANTS,
                        Feet.LEATHER_BOOTS,
                        RightHand.NONE,
                        LeftHand.NONE,
                        "src/main/resources/images/sprites/character-white_skin.png"
                );
        pnjHunter.setName(name);
        pnjHunter.setHitbox(Hitbox.FULL);
        pnjHunter.setDialogue(MainZeldo.dialogueList.get("bonjour"));

        MainZeldo.pnjList.put(name, pnjHunter);
    }

}
