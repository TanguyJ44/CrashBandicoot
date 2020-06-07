package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public abstract class Entity {

    protected float x, y;
    protected boolean removed = false;
    protected Texture texture;
    protected Level level;
    protected float mass;
    protected float drag;

    // constructeur de la classe Entity
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void init(Level level);
    public abstract void update();
    public abstract void render();

    // fonction de vérification du contact ou non du joueur sur le sol (tile = tuile du sol et/ou des caisses)
    public boolean isSolidTile(float xa, float ya) {

        int x0 = (int) (x + xa) / 16;
        int x1 = (int) (x + xa) / 16;
        int y0 = (int) (y + ya) / 16;
        int y1 = (int) (y + ya) / 16;

        if (level.getSolidTile(x0, y0) != null) return true;
        if (level.getSolidTile(x1, y0) != null) return true;
        if (level.getSolidTile(x1, y1) != null) return true;
        if (level.getSolidTile(x0, y1) != null) return true;
        return false;
    }

    // fonction de vérification si les le joueur est en saut ou non par rapport au sol / caisses
    public boolean isGrounded() {
        if (level.getSolidTile((int)(x + 5) / 16,(int)(y + 14.1) / 16) != null) return true;
        return false;
    }

    // getter and setter

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean getRemoved() {
        return removed;
    }

    public Texture getTexture() {
        return texture;
    }

}
