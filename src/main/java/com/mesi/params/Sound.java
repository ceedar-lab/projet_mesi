package com.mesi.params;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static File MENU = new File("src/main/resources/sounds/menu.wav");
    public static File MENU_CLIC = new File("src/main/resources/sounds/menu_clic.wav");

    public static void play(File file) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.start();
    }
}
