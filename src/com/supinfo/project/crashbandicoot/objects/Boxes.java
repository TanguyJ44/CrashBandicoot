package com.supinfo.project.crashbandicoot.objects;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;

public class Boxes {

    int x, y;
    int numberBreak;
    boolean tornadoBreak;
    boolean isBreak;
    int level;

    int ssPositionX;
    int ssPositionY;

    int constY;

    Texture texture;

    BoxType boxType;

    public enum BoxType {
        BASIC, JUMP, ARROW, AKUAKU, CRASH, IRON, TNT, NITRO
    }

    public Boxes(int x, int y, int numberBreak, boolean tornadoBreak, boolean isBreak, BoxType boxType, int level) {
        this.x = x;
        this.y = y;
        this.numberBreak = numberBreak;
        this.tornadoBreak = tornadoBreak;
        this.isBreak = isBreak;
        this.boxType = boxType;
        this.level = level;

        constY = y;
        refY = y+5;

        texture = Texture.boxe;

        initSpriteSheet();
    }

    public void initSpriteSheet() {
        switch (boxType) {
            case BASIC:
                ssPositionX = 0;
                ssPositionY = 0;
                break;
            case JUMP:
                ssPositionX = 1;
                ssPositionY = 0;
                break;
            case ARROW:
                ssPositionX = 2;
                ssPositionY = 0;
                break;
            case AKUAKU:
                ssPositionX = 3;
                ssPositionY = 0;
                break;
            case CRASH:
                ssPositionX = 0;
                ssPositionY = 1;
                break;
            case IRON:
                ssPositionX = 1;
                ssPositionY = 1;
                break;
            case TNT:
                ssPositionX = 2;
                ssPositionY = 1;
                break;
            case NITRO:
                ssPositionX = 3;
                ssPositionY = 1;
                break;

            default:
                break;
        }
    }

    public boolean getCollideX () {
        float px = Player.playerX + Player.xa;
        float py = Player.playerY + Player.ya;

        if ((px >= x + 25) || (px + Player.playerBoxWidth <= x) || (py >= y + 25) || (py + Player.playerBoxHeight <= y+20)){
            return false;
        } else {
            return true;
        }
    }

    public boolean getCollideY () {
        float px = Player.playerX + Player.xa;
        float py = Player.playerY + Player.ya;

        if ((px >= x + 25) || (px + Player.playerBoxWidth <= x) || (py >= y + 25) || (py + Player.playerBoxHeight <= y+4)){
            return false;
        } else {
            return true;
        }
    }

    boolean animate = false;
    boolean switchY = false;
    int refY;

    public void update() {

        if(numberBreak > 0 && getCollideY() && (Player.playerY +40 > y)) {
            if(!animate) animate = true;
        }

        if(animate) {
            if(!switchY) {
                if(y < refY) y++;
                if(y == refY) {
                    switchY = true;
                    refY -=5;
                }
            } else {
                if(y > refY) y--;
                if(y == refY) {
                    switchY = false;
                    animate = false;
                    y = constY;
                    refY = constY + 5;
                    numberBreak--;
                    if(numberBreak == 0) isBreak = true;
                }
            }
        }

    }

    public void render() {
        texture.bind();
            Renderer.renderEntity(x, y, 25, 25, Colors.WHITE, 4.4f, ssPositionX, ssPositionY);
        texture.unbind();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getBreak() {
        return isBreak;
    }

    public boolean getTornadoBreak() {
        return tornadoBreak;
    }

    public int getLevel() { return level; }

    public BoxType getBoxType() { return boxType; }

    public void setBreak(boolean newBreak) {
        isBreak = newBreak;
    }

}
