package com.supinfo.project.crashbandicoot.objects;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Clouds {

    int x, y;
    int speed;

    boolean level1;

    Texture texture;

    // constructeur de la classe Clouds
    public Clouds(int x, int y, int speed, boolean level1) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.level1 = level1;

        texture = Texture.cloud;
    }

    int time = 0;
    // méthode d'update des valeurs de variable
    public void update() {
        time++;
        if (time > speed) {
            x++;
            if(Level.levelNumber == 1 && x > 1050) {
                x = -70;
            } else if((Level.levelNumber == 2 || Level.levelNumber == 3) && x > 1950) {
                x = 0;
            }

            time = 0;
        }
    }

    // méthode de rendu graphique des nuages
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 70, 35, Colors.WHITE, 1f, 0, 0);
        texture.unbind();
    }

    // getter and setter

    public boolean getLevel1() { return level1; }

}
