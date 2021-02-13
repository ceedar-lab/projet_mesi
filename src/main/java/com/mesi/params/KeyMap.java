package com.mesi.params;

/**
 * Classe qui contient le paramétrages des touches.
 */
public class KeyMap {

    public static final Integer LEFT = 37;
    public static final Integer RIGHT = 39;
    public static final Integer UP = 38;
    public static final Integer DOWN = 40;
    public static final Integer ESCAPE = 27;
    public static final Integer ENTER = 10;
    public static final Integer ATTACK = 32; //touche espace
    public static final Integer STOP = 83; //touche S
    public static final Integer ACTION = 65; //touche A

    private KeyMap() {
        // Constructeur privé utilisé pour cacher le constructeur implicite crée par Java.
    }
}
