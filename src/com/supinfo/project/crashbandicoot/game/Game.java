package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.Component;
import org.lwjgl.opengl.GL11;

public class Game {

    Level level;

    public static float xScroll, yScroll;

    public Game() {
        level = new Level(80, 80);
    }

    public void init() {
        level.init();
    }

    public void translateView(float xa, float ya) {
        /*if (xScroll > -167) {
            xScroll = xa;
            yScroll = ya;
        }*/
        xScroll = xa;
        yScroll = ya;
    }

    float xa = 0, ya = 0;
    public void update() {
        level.update();

        if(Level.getPlayer().getX() > Component.frameWidth / 2 - 16) {
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
