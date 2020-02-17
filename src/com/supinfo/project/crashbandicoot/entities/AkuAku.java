package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class AkuAku extends Entity {

    public static boolean invokAkuaku = false;
    public static int akuakuLife = 2;

    public AkuAku(int x, int y) {
        super(x, y);

        texture = Texture.akuaku;
    }

    @Override
    public void init(Level level) { }

    @Override
    public void update() {
        if(Player.dir == 0) {
            x = Player.playerX -30;
        } else if (Player.dir == 1) {
            x = Player.playerX +40;
        }
        y = Player.playerY - 20;
    }

    @Override
    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 25, 25, Colors.WHITE, 5.3f, akuakuLife, 0);
        texture.unbind();
    }
}
