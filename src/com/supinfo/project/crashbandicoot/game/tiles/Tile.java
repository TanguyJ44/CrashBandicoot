package com.supinfo.project.crashbandicoot.game.tiles;

import com.supinfo.project.crashbandicoot.graphics.Renderer;

public class Tile {

    public int x, y;
    public int size = 16;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render() {
        Renderer.renderQuad(x * size, y * size, size, size, new float[]{1, 1, 1, 1});
    }

}
