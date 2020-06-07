package com.supinfo.project.crashbandicoot.utiles;

public class ObjectsAnimation {

    // Cette classe permet d'animer certains objets du jeu

    private int coord = 0;
    private int length;
    private int speed;
    private boolean loop = false;

    private boolean playing = false;
    private int time = 0;

    // constructeur de la classe ObjectsAnimation
    public ObjectsAnimation(int length, int speed, boolean loop) {
        this.length = length;
        this.speed = speed;
        this.loop = loop;
    }

    boolean invert = false;

    // méthode update des valeurs de variable
    public void update() {
        if (playing) {
            time++;
            if (time > speed) {
                if (coord <= length && invert == false) coord++;
                if(coord <= length && invert == true) coord--;
                if (coord == length) invert = true;
                if (coord == 0){
                    invert = false;
                    if(loop == false) playing = false;
                }
                time = 0;
            }
        }
    }

    // méthode de lancement de l'animation
    public void play() {
        playing = true;
    }

    // méthode de mise en pause de l'animation
    public void pause() {
        playing = false;
    }

    // méthode pour stopper l'animation
    public void stop() {
        playing = false;
        coord = 0;
    }

    // getter and setter

    public int getCurrentCoord() {
        return coord;
    }

}
