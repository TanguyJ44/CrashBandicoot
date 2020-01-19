package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.Component;
import com.supinfo.project.crashbandicoot.graphics.Renderer;

public class Game {

    public Game() {

    }

    public void init() {

    }

    public void update() {

    }

    public void render() {
        for(int x = 0; x < Component.frameWidth / 16; x++) {
            for(int y = 0; y < Component.frameHeight / 16; y++) {
                Renderer.renderQuad(x * 17, y * 17, 16, 16, new float[]{1, 1, 1, 1});
            }
        }
    }

}
