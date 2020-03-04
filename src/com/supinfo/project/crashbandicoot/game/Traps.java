package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.utiles.ObjectsAnimation;

public class Traps {

    static int time = 0;
    static int speed = 50;

    public static ObjectsAnimation animPique;

    public static void init() {
        animPique = new ObjectsAnimation(50, 0, false);
    }

    static int rand;

    public static void update() {

        animPique.update();

        if (true) {
            time++;
            if (time > speed) {

                rand = 1 + (int)(Math.random() * ((8 - 1) + 1));

                if(rand == 5) {
                    animPique.play();
                }

                time = 0;
            }
        }

        // Traps collide detection
        if ((Player.playerX >= 643 + 32)
                || (Player.playerX + Player.playerBoxWidth <= 643)
                || (Player.playerY >= 170 - animPique.getCurrentCoord() + 64)
                || (Player.playerY + Player.playerBoxHeight <= 170 - animPique.getCurrentCoord())){
            // Player is not in a area
        } else {
            Player.killPlayer = true;
        }

    }

}
