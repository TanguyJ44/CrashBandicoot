package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Fish extends Entity {

    public Fish(int x, int y) {
        super(x, y);

        texture = Texture.fish;
    }

    @Override
    public void init(Level level) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 25, 35, Colors.WHITE, 5f, 0, 0);
        texture.unbind();
    }
}
