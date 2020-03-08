package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
import com.supinfo.project.crashbandicoot.game.Traps;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.utiles.Animation;
import org.lwjgl.input.Keyboard;

public class Player extends Entity{

    public static int playerBoxWidth = 32;
    public static int playerBoxHeight = 40;

    Animation anim;
    public static int dir = 0;

    boolean isJump = false;
    public static boolean tornadoAttack = false;

    public static float playerX, playerY;

    public static int numberFruits = 0;
    public static int playerLife = 3;

    public static boolean killPlayer = false;
    public static boolean playerIsDead = false;

    public static boolean keysEnable = false;

    public Player(int x, int y) {
        super(x, y);
        texture = Texture.player;

        anim = new Animation(2, 10, true);

        mass = 0.5f;
        drag = 0.70f;
    }

    @Override
    public void init(Level level) {
        this.level = level;
    }

    float xa, ya;
    float speed = 0.8f;

    int old_dir;

    @Override
    public void update() {
        ya += level.gravity * mass;

        anim.update();
        anim.pause();

        playerX = x;
        playerY = y;

        if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && x < 990) {
            xa += speed;
            if(dir != 0) dir = 0;
            anim.play();
        }
        if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) && x > 0) {
            xa -= speed;
            if(dir != 1) dir = 1;
            anim.play();
        }
        if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            if(dir != 2) {
                anim.pause();
                anim.setCurrentFrame(dir);
                old_dir = dir;
                dir = 2;
            }
            if(isGrounded()) {
                ya -= 20;
            }
        }
        if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ya = speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F1)) {
            playerLife = 3;
            AkuAku.akuakuLife = 0;
            AkuAku.invokAkuaku = false;
            x = 10;
            y = 80;
            dir = 0;
            numberFruits = 0;
            level.reloadObject();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
            numberFruits++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
            if(playerLife < 3) playerLife++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F4)) {
            if(AkuAku.invokAkuaku == false) AkuAku.invokAkuaku = true;
            if(AkuAku.akuakuLife < 2) AkuAku.akuakuLife++;
        }

        // Jump
        if (ya == 0.9f && dir == 2) {
            anim.play();
            dir = old_dir;
        }

        // Drop Limit
        if(y > 220) {
            killPlayer = true;
        }

        int xStep = (int) Math.abs(xa * 100);
        for (int i = 0; i < xStep; i++) {
            if (!isSolidTile(xa / xStep, 0)) {
                x += xa / xStep;
            } else {
                xa = 0;
            }
        }
        int yStep = (int) Math.abs(ya * 100);
        for (int i = 0; i < yStep; i++) {
            if (!isSolidTile(0, ya / yStep)) {
                y += ya / yStep;
            } else {
                ya = 0;
            }
        }

        // Détection des fruits
        for (int i = 0; i < level.fruits.size(); i++) {
            // Fruit collide detection
            if ((Player.playerX+5 >= level.fruits.get(i).getX() + 20)
                    || (Player.playerX+5 + Player.playerBoxWidth-5 <= level.fruits.get(i).getX())
                    || (Player.playerY+5 >= level.fruits.get(i).getY() + 20)
                    || (Player.playerY+5 + Player.playerBoxHeight-5 <= level.fruits.get(i).getY())){
                // Player is not in a area
            } else {
                if(level.fruits.get(i).getEat() == false) {
                    level.fruits.get(i).setEat(true);
                    numberFruits++;
                    Level.wompasSound.play();
                }
            }

        }

        xa *= drag;
        ya *= drag;

        playerDeath();

    }

    public void playerDeath () {
        if(killPlayer == true) {
            keysEnable = false;
            playerIsDead = true;

            if(playerLife > 0) playerLife--;
            x = 10;
            y = 80;
            dir = 0;
            numberFruits = 0;
            level.reloadObject();

            playerIsDead = false;
            keysEnable = true;

            killPlayer = false;
        }
    }

    @Override
    public void render() {
        level.levelObjects();

        texture.bind();
            Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, dir, anim.getCurrentFrame());
        texture.unbind();

        level.loadAfterPlayer();
    }
}
