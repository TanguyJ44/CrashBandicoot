package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Delay;

public class Plant extends Entity{

    private int length;
    private int speed;
    private int time;

    private int dir;

    private int swing;
    private int swingCount;

    boolean eating = false;

    Delay delay;

    public Plant(int x, int y) {
        super(x, y);

        texture = Texture.plant;
    }

    @Override
    public void init(Level level) {
        length = 60;
        speed = 2;
        time = 0;

        dir = 1;

        swing = 0;
        swingCount = 0;

        delay = new Delay(2, true);
    }

    @Override
    public void update() {
        if (Player.playerX < x && dir == 0) dir = 1;
        else if (Player.playerX > x && dir == 1) dir = 0;

        if (true) {
            time++;
            if (time > speed) {

                if (swingCount > 8) {
                    swingCount = 0;
                    if (swing == 0) {
                        swing = 1;
                        y+= 1;
                    }
                    else {
                        swing = 0;
                        y-= 1;
                    }
                }

                swingCount++;

                time = 0;
            }
        }

        // Plant collide detection
        if ((Player.playerX >= x + 32)
                || (Player.playerX + Player.playerBoxWidth <= x)
                || (Player.playerY >= y + 40)
                || (Player.playerY + Player.playerBoxHeight <= y)){
            // Player is not in a area
        } else {
            if (Player.tornadoAttack == false) {
                if(eating == true && delay.talk() == true) {
                    Player.killPlayer = true; // Player death

                    delay.start();
                }
            } else {
                // Plant kill
            }
        }


    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, dir, swing);
        texture.unbind();
    }
}
