package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Crab extends Entity {

    private int length;
    private int speed;
    private int time;
    private int coord;

    private int dir;
    private int dirCount;

    boolean invert = false;

    public Crab(int x, int y) {
        super(x, y);

        texture = Texture.crab;
    }

    @Override
    public void init(Level level) {
        length = 60;
        speed = 2;
        time = 0;
        coord = 0;

        dir = 0;
        dirCount = 0;
    }

    @Override
    public void update() {
        if (true) {
            time++;
            if (time > speed) {
                if (coord <= length && invert == false) coord++;
                if(coord <= length && invert == true) coord--;
                if (coord == length) invert = true;
                if (coord == 0) invert = false;

                if(dirCount > 5) {
                    dirCount = 0;
                    if(dir == 0) dir = 1;
                    else dir = 0;
                }

                dirCount++;

                time = 0;
            }
        }

        // Crab collide detection
        if ((Player.playerX >= x + coord + 35)
                || (Player.playerX + Player.playerBoxWidth <= x + coord)
                || (Player.playerY >= y + 35)
                || (Player.playerY + Player.playerBoxHeight <= y)){
            // Player is not in a area
        } else {
            if (Player.tornadoAttack == false) {
                // Player death
                Player.killPlayer = true;

                if(Player.dir == 0) x +=5;
                else x-=5;

            } else {
                // Crab kill
            }

        }
    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x + coord, y, 25, 25, Colors.WHITE, 2f, dir, 0);
        texture.unbind();
    }
}
