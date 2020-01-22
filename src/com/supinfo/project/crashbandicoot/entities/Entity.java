package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.graphics.Texture;

public abstract class Entity {

    protected int x, y;
    protected boolean removed = false;
    protected Texture texture;

    public Entity(int x, int y) {
        this.x = x * 16;
        this.y = y * 16;
    }

    public abstract void update();
    public abstract void render();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getRemoved() {
        return removed;
    }

    public Texture getTexture() {
        return texture;
    }

}
