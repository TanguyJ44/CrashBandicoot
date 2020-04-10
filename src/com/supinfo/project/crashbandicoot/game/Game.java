package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import org.lwjgl.opengl.GL11;

public class Game {

    Level level;

    public static float xScroll, yScroll;

    public Game() {
        level = new Level(160, 160);
    }

    public void init() {
        level.init();
    }

    public void translateView(float xa, float ya) {
        xScroll = xa;
        yScroll = ya;
    }

    float xa = 0, ya = 0;
    public void update() {
        level.update();

        if(Level.getPlayer().getX() > Component.frameWidth / 2 - 16) {
            if (Level.levelNumber == 1 && Player.playerX < 839 || Level.levelNumber == 2 && Player.playerX < 1698)
                xa = -Level.getPlayer().getX() + Component.frameWidth / 2 - 16;
        } else {
            xa = -0.3f;
        }

        translateView(xa, ya);
    }

    public void render() {
        GL11.glTranslatef(xScroll, yScroll, 0);
        level.render();
    }

}
