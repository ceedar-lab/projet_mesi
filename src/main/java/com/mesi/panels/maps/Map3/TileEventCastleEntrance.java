package com.mesi.panels.maps.Map3;

import com.mesi.MainZeldo;
import com.mesi.panels.DialoguePanel;
import com.mesi.panels.maps.TileEvent;

public class TileEventCastleEntrance implements TileEvent {

    @Override
    public void action() {

        new DialoguePanel(MainZeldo.dialogueList.get("royalGard1"));

        MainZeldo.mapList.get("MAP_3").getTileList().get("56,11").setTileEvent(null);
        MainZeldo.mapList.get("MAP_3").getTileList().get("57,11").setTileEvent(null);
        MainZeldo.mapList.get("MAP_3").getTileList().get("58,11").setTileEvent(null);

    }
}
