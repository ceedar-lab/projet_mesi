package com.mesi.resources;

import com.mesi.panels.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    private static final Logger logger = LogManager.getLogger(Player.class);

    Thread player;

    public Player(File file, Boolean loop) {
        if(Game.sound){
            player = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            AudioInputStream inputFile = AudioSystem.getAudioInputStream(file);
                            if (inputFile != null) {
                                AudioFormat baseFormat = inputFile.getFormat();

                                AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
                                        16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

                                AudioInputStream dataIn = AudioSystem.getAudioInputStream(targetFormat, inputFile);

                                byte[] buffer = new byte[4096];

                                DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);
                                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

                                if (line != null) {
                                    line.open();

                                    line.start();
                                    int nBytesRead = 0, nBytesWritten = 0;
                                    while (nBytesRead != - 1) {
                                        nBytesRead = dataIn.read(buffer, 0, buffer.length);
                                        if (nBytesRead != - 1) {
                                            nBytesWritten = line.write(buffer, 0, nBytesRead);
                                        }
                                    }

                                    line.drain();
                                    line.stop();
                                    line.close();

                                    dataIn.close();
                                }

                                inputFile.close();

                                if (!loop) stop();
                            }
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            logger.error("Player unable to read sound : " +e.getMessage());
                            logger.fatal("<--------------- GAME STOP --------------->");
                            System.exit(1);
                        }
                    }
                }
            });
            player.start();
        }
    }

    public void stop() {
        if(player!=null){
            player.stop();
        }
    }
}
