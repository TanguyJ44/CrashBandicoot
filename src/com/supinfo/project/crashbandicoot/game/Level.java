package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.Entity;
import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.tiles.Tile;
import com.supinfo.project.crashbandicoot.graphics.Color;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.objects.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public float gravity = 1.8f;

    public int width, height;

    Tile[][] solidTile;

    List<Tile> tiles = new ArrayList<Tile>();

    List<Entity> entities = new ArrayList<>();

    Texture texture;

    public ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    private static Player player = new Player(10, 80);

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        generate();
        spawner();
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
        initObjects();

        solidTile = new Tile[width][height];
        texture = Texture.apple;

        for (int i = 0; i < width; i++) {
            if(i != 3) {
                solidTile[i][7] = new Tile(i, 7, 0, 0, Tile.Tiles.COL);
                solidTile[i][8] = new Tile(i, 8, 0, 0, Tile.Tiles.COL);
            }
        }
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
        fruits.add(new Fruit(200, 110, false));
        fruits.add(new Fruit(280, 120, false));
        fruits.add(new Fruit(360, 110, false));
        fruits.add(new Fruit(440, 120, false));
    }

    public void levelObjects() {
        texture.bind();
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false)
                Renderer.renderEntity(fruits.get(i).getX(), fruits.get(i).getY(), 32, 32, Color.WHITE, 0.5f, 0, 0);
        }
        texture.unbind();
    }

    public static Player getPlayer() {
        return player;
    }

}
