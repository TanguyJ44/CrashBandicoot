package com.supinfo.project.crashbandicoot.utiles;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioControl implements LineListener {

    private static boolean playCompleted;

    private AudioInputStream audioStream = null;
    private AudioFormat format;
    private DataLine.Info info;
    public static Clip audioClip;

    private static Thread audioThread;

    private int duration;

    public void init(File audioFilePath) {

        try {

            audioStream = AudioSystem.getAudioInputStream(audioFilePath);

            if (audioStream != null) {
                System.out.println("Audio init !");
            }

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            duration = (int) audioClip.getMicrosecondLength() / 1000000;

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AudioControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int realTime = 0;
    int cursorTime = 0;

    public void play() {

        playCompleted = false;

        audioThread = new Thread(new Runnable() {
            @Override
            public void run() {

                audioClip.setMicrosecondPosition(0);

                audioClip.start();

                while (!playCompleted) {

                    realTime = (int) audioClip.getMicrosecondPosition() / 1000;

                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                audioClip.close();
                audioThread.currentThread().interrupt();
            }
        });
        audioThread.start();

    }

    public static void stop() {
        playCompleted = true;
        audioClip.close();
        audioThread.currentThread().interrupt();
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }

    }

    public static float getVolume() {
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public static void setVolume(float volume) {
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

}
