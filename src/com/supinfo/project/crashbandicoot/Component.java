package com.supinfo.project.crashbandicoot;

import com.supinfo.project.crashbandicoot.game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Component {

    public static int frameScale = 3;
    public static int frameWidth = 1000 / frameScale;
    public static int frameHeight = 750 / frameScale;

    public boolean run = false;

    public static boolean tick = false;
    public static boolean render = false;

    DisplayMode displaymode = new DisplayMode(frameWidth * frameScale, frameHeight * frameScale);

    Game game;

    public Component() {
        display();

        game = new Game();
    }

    public void launch() {
        run = true;
        loop();
    }

    public void loop() {

        game.init();

        long timer = System.currentTimeMillis();

        long before = System.nanoTime();
        double elapsed = 0;
        double nanoSeconds = 1000000000.0 / 60.0;

        int frames = 0;

        long noww = System.currentTimeMillis(), delta;

        while (run) {
            if(Display.isCloseRequested())
                destroy();

            Display.update();

            frameWidth = Display.getWidth() / frameScale;
            frameHeight = Display.getHeight() / frameScale;

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

            delta = -noww + (noww = System.currentTimeMillis());
            if(delta < 5){
                try {
                    Thread.sleep(5-delta);
                    noww += 5-delta;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
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
        game.update();
    }

    public void render(){
        view2D(frameWidth, frameHeight);
        glClear(GL_COLOR_BUFFER_BIT);

        game.render();
    }

    public void display() {
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

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void main(String[] args) {
        Component component = new Component();

        component.launch();
    }

}
