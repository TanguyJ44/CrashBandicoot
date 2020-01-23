package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public abstract class Entity {

    protected int x, y;
    protected boolean removed = false;
    protected Texture texture;
    protected Level level;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void init(Level level);
    public abstract void update();
    public abstract void render();

    public boolean isSolidTile(float xa, float ya) {
        //System.out.println("x:" + (int) (x + xa) / 16 + " y:" + (int) (y + ya) / 16);
        if (level.getSolidTile((int) (x + xa) / 16, (int) (y + ya) / 16) != null) return true;
        return false;
    }

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
