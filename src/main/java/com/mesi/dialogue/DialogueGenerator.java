package com.mesi.dialogue;

import com.mesi.MainZeldo;
import com.mesi.dialogue.dialogues.DialogueTest;

public class DialogueGenerator {

    public DialogueGenerator() {
        MainZeldo.dialogueList.put("test", new DialogueTest());

    }
}
