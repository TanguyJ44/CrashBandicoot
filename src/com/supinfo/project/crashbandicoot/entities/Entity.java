package com.supinfo.project.crashbandicoot.entities;

public abstract class Entity {

    private int x, y;
    private boolean removed = false;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
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

}
