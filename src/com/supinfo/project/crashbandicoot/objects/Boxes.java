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

    public boolean getCollide () {
        if ((Player.playerX >= x + 35)
                || (Player.playerX + Player.playerBoxWidth+10 <= x)
                || (Player.playerY >= y + 35)
                || (Player.playerY + Player.playerBoxHeight+10 <= y)){
            return false;
        } else {
            return true;
        }
    }

    public void update() {

        /*if ((Player.playerX >= x + 35)
                || (Player.playerX + Player.playerBoxWidth+10 <= x)
                || (Player.playerY >= y + 35)
                || (Player.playerY + Player.playerBoxHeight+10 <= y)){

            System.out.println("!!");

        }*/

        /*if ((Player.playerX >= x + 35)
                || (Player.playerX + Player.playerBoxWidth+10 <= x)
                || (Player.playerY >= y + 35)
                || (Player.playerY + Player.playerBoxHeight+10 <= y)){
            // Player is not in a area

            //if(Player.moveR == false) Player.moveR = true;
            //if(Player.moveL == false) Player.moveL = true;
            //if(Player.gravityEnabled == false) Player.gravityEnabled = true;
        } else {
            if(Player.tornadoAttack == false) {
                /*Player.moveR = true;

                if(Player.playerX < x && (Player.playerY+Player.playerBoxHeight) > y+5) {
                    Player.moveR = false;
                }

                if(Player.playerX > x+2) {
                    Player.moveL = false;
                    //System.out.println("right");
                }
                if((Player.playerY + Player.playerBoxHeight) < y) {
                    Player.gravityEnabled = false;
                    if(Player.moveR == false) Player.moveR = true;
                }

            } else {
                isBreak = true;
            }

        }*/

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
