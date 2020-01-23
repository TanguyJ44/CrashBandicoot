package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.graphics.Color;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Player extends Entity{

    public Player(int x, int y) {
        super(x, y);
        texture = Texture.player;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        texture.bind();
        Renderer.renderEntity(x, y, 32, 40, Color.WHITE, 4.5f, 0, 0);
        texture.unbind();
    }
}
