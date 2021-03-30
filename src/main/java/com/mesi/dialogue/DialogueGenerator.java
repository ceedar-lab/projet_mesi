package com.mesi.dialogue;

import com.mesi.MainZeldo;
import com.mesi.dialogue.dialogues.DialogueHello;
import com.mesi.dialogue.dialogues.DialoguePnjTente;
import com.mesi.dialogue.dialogues.DialogueTest;

public class DialogueGenerator {

    public DialogueGenerator() {
        MainZeldo.dialogueList.put("test", new DialogueTest());
        MainZeldo.dialogueList.put("hello", new DialogueHello());
        MainZeldo.dialogueList.put("tente", new DialoguePnjTente());

    }
}
