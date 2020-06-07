package com.supinfo.project.crashbandicoot.utiles;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

import java.io.File;

public class ScreenLoader {

    // Cette classe permet de générer et d'afficher l'écran de chargement du jeu

    static boolean isVisible = true;
    static boolean soundIsPlaying = false;

    static Texture loadScreen;

    static AudioControl audioControl;

    // fonction d'initialisation de l'écran de chargement
    public static void init() {
        loadScreen = Texture.load;

        audioControl = new AudioControl();
    }

    static int time = 0;

    // fonction de rendu graphique du chargement
    public static void render() {
        if(isVisible == true) {
            loadScreen.bind();
                Renderer.renderEntity(0, 0, Component.frameWidth+2, Component.frameHeight+2, Colors.WHITE, 1f, 0, 0);
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

            Level.startLevelSound(1);
        }

    }

    // getter and setter

    public static void setVisible(boolean visible) {
        isVisible = visible;
    }

}
