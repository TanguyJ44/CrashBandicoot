package com.supinfo.project.crashbandicoot.game.tiles;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.game.Game;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Tile {

    public int x, y;
    public int size = 500;

    int xo = 0, yo = 0;

    Tiles tile;

    public enum Tiles {
        GRASS, DIRTH, WATHER
    }

    public Tile(int x, int y, Tiles tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;

        if(tile == Tiles.GRASS) yo = 0;
        if(tile == Tiles.DIRTH) yo = 1;
        if(tile == Tiles.WATHER) yo = 2;
    }

    public void render() {
        float x0 = x + Game.xScroll / 16;
        float y0 = y + Game.yScroll / 16;

        float x1 = x + 1 + Game.xScroll / 16;
        float y1 = y + 1 + Game.yScroll / 16;

        //if(x1 < 0 || y1 < 0 || x0 > Component.frameWidth / 16 || y0 > Component.frameHeight / 16) return;
        Texture.tiles.bind();
            glBegin(GL_QUADS);
                Renderer.quadData(x * size, y * size, size, size, new float[]{1, 1, 1, 1}, xo, yo);
            glEnd();
        Texture.tiles.unbind();
    }

}
