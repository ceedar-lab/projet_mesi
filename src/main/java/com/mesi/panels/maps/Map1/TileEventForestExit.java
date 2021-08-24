package com.mesi.panels.maps.Map1;

import com.mesi.MainZeldo;
import com.mesi.equipement.Legs;
import com.mesi.equipement.Torso;
import com.mesi.panels.DialoguePanel;
import com.mesi.panels.Game;
import com.mesi.panels.maps.TileEvent;

public class TileEventForestExit implements TileEvent {

    @Override
    public void action() {

        if(Game.getCharacter().getLegs() == Legs.NONE || Game.getCharacter().getTorso() == Torso.NONE){
            MainZeldo.dialogueList.get("hello").setCurrentQuestion(5);
        }

        new DialoguePanel(MainZeldo.dialogueList.get("hello"));

        MainZeldo.mapList.get("MAP_1").getTileList().get("14,25").setTileEvent(null);
        MainZeldo.mapList.get("MAP_1").getTileList().get("15,25").setTileEvent(null);
        MainZeldo.mapList.get("MAP_1").getTileList().get("16,25").setTileEvent(null);

    }
}
