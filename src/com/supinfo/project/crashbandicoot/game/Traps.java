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

    public static ObjectsAnimation animPique;

    static Delay delay;

    // constructeur de la classe Traps
    public Traps(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;

        texturePique = Texture.pique;

        init();
    }

    // initialisation des valeurs de la classe Traps
    public static void init() {
        animPique = new ObjectsAnimation(50, 0, false);

        delay = new Delay(2, true);
    }

    static int rand;

    // update frame par frame les valeurs de variable
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

            // Détecteur de collision
            if ((Player.playerX+10 >= x + 32)
                    || (Player.playerX+10 + Player.playerBoxWidth-20 <= x)
                    || (Player.playerY+10 >= 170 - animPique.getCurrentCoord() + 64)
                    || (Player.playerY+10 + Player.playerBoxHeight-20 <= 170 - animPique.getCurrentCoord())){
                // Le joueur n'est pas dans la zone
            } else {
                if(delay.talk() == true) {
                    Player.killPlayer = true;
                    System.out.println("[Player] was killed by Traps !");
                    delay.start();
                }
            }
        }

    }

    // méthode de rendu graphique
    public static void render() {
        if(level == Level.levelNumber) {
            texturePique.bind();
                Renderer.renderEntity(x, y - animPique.getCurrentCoord(), 32, 64, Colors.WHITE, 2f, 0, 0);
            texturePique.unbind();
        }
    }

}
