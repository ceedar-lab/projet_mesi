package com.mesi.saves;


import com.github.cliftonlabs.json_simple.Jsoner;
import com.mesi.MainZeldo;
import com.mesi.decor.Bush;
import com.mesi.decor.DecorObject;
import com.mesi.panels.Game;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.panels.maps.MapModel;
import com.mesi.panels.maps.Map_1;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameInit {

    public static Integer[] startingCoordinates = new Integer[] { 11 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE };
    public static Integer direction = KeyMap.DOWN;
    public static String map = "MAP_1";
    public static ArrayList<DecorObject> map1_objectList = new ArrayList<>();

    public GameInit() throws IOException {
        JSONObject json = new JSONObject();

        JSONObject character = new JSONObject();
        character.put("positionX", Game.characterCoordinates[0]);
        character.put("positionY", Game.characterCoordinates[1]);
        character.put("direction", Game.direction.get(0));
        json.put("character", character);

        JSONObject maps = new JSONObject();
        Enumeration<String> e = MainZeldo.mapList.keys();
        while (e.hasMoreElements()) {
            JSONObject map = new JSONObject();
            String mapName = e.nextElement();
            for (DecorObject obj : MainZeldo.mapList.get(mapName).getDecorObjectArraylist()) {
                JSONObject object = new JSONObject();
                System.out.println(obj+" "+obj.getX()+" "+obj.getY());
                object.put("positionX", obj.getX());
                object.put("positionY", obj.getY());
                map.put(obj.toString().split("\\.")[3].toLowerCase(), object);
                System.out.println("/");
            }
            System.out.println(mapName);
            maps.put(mapName, map);
        }
        json.put("maps", maps);

        Files.write(Paths.get("src/main/resources/saves/init_data.json"), json.toJSONString().getBytes());
    }

    public static void RestartGame() {
        Game.characterCoordinates = new Integer[] { 11 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE };
        Game.direction = new ArrayList() {{ add(KeyMap.DOWN); }};

    }
}
