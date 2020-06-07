package com.supinfo.project.crashbandicoot.graphics;

public class Colors {

    // Cette classe permet d'utiliser les propriétés RVB du système de rendu du jeu

    public static final float[] WHITE = new Colors(1, 1, 1, 1).getColor();
    public static final float[] BLACK = new Colors(0, 0, 0, 1).getColor();

    public static final float[] RED = new Colors(1, 0, 0, 1).getColor();
    public static final float[] GREEN = new Colors(0, 1, 0, 1).getColor();
    public static final float[] BLUE = new Colors(0, 0, 1, 1).getColor();

    public float r, g, b, a;

    // constructeur de la classe Colors
    public Colors(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    // getter and setter
    public float[] getColor() {
        return new float[]{r, g, b, a};
    }

}
