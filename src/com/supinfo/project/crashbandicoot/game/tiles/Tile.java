package com.supinfo.project.crashbandicoot.game.tiles;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.game.Game;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Tile {

    public int x, y;
    public int size = 32;

    public int xo;
    public int yo;

    Tiles tile;

    public enum Tiles {
        BG, COL
    }

    public Tile(int x, int y, int xo, int yo, Tiles tile) {
        this.x = x;
        this.y = y;
        this.xo = xo;
        this.yo = yo;
        this.tile = tile;
    }

    public void render() {
        float x0 = x + Game.xScroll / 32;
        float y0 = y + Game.yScroll / 32;

        float x1 = x + 1 + Game.xScroll / 32;
        float y1 = y + 1 + Game.yScroll / 32;

        if(x1 < 0 || y1 < 0 || x0 > Component.frameWidth / 16 || y0 > Component.frameHeight / 16) return;
        if(Level.levelNumber == 1) {
            Texture.tilesLvl1.bind();
                glBegin(GL_QUADS);
                    Renderer.quadData(x * size, y * size, size, size, Colors.WHITE, 32.0f, xo, yo);
                glEnd();
            Texture.tilesLvl1.unbind();
        } else if (Level.levelNumber == 2) {
            Texture.tilesLvl2.bind();
                glBegin(GL_QUADS);
                    Renderer.quadData(x * size, y * size, size, size, Colors.WHITE, 32.0f, xo, yo);
                glEnd();
            Texture.tilesLvl2.unbind();
        }
    }

}
