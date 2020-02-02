package com.supinfo.project.crashbandicoot.objects;

public class Fruit {

    int x, y;
    boolean eat;
    int defaultY;

    public Fruit(int x, int y, boolean eat) {
        this.x = x;
        this.y = y;
        this.eat = eat;

        defaultY = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getEat() { return eat; }

    public int getDefaultY() { return defaultY; }

    public void setY(int newY) { y = newY; }

    public void setEat(boolean newEat) { eat = newEat; }

}
