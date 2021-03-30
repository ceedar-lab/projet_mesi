package com.mesi.params;

import com.mesi.MainZeldo;
import com.mesi.decor.*;
import com.mesi.decor.collectable.BootsLeather;
import com.mesi.decor.collectable.Dagger;
import com.mesi.decor.collectable.Shield;
import com.mesi.decor.collectable.Sword;
import com.mesi.panels.Game;
import com.mesi.panels.maps.MapModel;
import com.mesi.pnj.Pnj;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Backup {

    /**********  Attributes  **********/

    private static Logger logger = Logger.getLogger(Backup.class);

    private static final String SAVE_PATH = "src/main/resources/saves/";
    private static final String JSON = ".json";
    /** Personnage **/
    private static final String CHARACTER = "character";
    private static final String LOCATION = "location";
    private static final String POSITION_X = "positionX";
    private static final String POSITION_Y = "positionY";
    private static final String MAP = "map";
    private static final String DIRECTION = "direction";
    /** Cartes **/
    private static final String MAPS = "maps";
    private static final String MAP_1 = "MAP_1";
    private static final String MAP_2 = "MAP_2";
    private static final String STATE = "state";

    /**********  Constructors  **********/

    public Backup() {
        // Initialisation classe backup.
    }

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
        location.put(MAP, MainZeldo.getGameState().toString());
        location.put(POSITION_X, Game.getCharacterCoordinates()[0]);
        location.put(POSITION_Y, Game.getCharacterCoordinates()[1]);
        location.put(DIRECTION, Game.getDirection().get(0));
        character.put(LOCATION, location);
        json.put(CHARACTER, character);

        JSONObject maps = new JSONObject();

        for (String mapName : MainZeldo.mapList.keySet()) {
            JSONObject map = new JSONObject();
            for (DecorObject obj : MainZeldo.mapList.get(mapName).getDecorObjectArraylist()) {
                JSONObject object = new JSONObject();
                if (obj instanceof Chest)
                    object.put(STATE, ((Chest) obj).getState());
                object.put(POSITION_X, obj.getX());
                object.put(POSITION_Y, obj.getY());
                map.put(obj.toString().split("\\.")[3].toLowerCase(), object);
            }
            maps.put(mapName, map);
        }
        json.put(MAPS, maps);

        Files.write(Paths.get(SAVE_PATH + title + JSON), json.toJSONString().getBytes());
    }

    /**
     * Récupération des paramètres du fichier de sauvegarde et lancement du panel.
     * @param title
     */
    public void load(String title) {
        clearDecorObjectList();

        FileReader reader = null;
        try {
            reader = new FileReader(SAVE_PATH + title + JSON);
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Erreur : le fichier recherché n'existe plus");
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject save = new JSONObject();
        try {
            save = (JSONObject) jsonParser.parse(reader);
        } catch (Exception e) {
            logger.error("Erreur lors du chargement de la partie");
        }

        JSONObject location = (JSONObject)((JSONObject) save.get(CHARACTER)).get(LOCATION);
        Game.setCharacterCoordinates(new Integer[]{
                Integer.valueOf(location.get(POSITION_X).toString()),
                Integer.valueOf(location.get(POSITION_Y).toString())
        });
        List<Integer> direction = new ArrayList<>();
        direction.add(Integer.valueOf(location.get(DIRECTION).toString()));
        Game.setDirection(direction);

        JSONObject maps = (JSONObject) save.get(MAPS);
        for (Object map : maps.keySet()) {
            JSONObject m = (JSONObject) maps.get(map);
            for (Object decorObject : m.keySet()) {
                JSONObject p = (JSONObject) m.get(decorObject);
                if (decorObject.toString().contains("bush")) {
                    MainZeldo.mapList.get(map.toString()).getDecorObjectArraylist().add(new Bush(
                            Integer.valueOf(p.get(POSITION_X).toString()) / Constant.TILE_SIZE,
                            Integer.valueOf(p.get(POSITION_Y).toString()) / Constant.TILE_SIZE
                    ));
                } else if (decorObject.toString().contains("chest")) {
                    MainZeldo.mapList.get(map.toString()).getDecorObjectArraylist().add(new Chest((String) p.get(STATE),
                            Integer.valueOf(p.get(POSITION_X).toString()) / Constant.TILE_SIZE,
                            Integer.valueOf(p.get(POSITION_Y).toString()) / Constant.TILE_SIZE
                    ));
                }
            }
        }

        Game.setKillThread(false);
        MainZeldo.setGameState(MainZeldo.GameState.valueOf((String)location.get(MAP)));
        MainZeldo.setGameStateChange(true);
    }

    /**
     * Charge les paramètre initiaux comme la position du joueur, objets dynamiques etc... et lance une nouvelle partie.
     */
    public void startNewGame() {
        clearDecorObjectList();

        Game.setCharacterCoordinates(new Integer[] {
                11 * Constant.TILE_SIZE,
                11 * Constant.TILE_SIZE
        });
        List<Integer> direction = new ArrayList<>();
        direction.add(KeyMap.DOWN);
        Game.setDirection(direction);

        MapModel map1 = MainZeldo.mapList.get(MAP_1);
        for (int i = 14; i < 17; i++) {
            map1.getDecorObjectArraylist().add(new Bush(i, 24));
        }
        map1.getDecorObjectArraylist().add(new Sword(12, 13));
        map1.getDecorObjectArraylist().add(new Shield(19, 18));
        Pnj pnjTest = MainZeldo.pnjList.get("pnjTest");
        pnjTest.setCharacterCoordinates(new Integer[]{12 * Constant.TILE_SIZE, 16 * Constant.TILE_SIZE});
        pnjTest.setDirection(1);
        map1.getPnjList().add(pnjTest);

        MapModel map2 = MainZeldo.mapList.get(MAP_2);
        map2.getDecorObjectArraylist().add(new Dagger(map2.getMapWidth()/2 - 8, map2.getMapHeight()/2 + 3));
        map2.getDecorObjectArraylist().add(new BootsLeather(map2.getMapWidth()/2 - 2, map2.getMapHeight()/2 + -2));
        map2.getDecorObjectArraylist().add(new Chest("closed", map2.getMapWidth()/2, map2.getMapHeight()/2 + -2));
        map2.getDecorObjectArraylist().add(new Chest("closed", map2.getMapWidth()/2 + 3, map2.getMapHeight()/2 + -2));

        Pnj pnjTest2 = MainZeldo.pnjList.get("pnjTest2");
        pnjTest2.setCharacterCoordinates(new Integer[]{map2.getMapWidth()/2 * Constant.TILE_SIZE, map2.getMapHeight()/2 * Constant.TILE_SIZE});
        pnjTest2.setDirection(1);
        map2.getPnjList().add(pnjTest2);

        Game.setKillThread(false);
        MainZeldo.setGameState(MainZeldo.GameState.MAP_1);
        MainZeldo.setGameStateChange(true);
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
