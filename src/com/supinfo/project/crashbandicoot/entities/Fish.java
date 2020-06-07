package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Delay;

public class Fish extends Entity {

    int level;

    boolean isEnabled = true;

    private int length;
    private int speed;
    private int time;
    private int coord;

    Texture textureFishCover;
    Texture textureFishCoverLvl3;

    Delay delay;

    // constructeur de la classe Fish
    public Fish(int x, int y, int level) {
        super(x, y);

        this.level = level;

        texture = Texture.fish;
    }

    // méthode d'initialisation des paramètres lors de l'initialisation de l'objet
    @Override
    public void init(Level level) {
        length = 50;
        speed = 0;
        time = 0;
        coord = 0;

        textureFishCover = Texture.fishCover;
        textureFishCoverLvl3 = Texture.fishCoverLvl3;

        delay = new Delay(2, true);
    }

    boolean invert = false;
    int dir = 0;

    // méthode d'update des valeurs de variable frame par frame
    @Override
    public void update() {
        if(Level.levelNumber == level) {
            if (true) {
                time++;
                if (time > speed) {
                    if (coord <= length && invert == false) coord++;
                    if(coord <= length && invert == true) coord--;
                    if (coord == length) {
                        invert = true;
                        dir = 1;
                    }
                    if (coord == 0) {
                        invert = false;
                        dir = 0;
                    }
                    time = 0;
                }
            }


            // Détecteur de collision
            if ((Player.playerX+10 >= x + 25)
                    || (Player.playerX+10 + Player.playerBoxWidth-20 <= x)
                    || (Player.playerY+10 >= y - coord + 35)
                    || (Player.playerY+10 + Player.playerBoxHeight-20 <= y - coord)){
                // Le joueur n'est pas dans la zone
            } else {
                if(delay.talk() == true) {
                    // Le joueur meurt
                    Player.killPlayer = true;
                    delay.start();
                    System.out.println("[Player] was killed by Fish !");
                }
            }
        }
    }

    // méthode de rendu graphique frame par grape
    @Override
    public void render() {
        if(Level.levelFinished != true && Level.levelNumber == level) {
            texture.bind();
                Renderer.renderEntity(x, y - coord, 25, 35, Colors.WHITE, 5f, dir, 0);
            texture.unbind();

            if(Level.levelNumber != 3) {
                textureFishCover.bind();
                    Renderer.renderEntity(x, y, 25, 35, Colors.WHITE, 1f, 0, 0);
                textureFishCover.unbind();
            } else {
                textureFishCoverLvl3.bind();
                    Renderer.renderEntity(x-4, y+2, 52, 52, Colors.WHITE, 1f, 0, 0);
                textureFishCoverLvl3.unbind();
            }
        }
    }

    // getter and setter

    public int getLevel() { return level; }

    public boolean getEnabled() { return isEnabled; }

}
