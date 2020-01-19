package com.supinfo.project.crashbandicoot.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void quadData(int x, int y, int width, int height) {
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
    }

    public static void renderQuad(int x, int y, int width, int height) {
        glBegin(GL_QUADS);
            Renderer.quadData(x, y, width, height);
        glEnd();
    }

}
