package com.mesi.params;

import com.mesi.MainZeldo;
import com.mesi.decor.Bush;
import com.mesi.decor.Chest;
import com.mesi.decor.DecorObject;
import com.mesi.panels.Game;
import com.mesi.panels.maps.MapModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Backup {

    /**********  Constructor  ***********/

    public Backup() {}

    /**********  Methods  **********/

    /**
     * Sauvegarde les paramètres du jeu en cours au format JSON.
     * @param title
     * @throws IOException
     */
    public void save(String title) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject character = new JSONObject();
        JSONObject location = new JSONObject();
        location.put("map", MainZeldo.state.toString());
        location.put("positionX", Game.characterCoordinates[0]);
        location.put("positionY", Game.characterCoordinates[1]);
        location.put("direction", Game.direction.get(0));
        character.put("location", location);
        json.put("character", character);

        JSONObject maps = new JSONObject();
        Enumeration<String> e = MainZeldo.mapList.keys();
        while (e.hasMoreElements()) {
            JSONObject map = new JSONObject();
            String mapName = e.nextElement();
            for (DecorObject obj : MainZeldo.mapList.get(mapName).getDecorObjectArraylist()) {
                JSONObject object = new JSONObject();
                object.put("positionX", obj.getX());
                object.put("positionY", obj.getY());
                map.put(obj.toString().split("\\.")[3].toLowerCase(), object);
            }
            maps.put(mapName, map);
        }
        json.put("maps", maps);

        Files.write(Paths.get("src/main/resources/saves/"+title+".json"), json.toJSONString().getBytes());
    }

    /**
     * Récupération des paramètres du fichier de sauvegarde et lancement du panel.
     * @param title
     */
    public void load(String title) {
        clearDecorObjectList();

        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/saves/"+title+".json");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject save = null;
        try {
            save = (JSONObject) jsonParser.parse(reader);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        JSONObject location = (JSONObject)((JSONObject) save.get("character")).get("location");
        Game.characterCoordinates[0] = Integer.valueOf(location.get("positionX").toString());
        Game.characterCoordinates[1] = Integer.valueOf(location.get("positionY").toString());
        Game.direction = new ArrayList<>() {{ add(Integer.valueOf(location.get("direction").toString())); }};

        JSONObject maps = (JSONObject) save.get("maps");
        for (Object map : maps.keySet()) {
            JSONObject m = (JSONObject) maps.get(map);
            for (Object decorObject : m.keySet()) {
                JSONObject p = (JSONObject) m.get(decorObject);
                if (decorObject.toString().contains("bush")) {
                    MainZeldo.mapList.get(map.toString()).getDecorObjectArraylist().add(new Bush(
                            Integer.valueOf(p.get("positionX").toString()) / 32,
                            Integer.valueOf(p.get("positionY").toString()) / 32
                    ));
                }
            }
        }

        Game.killThread = false;
        MainZeldo.state = MainZeldo.GameState.valueOf((String)location.get("map"));
        MainZeldo.onStateChange = true;
    }

    /**
     * Charge les paramètre initiaux comme la position du joueur, objets dynamiques etc... et lance une nouvelle partie.
     */
    public void startNewGame() {
        clearDecorObjectList();

        Game.characterCoordinates = new Integer[] { 11 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE };
        Game.direction = new ArrayList() {{ add(KeyMap.DOWN); }};

        MapModel map_1 = MainZeldo.mapList.get("MAP_1");
        for (int i = 14; i < 17; i++) {
            map_1.getDecorObjectArraylist().add(new Bush(i, 24));
        }

        MapModel map_2 = MainZeldo.mapList.get("MAP_2");
        map_2.getDecorObjectArraylist().add(new Chest(map_2.getWidth()/2, map_2.getHeight()/2 + -2));
        map_2.getDecorObjectArraylist().add(new Chest(map_2.getWidth()/2 + 3, map_2.getHeight()/2 + -2));

        Game.killThread = false;
        MainZeldo.state = MainZeldo.GameState.MAP_1;
        MainZeldo.onStateChange = true;
    }

    /**
     * Nettoie la liste des objets dynamiques.
     */
    public void clearDecorObjectList() {
        for (MapModel map : MainZeldo.mapList.values()) {
            map.getDecorObjectArraylist().clear();
        }
    }
}
