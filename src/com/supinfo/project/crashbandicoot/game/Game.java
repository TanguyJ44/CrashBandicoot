package com.supinfo.project.crashbandicoot.game;

import org.lwjgl.opengl.GL11;

public class Game {

    Level level;

    public static float xScroll, yScroll;

    public Game() {
        level = new Level(40, 40);
    }

    public void init() {
        level.init();
    }

    public void translateView(float xa, float ya) {
        if (xScroll > -167) {
            xScroll += xa;
            yScroll += ya;
        }
    }

    public void update() {
        translateView(-0.3f, 0);
        level.update();
    }

    public void render() {
        GL11.glTranslatef(xScroll, yScroll, 0);
        level.render();
    }

}
