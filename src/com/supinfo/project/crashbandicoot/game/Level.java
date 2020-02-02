package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.Entity;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.tiles.Tile;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.objects.Boxes;
import com.supinfo.project.crashbandicoot.objects.Fruit;
import com.supinfo.project.crashbandicoot.utiles.ObjectsAnimation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public float gravity = 1.8f;

    public int width, height;

    Tile[][] solidTile;

    List<Tile> tiles = new ArrayList<Tile>();

    List<Entity> entities = new ArrayList<>();

    Texture textureFruit;
    Texture textureBoxe;

    public ArrayList<Fruit> fruits = new ArrayList<Fruit>();
    public ArrayList<Boxes> boxes = new ArrayList<Boxes>();

    private static Player player = new Player(10, 80);

    ObjectsAnimation animFruits;

    UnicodeFont font;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        generate();
        spawner();

        animFruits = new ObjectsAnimation(5, 6, true);
    }

    public void generate() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(x, y, x, y, Tile.Tiles.BG));
            }
        }
    }

    public void spawner() {
        player.init(this);
        addEntity(player);
    }

    public Tile getSolidTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return solidTile[x][y];
    }

    public void init() {
        solidTile = new Tile[width][height];
        textureFruit = Texture.apple;
        textureBoxe = Texture.boxe;

        for (int i = 0; i < width; i++) {
            solidTile[i][7] = new Tile(i, 7, 0, 0, Tile.Tiles.COL);
            solidTile[i][8] = new Tile(i, 8, 0, 0, Tile.Tiles.COL);
        }

        solidTile[10][6] = new Tile(10, 6, 0, 0, Tile.Tiles.COL);
        solidTile[11][6] = new Tile(11, 6, 0, 0, Tile.Tiles.COL);
        solidTile[12][6] = new Tile(12, 6, 0, 0, Tile.Tiles.COL);

        solidTile[19][5] = new Tile(10, 6, 0, 0, Tile.Tiles.COL);
        solidTile[20][5] = new Tile(11, 6, 0, 0, Tile.Tiles.COL);
        solidTile[21][5] = new Tile(12, 6, 0, 0, Tile.Tiles.COL);

        initObjects();
        animFruits.play();
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void update() {
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e.getRemoved()) entities.remove(e);
            e.update();
        }
        animFruits.update();
    }

    public void render() {
        for (Tile tile : tiles) {
            tile.render();
        }
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render();
        }
    }

    public void initObjects() {
        fruits.add(new Fruit(120, 120, false));
        fruits.add(new Fruit(200, 80, false));
        fruits.add(new Fruit(290, 120, false));
        //fruits.add(new Fruit(360, 110, false));
        fruits.add(new Fruit(440, 120, false));

        fruits.add(new Fruit(550, 90, false));
        fruits.add(new Fruit(650, 120, false));
        fruits.add(new Fruit(750, 90, false));
        fruits.add(new Fruit(850, 120, false));
        fruits.add(new Fruit(950, 90, false));

        boxes.add(new Boxes(190, 125, 1, true, false, false, Boxes.Box.BASIC));
        boxes.add(new Boxes(325, 75, 1, true, false, false, Boxes.Box.BASIC));

        fontInit();
    }

    public void fontInit() {
        Font awtFont = new Font("Arial", Font.BOLD, 20);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();

        try {
            font.loadGlyphs();
        } catch (SlickException ex) {
            System.err.println(ex.toString());
        }
    }

    public void levelObjects() {
        font.drawString(Math.abs(Game.xScroll) + 10, 5, "" + player.numberFruits);

        textureFruit.bind();
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false)
                Renderer.renderEntity(fruits.get(i).getX(), fruits.get(i).getY(), 32, 32, Colors.WHITE, 0.5f, 0, 0);
        }
        textureFruit.unbind();

        textureBoxe.bind();
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getBreak() == false)
                Renderer.renderEntity(boxes.get(i).getX(), boxes.get(i).getY(), 32, 32, Colors.WHITE, 1f, 0, 0);
        }
        textureBoxe.unbind();

        for (int i = 0; i < fruits.size(); i++) {
            fruits.get(i).setY(fruits.get(i).getDefaultY() + animFruits.getCurrentCoord());
        }
    }

    public void reloadObject() {
        // Reload fruits
        for (int i = 0; i < fruits.size(); i++) {
            if(fruits.get(i).getEat() == true) fruits.get(i).setEat(false);
        }

        // Reload boxes
        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() == true) boxes.get(i).setBreak(false);
        }
    }

    public static Player getPlayer() {
        return player;
    }

}
