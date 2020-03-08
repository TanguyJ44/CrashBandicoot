package com.supinfo.project.crashbandicoot.utiles;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class ScreenLoader {

    static boolean isVisible = true;

    static Texture loadScreen;

    public static void init() {
        loadScreen = Texture.load;
    }

    static int time = 0;

    public static void render() {
        if(isVisible == true) {
            loadScreen.bind();
                Renderer.renderEntity(0, 0, Component.frameWidth, Component.frameHeight, Colors.WHITE, 1f, 0, 0);
            loadScreen.unbind();
        }

        if(time < 501) time++;
        if(time == 2) {
            isVisible = false;
            Player.keysEnable = true;
        }

    }

    public static void setVisible(boolean visible) {
        isVisible = visible;
    }

}
