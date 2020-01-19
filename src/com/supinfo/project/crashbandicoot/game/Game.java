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
        xScroll += xa;
        yScroll += ya;
    }

    public void update() {
        translateView(-1, 0);
        level.update();
    }

    public void render() {
        GL11.glTranslatef(xScroll, yScroll, 0);
        level.render();
    }

}
