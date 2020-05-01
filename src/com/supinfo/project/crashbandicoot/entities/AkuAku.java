package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class AkuAku extends Entity {

    public static boolean invokAkuaku = false;
    public static int akuakuLife = 0; // De 0 à 2

    public AkuAku(int x, int y) {
        super(x, y);

        texture = Texture.akuaku;
    }

    @Override
    public void init(Level level) { }

    int akuakuDir;
    float draging = 2f;

    @Override
    public void update() {

        if(Player.dir == 0) {
            akuakuDir = 0;

            if(x < Player.playerX - 50) {
                x+=1.5f;
                if(draging != 2f) draging = 2f;
            } else{
                if(draging > 0) draging -= 0.1f;
                x+= draging;
            }
        } else if (Player.dir == 1) {
            akuakuDir = 1;

            if(x > Player.playerX + 50) {
                x-=1.5f;
                if(draging != 2f) draging = 2f;
            } else{
                if(draging > 0) draging -= 0.1f;
                x-= draging;
            }
        }

    }

    @Override
    public void render() {
        if(invokAkuaku == true) {
            texture.bind();
                Renderer.renderEntity(x, y, 25, 25, Colors.WHITE, 5.3f, akuakuLife, akuakuDir);
            texture.unbind();
        }
    }

    public boolean getInvokAkuaku () { return invokAkuaku; }
    public int getAkuakuLife () { return akuakuLife; }

    public void setInvokAkuaku (boolean value) { invokAkuaku = value; }
    public void setAkuakuLife (int value) { akuakuLife = value; }
}
