package com.supinfo.project.crashbandicoot.utiles;

public class ObjectsAnimation {

    private int coord = 0;
    private int length;
    private int speed;
    private boolean loop = false;

    private boolean playing = false;
    private int time = 0;

    public ObjectsAnimation(int length, int speed, boolean loop) {
        this.length = length;
        this.speed = speed;
        this.loop = loop;
    }

    boolean invert = false;

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

    public void play() {
        playing = true;
    }

    public void pause() {
        playing = false;
    }

    public void stop() {
        playing = false;
        coord = 0;
    }

    public int getCurrentCoord() {
        return coord;
    }

}
