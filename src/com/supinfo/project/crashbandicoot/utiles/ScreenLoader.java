package com.supinfo.project.crashbandicoot.utiles;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

import java.io.File;

public class ScreenLoader {

    static boolean isVisible = true;
    static boolean soundIsPlaying = false;

    static Texture loadScreen;

    static AudioControl audioControl;

    public static void init() {
        loadScreen = Texture.load;

        audioControl = new AudioControl();
    }

    static int time = 0;

    public static void render() {
        if(isVisible == true) {
            loadScreen.bind();
                Renderer.renderEntity(0, 0, Component.frameWidth, Component.frameHeight, Colors.WHITE, 1f, 0, 0);
            loadScreen.unbind();
        }

        if(soundIsPlaying == false) {
            soundIsPlaying = true;

            Level.wompasSound.init(new File("./res/sounds/loading.wav"));
            Level.wompasSound.play();
        }

        if(time < 801) time++;
        if(time == 800) {
            isVisible = false;
            Player.keysEnable = true;
        }

    }

    public static void setVisible(boolean visible) {
        isVisible = visible;
    }

}
