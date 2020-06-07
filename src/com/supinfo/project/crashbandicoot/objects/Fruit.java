package com.supinfo.project.crashbandicoot.objects;

public class Fruit {

    int x, y;
    boolean eat;
    int defaultY;
    int level;

    // constructeur classe Fruit
    public Fruit(int x, int y, boolean eat, int level) {
        this.x = x;
        this.y = y;
        this.eat = eat;
        this.level = level;

        defaultY = y;
    }

    // quelques getter and setter par-ci par-là ...

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getEat() { return eat; }

    public int getLevel() { return level; }

    public int getDefaultY() { return defaultY; }

    public void setY(int newY) { y = newY; }

    public void setEat(boolean newEat) { eat = newEat; }
}
