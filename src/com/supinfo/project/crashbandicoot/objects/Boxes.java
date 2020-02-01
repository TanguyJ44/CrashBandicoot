package com.supinfo.project.crashbandicoot.objects;

public class Boxes {

    int x, y;
    int numberBreak;
    boolean tornadoBreak;
    boolean solid;
    boolean isBreak;

    Box box;

    public enum Box {
        BASIC, JUMP, ARROW, AKUAKU, CRASH, IRON, TNT, NITRO
    }

    public Boxes(int x, int y, int numberBreak, boolean tornadoBreak, boolean solid, boolean isBreak, Box box) {
        this.x = x;
        this.y = y;
        this.numberBreak = numberBreak;
        this.tornadoBreak = tornadoBreak;
        this.solid = solid;
        this.isBreak = isBreak;
        this.box = box;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getBreak() {
        return isBreak;
    }

    public boolean getTornadoBreak() {
        return tornadoBreak;
    }

    public void setBreak(boolean newBreak) {
        isBreak = newBreak;
    }

}
