package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Color;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Animation;
import org.lwjgl.input.Keyboard;

public class Player extends Entity{

    Animation anim;
    int dir = 0;

    boolean isJump = false;

    public Player(int x, int y) {
        super(x, y);
        texture = Texture.player;

        anim = new Animation(2, 10, true);

        mass = 0.9f;
        drag = 0.70f;
    }

    @Override
    public void init(Level level) {
        this.level = level;
    }

    float xa, ya;
    float speed = 0.8f;
    @Override
    public void update() {
        ya += level.gravity * mass;

        anim.update();
        anim.pause();

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && x < 450) {
            xa += speed;
            if(dir != 0) dir = 0;
            anim.play();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && x > 0) {
            xa -= speed;
            if(dir != 1) dir = 1;
            anim.play();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            //if(y == 111 && isJump == true) isJump = false;
            if(isGrounded() /*&& isJump == false*/) {
                //isJump = true;
                ya -= 25;
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ya = speed;
        }

        int xStep = (int) Math.abs(xa * 100);
        for (int i = 0; i < xStep; i++) {
            if (!isSolidTile(xa / xStep, 0)) {
                x += xa / xStep;
            } else {
                xa = 0;
            }
        }
        int yStep = (int) Math.abs(ya * 100);
        for (int i = 0; i < yStep; i++) {
            if (!isSolidTile(0, ya / yStep)) {
                y += ya / yStep;
            } else {
                ya = 0;
            }
        }

        xa *= drag;
        ya *= drag;

    }

    @Override
    public void render() {
        texture.bind();
        Renderer.renderEntity(x, y, 32, 40, Color.WHITE, 4.5f, dir, anim.getCurrentFrame());
        texture.unbind();
    }
}
