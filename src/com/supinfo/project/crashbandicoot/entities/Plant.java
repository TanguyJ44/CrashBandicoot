package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Plant extends Entity{

    int dir;

    boolean eating = false;

    public Plant(int x, int y) {
        super(x, y);

        texture = Texture.plant;
    }

    @Override
    public void init(Level level) {
        dir = 1;
    }

    @Override
    public void update() {
        if (Player.playerX < x && dir == 0) dir = 1;
        else if (Player.playerX > x && dir == 1) dir = 0;

        // Plant collide detection
        if ((Player.playerX >= x + 32)
                || (Player.playerX + Player.playerBoxWidth <= x)
                || (Player.playerY >= y + 40)
                || (Player.playerY + Player.playerBoxHeight <= y)){
            // Player is not in a area
        } else {
            if (Player.tornadoAttack == false) {
                if(eating == true) Player.killPlayer = true; // Player death
            } else {
                // Plant kill
            }
        }


    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, dir, 0);
        texture.unbind();
    }
}
