package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Color;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import org.lwjgl.input.Keyboard;

public class Player extends Entity{

    int xo = 0;

    public Player(int x, int y) {
        super(x, y);
        texture = Texture.player;
    }

    @Override
    public void init(Level level) {
        this.level = level;
    }

    float xa, ya;
    @Override
    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && x < 450) {
            xa = 1;
            if(xo != 0) xo = 0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && x > 0) {
            xa = -1;
            if(xo != 1) xo = 1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            ya = -1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ya = +1;
        }

        if(!isSolidTile(xa, 0)) {
            x += xa;
            xa = 0;
        }
        if(!isSolidTile(0, ya)) {
            y += ya;
            ya += 0;
        }

        xa = 0;
        ya = 0;
    }

    @Override
    public void render() {
        texture.bind();
        Renderer.renderEntity(x, y, 32, 40, Color.WHITE, 4.5f, xo, 0);
        texture.unbind();
    }
}
