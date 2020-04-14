package com.supinfo.project.crashbandicoot.entities;

import com.supinfo.project.crashbandicoot.game.Level;
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

    public static boolean moveRight = false;
    public static boolean moveLeft = false;
    public static boolean moveJump = false;
    public static boolean gamepadB = false;
    public static boolean gamepadTornado = false;

    public static boolean moveR = true;
    public static boolean moveL = true;
    public static boolean moveT = true;
    public static boolean gravityEnabled = true;

    @Override
    public void update() {
        if(gravityEnabled == true) ya += level.gravity * mass;

        anim.update();
        anim.pause();

        playerX = x;
        playerY = y;

        if (keysEnable == true && moveR == true && (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) /*&& x < 990*/) {
            if(Level.levelNumber == 1 & x < 990 || Level.levelNumber == 2 & x < 1980) {
                xa += speed;
                if(dir != 0) dir = 0;
                anim.play();
            }
        }
        if(keysEnable == true && moveR == true && x < 990 && moveRight == true) {
            xa += speed;
            if(dir != 0) dir = 0;
            anim.play();
        }


        if (keysEnable == true && moveL == true && Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) && x > 0) {
            xa -= speed;
            if(dir != 1) dir = 1;
            anim.play();
        }
        if(keysEnable == true && moveL == true && x > 0 && moveLeft == true) {
            xa -= speed;
            if(dir != 1) dir = 1;
            anim.play();
        }


        if (keysEnable == true && moveT == true && Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            if(dir != 2) {
                anim.pause();
                anim.setCurrentFrame(dir);
                old_dir = dir;
                dir = 2;
            }
            if(isGrounded()) {
                ya -= 20;
            }
            gravityEnabled = true;
        }
        if(keysEnable == true && moveT == true && moveJump == true) {
            moveJump = false;
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


        /*if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ya = speed;
        }*/

        if (keysEnable == true && Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
            if(tornadoAttack == false) {
                tornadoAttack = true;
            }
        }
        if(keysEnable == true && gamepadTornado == true) {
            if(tornadoAttack == false) {
                tornadoAttack = true;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_F1) || gamepadB == true) {
            gamepadB = false;
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

        if (Keyboard.isKeyDown(Keyboard.KEY_F5)) {
            Level.levelFinished = false;
            Level.levelNumber = 1;

            keysEnable = false;
            playerIsDead = false;
            killPlayer = false;

            x = 10;
            y = 80;
            dir = 0;

            Renderer.renderBlackOutReload();

            playerLife = 3;
            numberFruits = 0;
            level.reloadObject();
        }

        if(x > 970) {
            if(Level.levelNumber == 1) {
                Level.levelFinished = true;
                keysEnable = false;

                x = 10;
                y = 80;
                dir = 0;
            }
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
            if ((Player.playerX+10 >= level.fruits.get(i).getX() + 20)
                    || (Player.playerX+10 + Player.playerBoxWidth-20 <= level.fruits.get(i).getX())
                    || (Player.playerY+10 >= level.fruits.get(i).getY() + 20)
                    || (Player.playerY+10 + Player.playerBoxHeight-20 <= level.fruits.get(i).getY())){
                // Player is not in a area
            } else {
                if(level.fruits.get(i).getEat() == false) {
                    level.fruits.get(i).setEat(true);
                    numberFruits++;
                    //Level.wompasSound.play();
                }
            }

        }

        xa *= drag;
        ya *= drag;

        playerDeath();
        onTornadoAttack();
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

            if(playerLife != 0) {
                playerIsDead = false;
                keysEnable = true;

                killPlayer = false;
            }
        }
    }

    int time = 0;
    int tornadoTime = 0;
    public void onTornadoAttack () {
        if(tornadoAttack == true) {

            if (true) {
                time++;
                if (time > 10) {
                    tornadoTime++;
                    if(tornadoTime == 7) {
                        tornadoAttack = false;
                        tornadoTime = 0;
                    }

                    time = 0;
                }
            }

        }
    }

    @Override
    public void render() {
        if(Level.levelNumber == 1) level.level1Objects();
        else if(Level.levelNumber == 2) level.level2Objects();
        else if(Level.levelNumber == 3) level.level3Objects();

        if(tornadoAttack == false) {
            texture.bind();
                Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, dir, anim.getCurrentFrame());
            texture.unbind();
        } else {
            texture.bind();
                Renderer.renderEntity(x, y, 32, 40, Colors.WHITE, 4.5f, 3, 0);
            texture.unbind();
        }

        if(Level.levelNumber == 1) level.load1AfterPlayer();
        else if(Level.levelNumber == 2) level.load2AfterPlayer();
        else if(Level.levelNumber == 3) level.load3AfterPlayer();

        if(Level.levelFinished == true) Renderer.renderBlackOut(1);
        if(playerLife == 0) Renderer.renderBlackOut(2);
    }
}
