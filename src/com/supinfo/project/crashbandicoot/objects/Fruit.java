package com.supinfo.project.crashbandicoot.objects;

public class Fruit {

    int x, y;
    boolean eat;

    public Fruit(int x, int y, boolean eat) {
        this.x = x;
        this.y = y;
        this.eat = eat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getEat() { return eat; }

    public void setEat(boolean newEat) { eat = newEat; }

}
