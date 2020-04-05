package com.supinfo.project.crashbandicoot.graphics;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Game;
import com.supinfo.project.crashbandicoot.game.Level;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void quadData(float x, float y, int width, int height, float[] color, float size, int xo, int yo) {
        glColor4f(color[0], color[1], color[2], color[3]);
        glTexCoord2f((0 + xo) / size, (0 + yo) / size); glVertex2f(x, y);
        glTexCoord2f((1 + xo) / size, (0 + yo) / size); glVertex2f(x + width, y);
        glTexCoord2f((1 + xo) / size, (1 + yo) / size); glVertex2f(x + width, y + height);
        glTexCoord2f((0 + xo) / size, (1 + yo) / size); glVertex2f(x, y + height);
    }

    public static void renderQuad(float x, float y, int width, int height, float[] color, float size, int xo, int yo) {
        glBegin(GL_QUADS);
            Renderer.quadData(x, y, width, height, color, size, xo, yo);
        glEnd();
    }

    public static void renderEntity(float x, float y, int width, int height, float[] color, float size, int xo, int yo) {
        glBegin(GL_QUADS);
            glColor4f(color[0], color[1], color[2], color[3]);
            glTexCoord2f((0 + xo) / size, (0 + yo) / size); glVertex2f(x, y);
            glTexCoord2f((1 + xo) / size, (0 + yo) / size); glVertex2f(x + width, y);
            glTexCoord2f((1 + xo) / size, (1 + yo) / size); glVertex2f(x + width, y + height);
            glTexCoord2f((0 + xo) / size, (1 + yo) / size); glVertex2f(x, y + height);
        glEnd();
    }

    static boolean loop = true;
    static boolean drawText = false;

    static int time = 0;
    static int frame = 0;

    static float alpha = 0f;

    static String gameOverText = "GAME OVER ! \n\n Appuyer sur [F5] pour lancer\n une nouvelle partie";

    // type 1 = next level, type 2 = game over
    public static void renderBlackOut(int type) {

        float screenScroller = Math.abs(Game.xScroll);

        glColor4f(0f,0f,0f, alpha);
        glBegin(GL_QUADS);
            glVertex2f(screenScroller,0);
            glVertex2f(screenScroller + Component.frameWidth,0);
            glVertex2f(screenScroller + Component.frameWidth, Component.frameHeight);
            glVertex2f(screenScroller, Component.frameHeight);
        glEnd();

        if (loop) {
            time++;
            if (time > 15) {
                frame++;
                if(alpha < 1.1f){
                    alpha += 0.1f;
                } else {
                    loop = false;
                    Level.levelNumber += 1;
                    drawText = true;
                }
                time = 0;
            }
        }

        if(drawText == true) {
            if(type == 1) {
                Header.fontBlackOut.drawString((screenScroller + (Component.frameWidth /2)) - (Header.fontBlackOut.getWidth("LEVEL " + Level.levelNumber)/2), Component.frameHeight / 3, "LEVEL " + Level.levelNumber);
            } else if(type == 2) {
                Header.fontBlackOut.drawString((screenScroller + (Component.frameWidth /2)) - (Header.fontBlackOut.getWidth(gameOverText)/2), Component.frameHeight / 3, gameOverText);
            }
        }

    }

}
