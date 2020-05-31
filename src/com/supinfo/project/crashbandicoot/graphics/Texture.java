package com.supinfo.project.crashbandicoot.graphics;

import com.supinfo.project.crashbandicoot.game.tiles.Tile;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class Texture {

    public static Texture load = loadTexture("./res/images/load.png");
    public static Texture tilesLvl1 = loadTexture("./res/images/lvl1.png");
    public static Texture tilesLvl2 = loadTexture("./res/images/lvl2.png");
    public static Texture tilesLvl3 = loadTexture("./res/images/lvl3.png");

    public static Texture player = loadTexture("./res/images/sprite_perso.png");
    public static Texture tornado = loadTexture("./res/images/sprite_tornado.png");
    public static Texture head_player = loadTexture("./res/images/head_cb.png");
    public static Texture akuaku = loadTexture("./res/images/sprite_akuaku.png");

    public static Texture apple = loadTexture("./res/images/fruit.png");
    public static Texture boxe = loadTexture("./res/images/case.png");
    public static Texture pique = loadTexture("./res/images/pique.png");
    public static Texture postCP = loadTexture("./res/images/post_cp.png");
    public static Texture flagCP = loadTexture("./res/images/flag_cp.png");

    public static Texture fish = loadTexture("./res/images/sprite_fish.png");
    public static Texture fishCover = loadTexture("./res/images/fish_cover.png");
    public static Texture fishCoverLvl3 = loadTexture("./res/images/fish_cover_lvl3.png");
    public static Texture crab = loadTexture("./res/images/crab.png");
    public static Texture plant = loadTexture("./res/images/sprite_plant.png");

    public static Texture game_over = loadTexture("./res/images/game_over.png");
    public static Texture turtle = loadTexture("./res/images/turtle.png");
    public static Texture cloud = loadTexture("./res/images/cloud.png");

    public static Texture explosion1 = loadTexture("./res/images/explosion1.png");
    public static Texture explosion2 = loadTexture("./res/images/explosion2.png");
    public static Texture explosion3 = loadTexture("./res/images/explosion3.png");

    int width, height;
    int id;

    public Texture(int width, int height, int id) {
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public static Texture loadTexture(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            //Text.class.getResource(path)
        } catch (IOException e) {
            e.printStackTrace();
        }

        int w = image.getWidth();
        int h = image.getHeight();

        int[] pixels = new int[w * h];
        image.getRGB(0, 0, w, h, pixels, 0, w);

        ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);

        for (int y = 0; y < w; y++) {
            for (int x = 0; x < h; x++) {
                int i = pixels[x + y * w];
                buffer.put((byte) ((i >> 16) & 0xFF));
                buffer.put((byte) ((i >> 8) & 0xFF));
                buffer.put((byte) ((i) & 0xFF));
                buffer.put((byte) ((i >> 24) & 0xFF));
            }
        }

        buffer.flip();

        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return new Texture(w, h, id);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}
