package com.mesi.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    AudioInputStream inputFile;
    AudioInputStream dataIn;
    SourceDataLine line;

    private Boolean loop = false;

    Thread player;

    public Player(File file, Boolean loop) {
        //this.file = file;
        player = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        inputFile = AudioSystem.getAudioInputStream(file);
                        if (inputFile != null) {
                            AudioFormat baseFormat = inputFile.getFormat();

                            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
                                    16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

                            dataIn = AudioSystem.getAudioInputStream(targetFormat, inputFile);

                            byte[] buffer = new byte[4096];

                            // get a line from a mixer in the system with the wanted format
                            DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);
                            line = (SourceDataLine) AudioSystem.getLine(info);

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
                            if (loop == false) stop();

                            //playAgain();
                        }
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        // failed
                    }


                }
            }
        });
        player.start();
    }

    public void stop() {
        player.stop();
    }
}
