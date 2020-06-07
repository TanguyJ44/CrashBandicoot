package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import org.lwjgl.opengl.GL11;

public class Game {

    Level level;

    public static float xScroll, yScroll;

    // Constructeur de la classe
    public Game() {
        level = new Level(160, 160);
    }

    // fonction d'initialisation des levels du jeu
    public void init() {
        level.init();
    }

    // fonction de déplacement de la caméra (slide) de gauche a droite (ou inversement)
    public void translateView(float xa, float ya) {
        xScroll = xa;
        yScroll = ya;
    }

    float xa = 0, ya = 0;
    // fonction d'update frmae par frame des valeurs du terrain
    public void update() {
        level.update();

        if(Level.getPlayer().getX() > Component.frameWidth / 2 - 16) {
            if (Level.levelNumber == 1 && Player.playerX < 839 || Level.levelNumber == 2 && Player.playerX < 1860 || Level.levelNumber == 3 && Player.playerX < 1860)
                xa = -Level.getPlayer().getX() + Component.frameWidth / 2 - 16;
        } else {
            xa = -0.3f;
        }

        translateView(xa, ya);
    }

    // Bon tu commence à comprendre l'affaire, on a ici une belle petite méthode de rendu en 2 lignes .... Incroyable non ?
    public void render() {
        GL11.glTranslatef(xScroll, yScroll, 0);
        level.render();
    }

}
