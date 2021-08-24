package com.mesi.dialogue;

import com.mesi.MainZeldo;
import com.mesi.dialogue.dialogues.*;

public class DialogueGenerator {

    public DialogueGenerator() {
        MainZeldo.dialogueList.put("test", new DialogueTest());
        MainZeldo.dialogueList.put("bonjour", new DialogueBonjour());
        MainZeldo.dialogueList.put("hello", new DialogueHello());
        MainZeldo.dialogueList.put("tente", new DialoguePnjTente());
        MainZeldo.dialogueList.put("royalGard1", new DialogueRoyalGards1());
        MainZeldo.dialogueList.put("royalGard2", new DialogueRoyalGards2());

    }
}
