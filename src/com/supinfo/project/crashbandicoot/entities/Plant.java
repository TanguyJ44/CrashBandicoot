package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Delay;

public class Plant extends Entity{

    int level;

    boolean isEnabled = true;

    private int length;
    private int speed;
    private int time;

    private int dir;

    private int swing;
    private int swingCount;

    boolean eating = false;
    boolean playerDetector = false;

    int eatSteps = 2;
    int countSteps = 0;

    Delay delay;

    public Plant(int x, int y, int level) {
        super(x, y);

        this.level = level;

        texture = Texture.plant;
    }

    @Override
    public void init(Level level) {
        length = 60;
        speed = 2;
        time = 0;

        dir = 1;

        swing = 0;
        swingCount = 0;

        delay = new Delay(2, true);
    }

    @Override
    public void update() {
        if(Level.levelNumber == level) {
            if (Player.playerX < x && dir == 0) dir = 1;
            else if (Player.playerX > x && dir == 1) dir = 0;

            if (true) {
                time++;
                if (time > speed) {

                    if(playerDetector == false) {
                        if (swingCount > 8) {
                            swingCount = 0;
                            if (swing == 0) {
                                swing = 1;
                                y+= 1;
                            }
                            else {
                                swing = 0;
                                y-= 1;
                            }
                        }
                    } else {
                        if (swingCount > 8) {
                            swingCount = 0;

                            swing = eatSteps;
                            if(eatSteps == 2) {
                                eatSteps++;
                            } else if(eatSteps == 3){
                                eatSteps--;
                            }

                            System.out.println("plant eat");

                            countSteps++;
                            if(countSteps == 3) {
                                playerDetector = false;
                                eatSteps = 2;
                                countSteps = 0;
                                swing = 1;
                            }
                        }
                    }

                    swingCount++;

                    time = 0;
                }
            }

            // Plant collide detection
            if ((Player.playerX >= x + 32)
                    || (Player.playerX + Player.playerBoxWidth <= x)
                    || (Player.playerY >= y + 40)
                    || (Player.playerY + Player.playerBoxHeight <= y)){
                // Player is not in a area

                if(Player.dir == 0 && (Player.playerX + Player.playerBoxWidth) >= x-15) {
                    if(playerDetector != true) playerDetector = true;
                }

                /*if(Player.dir == 1 && Player.playerX <= (x + 32 + 10)) {
                    if(playerDetector != true) playerDetector = true;
                }*/

            } else {
                eating = true;
                if (Player.tornadoAttack == false) {
                    if(eating == true && delay.talk() == true) {
                        Player.killPlayer = true; // Player death
                        System.out.println("[Player] was killed by Plant !");
                        delay.start();
                    }
                } else {
                    isEnabled = false;
                }
            }
        }
    }

    @Override
    public void render() {
        if(Level.levelFinished != true && Level.levelNumber == level) {
            texture.bind();
                Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, dir, swing);
            texture.unbind();
        }
    }

    public int getLevel() { return level; }

    public boolean getEnabled() { return isEnabled; }

    public void setEnabled(boolean value) { isEnabled = value; }
}
