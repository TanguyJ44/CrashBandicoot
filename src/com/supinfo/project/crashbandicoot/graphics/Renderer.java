package com.supinfo.project.crashbandicoot.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void quadData(int x, int y, int width, int height, float[] color, int xo, int yo) {
        /*glColor4f(color[0], color[1], color[2], color[3]);
        glTexCoord2f((0 + xo) / 32.0f, (0 + yo) / 32.0f); glVertex2f(x, y);
        glTexCoord2f((1 + xo) / 32.0f, (0 + yo) / 32.0f); glVertex2f(x + width, y);
        glTexCoord2f((1 + xo) / 32.0f, (1 + yo) / 32.0f); glVertex2f(x + width, y + height);
        glTexCoord2f((0 + xo) / 32.0f, (1 + yo) / 32.0f); glVertex2f(x, y + height);*/

        /*glColor4f(1, 1, 1, 1);

        glTexCoord2f(0, 0);
        glVertex2f(x, y);

        glTexCoord2f(0, 1);
        glVertex2f(x + width, y);

        glTexCoord2f(1, 1);
        glVertex2f(x + width, y + height);

        glTexCoord2f(1, 0);
        glVertex2f(x, y + height);*/
    }

    public static void renderQuad(int x, int y, int width, int height, float[] color, int xo, int yo) {
        glBegin(GL_QUADS);
            Renderer.quadData(x, y, width, height, color, xo, yo);
        glEnd();
    }

}
