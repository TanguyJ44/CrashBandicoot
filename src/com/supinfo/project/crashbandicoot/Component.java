package com.supinfo.project.crashbandicoot;

import com.supinfo.project.crashbandicoot.game.Game;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.utiles.driver.Interaction;
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

    public static int soundLevelEnabled = -1;

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

        while (run) {
            if(Display.isCloseRequested()){
                destroy();
                Level.wompasSound.destroy();
                System.out.println("Audio destroy !");
            }

            Display.update();
            Display.sync(200);

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

        }
        exit();
    }

    public void destroy() {
        run = false;
    }

    public void exit(){
        Display.destroy();
        Interaction.disconnect();
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

            Display.setResizable(false);
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

        for(String startArgs : args){
            if(startArgs.equalsIgnoreCase("gamepad")) {
                Interaction.connect(2236);
            }
            if(startArgs.contains("sound")) {
                System.out.println("+Args sound : " + Integer.parseInt(startArgs.substring(5)));
                soundLevelEnabled = Integer.parseInt(startArgs.substring(5));
            }
        }

        Component component = new Component();
        component.launch();
    }

}
