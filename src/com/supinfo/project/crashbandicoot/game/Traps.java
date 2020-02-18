package com.supinfo.project.crashbandicoot.game;

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
                    System.out.println("!");
                    animPique.play();
                }

                time = 0;
            }
        }
    }

}
