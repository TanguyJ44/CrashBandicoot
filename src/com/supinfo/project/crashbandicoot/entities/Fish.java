package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Delay;

public class Fish extends Entity {

    private int length;
    private int speed;
    private int time;
    private int coord;

    Texture textureFishCover;

    Delay delay;

    public Fish(int x, int y) {
        super(x, y);

        texture = Texture.fish;
    }

    @Override
    public void init(Level level) {
        length = 50;
        speed = 0;
        time = 0;
        coord = 0;

        textureFishCover = Texture.fishCover;

        delay = new Delay(2, true);
    }

    boolean invert = false;
    int dir = 0;

    @Override
    public void update() {

        if (true) {
            time++;
            if (time > speed) {
                if (coord <= length && invert == false) coord++;
                if(coord <= length && invert == true) coord--;
                if (coord == length) {
                    invert = true;
                    dir = 1;
                }
                if (coord == 0) {
                    invert = false;
                    dir = 0;
                }
                time = 0;
            }
        }


        // Fish collide detection
        if ((Player.playerX >= x + 25)
                || (Player.playerX + Player.playerBoxWidth <= x)
                || (Player.playerY >= y - coord + 35)
                || (Player.playerY + Player.playerBoxHeight <= y - coord)){
            // Player is not in a area
        } else {
            if(delay.talk() == true) {
                Player.killPlayer = true;
                delay.start();
            }
        }


    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y - coord, 25, 35, Colors.WHITE, 5f, dir, 0);
        texture.unbind();

        textureFishCover.bind();
            Renderer.renderEntity(790, 150, 25, 35, Colors.WHITE, 1f, 0, 0);
        textureFishCover.unbind();
    }

}