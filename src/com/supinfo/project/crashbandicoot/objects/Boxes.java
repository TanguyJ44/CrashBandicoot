package com.supinfo.project.crashbandicoot.objects;

import com.supinfo.project.crashbandicoot.entities.AkuAku;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.AudioControl;

import java.io.File;

public class Boxes {

    int x, y;
    int numberBreak;
    boolean tornadoBreak;
    boolean isBreak;
    int level;

    int ssPositionX;
    int ssPositionY;

    int constY;
    int constNumberBreak;

    Texture texture;

    AudioControl audioControl;

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

        audioControl = new AudioControl();

        constY = y;
        refY = y+5;
        constNumberBreak = numberBreak;

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
    boolean tnt = false;
    boolean explosion = false;
    int refY;
    int time = 0;
    int count = 3;

    public void update() {

        if(numberBreak > 0 && getCollideY() && (Player.playerY +40 > y)) {
            if(!animate && !tnt) animate = true;
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
                    if(numberBreak == 0 && boxType != BoxType.TNT && boxType != BoxType.NITRO) {
                        isBreak = true;
                        reset();
                    } else if(boxType == BoxType.JUMP) {
                        Player.numberFruits+=10;
                        audioControl.init(new File("./res/sounds/wompas.wav"));
                        audioControl.play();
                    } else if(boxType == BoxType.ARROW) {
                        Player.ya -=25;
                        audioControl.init(new File("./res/sounds/jump.wav"));
                        audioControl.play();
                    } else if(boxType == BoxType.CRASH) {
                        if(Player.playerLife < 3) {
                            Player.playerLife++;
                            audioControl.init(new File("./res/sounds/uplife.wav"));
                            audioControl.play();
                        }
                    } else if(boxType == BoxType.AKUAKU) {
                        if(!Level.akuaku.getInvokAkuaku()) {
                            Level.akuaku.setAkuakuLife(0);
                            Level.akuaku.setInvokAkuaku(true);
                        } else {
                            if(Level.akuaku.getAkuakuLife() < 2) Level.akuaku.setAkuakuLife(Level.akuaku.getAkuakuLife() +1);
                        }
                    } else if(boxType == BoxType.TNT) {
                        tnt = true;
                        audioControl.init(new File("./res/sounds/countdown.wav"));
                        audioControl.play();
                    } else if(boxType == BoxType.NITRO) {
                        reset();
                        count = 4;

                        texture = Texture.explosion1;

                        ssPositionX = 0;
                        ssPositionY = 0;

                        audioControl.init(new File("./res/sounds/explosion.wav"));
                        audioControl.play();

                        explosion = true;
                    }
                }
            }
        }
        if (tnt) {
            time++;

            if(count == 3) {
                ssPositionX = 0;
                ssPositionY = 2;
                count --;
            }

            if (time > 60) {

                if(count == 2) {
                    ssPositionX = 1;
                    ssPositionY = 2;
                }else if(count == 1) {
                    ssPositionX = 2;
                    ssPositionY = 2;
                } else if (count == 0) {
                    tnt = false;
                    count = 4;

                    ssPositionX = 0;
                    ssPositionY = 0;

                    audioControl.init(new File("./res/sounds/explosion.wav"));
                    audioControl.play();

                    explosion = true;
                }

                time = 0;
                count --;
            }
        }

        if (explosion) {
            time++;

            if(count == 3) {
                texture = Texture.explosion1;
                count --;
            }

            if (time > 20) {

                if(count == 2) {
                    texture = Texture.explosion2;
                }else if(count == 1) {
                    texture = Texture.explosion3;
                } else if (count == 0) {
                    isBreak = true;
                    reset();
                }

                time = 0;
                count --;
            }
        }

    }

    public void reset() {
        numberBreak = constNumberBreak;
        animate = false;
        switchY = false;
        tnt = false;
        explosion = false;
        time = 0;
        count = 3;

        texture = Texture.boxe;

        initSpriteSheet();
    }

    public void render() {
        if(!explosion) {
            texture.bind();
                Renderer.renderEntity(x, y, 25, 25, Colors.WHITE, 4.4f, ssPositionX, ssPositionY);
            texture.unbind();
        } else {
            texture.bind();
                Renderer.renderEntity(x-50, y-75, 100, 100, Colors.WHITE, 1f, ssPositionX, ssPositionY);
            texture.unbind();
        }
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
