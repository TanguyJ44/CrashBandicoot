package com.supinfo.project.crashbandicoot.utiles;

import com.supinfo.project.crashbandicoot.entities.Player;

public class Animation {

    // Cette classe permet d'annimer les entitées

    private int frame = 0;
    private int length;
    private int speed;
    private boolean loop = false;

    private boolean playing = false;
    private int time = 0;

    // constructeur de la classe Animation
    public Animation(int length, int speed, boolean loop) {
        this.length = length;
        this.speed = speed;
        this.loop = loop;
    }

    // méthode d'update des valeurs de variable
    public void update() {

        if (playing) {
            time++;
            if (time > speed) {
                frame++;
                if (frame >= length) {
                    if (loop) frame = 0;
                    else frame = length - 1;
                }
                time = 0;
            }
        }
    }

    // méthode pour lancer l'animation
    public void play() {
        playing = true;
    }

    // méthode pour mettre en pause l'animation
    public void pause() {
        playing = false;
    }

    // méthode pour stopper l'animation avant la fin
    public void stop() {
        playing = false;
        frame = 0;
    }

    // getter and setter

    public int getCurrentFrame() {
        return frame;
    }

    public void setCurrentFrame(int newFrame) {
        frame = newFrame;
    }

}
