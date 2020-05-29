package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Delay;
import com.supinfo.project.crashbandicoot.utiles.ObjectsAnimation;

public class Traps {

    static int x, y;
    static int level;

    static int time = 0;
    static int speed = 50;

    static Texture texturePique;
    static Texture texturePiqueCover;

    public static ObjectsAnimation animPique;

    static Delay delay;

    public Traps(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;

        texturePique = Texture.pique;
        texturePiqueCover = Texture.piqueCover;

        init();
    }

    public static void init() {
        animPique = new ObjectsAnimation(50, 0, false);

        delay = new Delay(2, true);
    }

    static int rand;

    public static void update() {
        if(level == Level.levelNumber) {
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
            if ((Player.playerX+10 >= 643 + 32)
                    || (Player.playerX+10 + Player.playerBoxWidth-20 <= 643)
                    || (Player.playerY+10 >= 170 - animPique.getCurrentCoord() + 64)
                    || (Player.playerY+10 + Player.playerBoxHeight-20 <= 170 - animPique.getCurrentCoord())){
                // Player is not in a area
            } else {
                if(delay.talk() == true) {
                    Player.killPlayer = true;
                    System.out.println("[Player] was killed by Traps !");
                    delay.start();
                }
            }
        }

    }

    public static void render() {
        if(level == Level.levelNumber) {
            texturePique.bind();
                Renderer.renderEntity(x, y - animPique.getCurrentCoord(), 32, 64, Colors.WHITE, 2f, 0, 0);
            texturePique.unbind();

//            texturePiqueCover.bind();
//                Renderer.renderEntity(x, y, 32, 64, Colors.WHITE, 2f, 0, 0);
//            texturePiqueCover.unbind();
        }
    }

}
