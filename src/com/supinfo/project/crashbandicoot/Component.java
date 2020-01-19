package com.supinfo.project.crashbandicoot;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Component {

    public static int frameScale = 3;
    public static int frameWidth = 1000 / frameScale;
    public static int frameHeight = 700 / frameScale;

    public boolean run = false;

    public static boolean tick = false;
    public static boolean render = false;

    DisplayMode displaymode = new DisplayMode(frameWidth * frameScale, frameHeight * frameScale);

    public Component() {
        try {
            Display.setDisplayMode(displaymode);

            Display.setTitle("Demake Crash Bandicoot - 1PROJ");

            Display.setResizable(true);
            Display.setFullscreen(false);

            Display.create();

            view2D(frameWidth, frameHeight);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void view2D(int width, int height){
        glViewport(0, 0, width * frameScale, height * frameScale);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, width, height, 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void launch() {
        run = true;
        loop();
    }

    public void loop() {

        long timer = System.currentTimeMillis();

        long before = System.nanoTime();
        double elapsed = 0;
        double nanoSeconds = 1000000000.0 / 60.0;

        int frames = 0;

        while (run) {
            if(Display.isCloseRequested())
                destroy();

            Display.update();

            tick = false;
            render = false;

            long now = System.nanoTime();
            elapsed = now - before;

            if(elapsed > nanoSeconds) {
                before += nanoSeconds;
                tick = true;
            } else {
                render = true;
                frames++;
            }

            if(tick) update();
            if(render) render();

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Display.setTitle("Demake Crash Bandicoot (FPS : " + frames + ")");
                frames = 0;
            }

        }
        exit();
    }

    public void destroy() {
        run = false;
    }

    public void exit(){
        Display.destroy();
        System.exit(0);
    }

    public void update(){

    }

    public void render(){
        view2D(Display.getWidth() / frameScale, Display.getHeight() / frameScale);

        glClear(GL_COLOR_BUFFER_BIT);

        glBegin(GL_QUADS);
            glColor3f(0.5f, 1.0f, 0.8f);
            glVertex2f(16, 16);
            glColor3f(1.0f, 0.8f, 0.8f);
            glVertex2f(16 + 16, 16);
            glColor3f(0.5f, 0.8f, 1.0f);
            glVertex2f(16 + 16, 16 + 16);
            glColor3f(1.0f, 1.0f, 0.8f);
            glVertex2f(16, 16 + 16);
        glEnd();
    }

    public static void main(String[] args) {
        Component component = new Component();

        component.launch();
    }

}
