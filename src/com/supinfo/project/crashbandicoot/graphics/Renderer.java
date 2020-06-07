package com.supinfo.project.crashbandicoot.graphics;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Game;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.utiles.AudioControl;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    // Cette classe est un peut prise de tête car elle rend sous la forme de carré les diffèrentes textures.
    // OpenGL nous aide à faire cela sous la forme de vecteur 2D

    public static AudioControl audioControl = new AudioControl();

    // fonction de création des carré 2D de rendu
    public static void quadData(float x, float y, int width, int height, float[] color, float size, int xo, int yo) {
        glColor4f(color[0], color[1], color[2], color[3]);
        glTexCoord2f((0 + xo) / size, (0 + yo) / size); glVertex2f(x, y);
        glTexCoord2f((1 + xo) / size, (0 + yo) / size); glVertex2f(x + width, y);
        glTexCoord2f((1 + xo) / size, (1 + yo) / size); glVertex2f(x + width, y + height);
        glTexCoord2f((0 + xo) / size, (1 + yo) / size); glVertex2f(x, y + height);
    }

    // fonction de rendu à l'écran des carré 2D
    public static void renderQuad(float x, float y, int width, int height, float[] color, float size, int xo, int yo) {
        glBegin(GL_QUADS);
            Renderer.quadData(x, y, width, height, color, size, xo, yo);
        glEnd();
    }

    // fonction de rendu des entités sous un format différent
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
    static boolean invert = false;

    static int time = 0;
    static int frame = 0;

    static float alpha = 0f;

    static int count = 0;

    // fonction d'affichage du changement de level et du game-over
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
                if(invert == false) {
                    if(alpha < 1.1f){
                        alpha += 0.1f;
                    } else {
                        loop = false;
                        if (type == 1) {
                            Level.levelNumber += 1;

                            if (Level.levelSoundIsPlaying == 1) Level.lvl1Sound.stop();
                            if (Level.levelSoundIsPlaying == 2) Level.lvl2Sound.stop();
                            if (Level.levelSoundIsPlaying == 3) Level.lvl3Sound.stop();

                            audioControl.init(new File("./res/sounds/nextlvl.wav"));
                            audioControl.play();
                        }
                        drawText = true;
                        if (type == 2) {
                            if (Level.levelSoundIsPlaying == 1) Level.lvl1Sound.stop();
                            if (Level.levelSoundIsPlaying == 2) Level.lvl2Sound.stop();
                            if (Level.levelSoundIsPlaying == 3) Level.lvl3Sound.stop();

                            audioControl.init(new File("./res/sounds/gameover.wav"));
                            audioControl.play();

                            Level.reloadGameOver();
                        }
                    }
                } else {
                    if(alpha > 0f){
                        alpha -= 0.1f;
                        //if(Level.levelFinished) Level.levelFinished = false;
                    } else {
                        renderBlackOutReload();
                    }
                }
                time = 0;
            }
        }

        if(drawText == true) {
            if(type == 1) {
                Header.fontBlackOut.drawString((screenScroller + (Component.frameWidth /2)) - (Header.fontBlackOut.getWidth("LEVEL " + Level.levelNumber)/2), Component.frameHeight / 3, "LEVEL " + Level.levelNumber);
            } else if(type == 2) {
                Texture.game_over.bind();
                    Renderer.renderEntity(Math.abs(Game.xScroll), 0, Component.frameWidth, Component.frameHeight, Colors.WHITE, 1f, 0, 0);
                Texture.game_over.unbind();
            }

            if(type == 1) {
                count++;
                if(count > 300) {
                    invert = true;
                    loop = true;
                    drawText = false;
                }
            }
        }
    }

    // remise à 0 du rendu des changements de niveau et du game-over
    public static void renderBlackOutReload() {
        drawText = false;
        loop = true;
        count = 0;
        invert = false;
        time = 0;
        frame = 0;

        Level.levelFinished = false;
        Player.keysEnable = true;

        if(Level.levelNumber == 2) {
            if (Level.levelSoundIsPlaying == 1) Level.lvl1Sound.stop();
            if (Level.levelSoundIsPlaying == 3) Level.lvl3Sound.stop();
            Level.startLevelSound(2);
        }
        if(Level.levelNumber == 3) {
            if (Level.levelSoundIsPlaying == 1) Level.lvl1Sound.stop();
            if (Level.levelSoundIsPlaying == 2) Level.lvl2Sound.stop();
            Level.startLevelSound(3);
        }
    }

}
