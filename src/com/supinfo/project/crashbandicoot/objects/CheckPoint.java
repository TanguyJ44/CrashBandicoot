package com.supinfo.project.crashbandicoot.objects;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.AudioControl;

import java.io.File;

public class CheckPoint {

    int x, y;
    boolean checked;
    int level;

    Texture texturePost, textureFlag;

    boolean upFlag = false;
    int yUp;

    AudioControl controlSound;

    public CheckPoint (int x, int y, boolean checked, int level) {
        this.x = x;
        this.y = y;
        this.checked = checked;
        this.level = level;

        texturePost = Texture.postCP;
        textureFlag = Texture.flagCP;

        yUp = 0;

        controlSound = new AudioControl();
    }

    public void update() {

        if(!checked && yUp !=0) yUp = 0;

        if ((Player.playerX >= x + 20)
                || (Player.playerX + Player.playerBoxWidth <= x)
                || (Player.playerY >= y + 20)
                || (Player.playerY + Player.playerBoxHeight <= y)){
            // Player is not in a area
        } else {
            checked = true;
            upFlag = true;
        }

        if(upFlag == true) {
            if (yUp == 0) {
                controlSound.init(new File("./res/sounds/checkpoint.wav"));
                controlSound.play();
            }
            if(yUp == 9) {
                upFlag = false;
            }
            if(yUp < 9) yUp++;
        }
    }

    public void render() {
        texturePost.bind();
            Renderer.renderEntity(x, y, 20, 20, Colors.WHITE, 1f, 0, 0);
        texturePost.unbind();

        textureFlag.bind();
            Renderer.renderEntity(x+6, y+12-yUp, 15, 15, Colors.WHITE, 1f, 0, 0);
        textureFlag.unbind();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getChecked() { return checked; }

    public int getLevel() { return level; }

    public void setChecked(boolean value) { checked = value; }

}
