package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.*;
import com.supinfo.project.crashbandicoot.game.tiles.Tile;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Header;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.objects.Boxes;
import com.supinfo.project.crashbandicoot.objects.Fruit;
import com.supinfo.project.crashbandicoot.utiles.AudioControl;
import com.supinfo.project.crashbandicoot.utiles.ObjectsAnimation;
import com.supinfo.project.crashbandicoot.utiles.ScreenLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public float gravity = 1.8f;

    public int width, height;

    Tile[][] solidTile;

    List<Tile> tiles = new ArrayList<Tile>();

    List<Entity> entities = new ArrayList<>();

    public static boolean levelFinished = false;
    public static int levelNumber = 1;

    Texture textureFruit;

    public static ArrayList<Fruit> fruits = new ArrayList<>();
    public static ArrayList<Boxes> boxes = new ArrayList<Boxes>();

    ArrayList<Crab> crabs = new ArrayList<>();
    ArrayList<Fish> fishs = new ArrayList<>();
    ArrayList<Plant> plants = new ArrayList<>();

    ArrayList<Traps> traps = new ArrayList<>();

    private static Player player = new Player(10, 80);
    public static AkuAku akuaku = new AkuAku(8, 90);

    ObjectsAnimation animFruits;

    public static AudioControl wompasSound;

    int loadLevel = 0;

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
        akuaku.init(this);

        addEntity(player);
        addEntity(akuaku);
    }

    public Tile getSolidTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return solidTile[x][y];
    }

    public void init() {
        solidTile = new Tile[width][height];
        textureFruit = Texture.apple;

        initObjects();
        initEntities();
        animFruits.play();

        Header.init();

        wompasSound = new AudioControl();

        ScreenLoader.init();

        boxes.add(new Boxes(250, 110,0,true,false, Boxes.BoxType.BASIC,1));
        boxes.add(new Boxes(280, 80,0,false,false, Boxes.BoxType.IRON,1));
        boxes.add(new Boxes(380, 130,1,true,false, Boxes.BoxType.AKUAKU,1));
    }

    public void mapInit() {
        for (int i = 0; i < width; i++) {
            for (int j = 7; j < 15; j++) {
                if(Level.levelNumber == 1/* && i != 28 && i != 48 && i != 49*/) {
                    solidTile[i][j] = new Tile(i, j, 0, 0, Tile.Tiles.COL);
                } else if(Level.levelNumber == 2 && i != 5){
                    solidTile[i][j] = new Tile(i, j, 0, 0, Tile.Tiles.COL);
                }
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

        for (int i = 0; i < traps.size(); i++) {
            traps.get(i).update();
        }

        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() != true)
                boxes.get(i).update();
        }

        animFruits.update();

        if(loadLevel < Level.levelNumber) {
            loadLevel = Level.levelNumber;
            mapInit();
            initObjects();
        }

        verifEntitiesIsDisabled();
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
        fruits.clear();

        if(Level.levelNumber == 1) {
            fruits.add(new Fruit(70, 120, false, 1));
            fruits.add(new Fruit(100, 90, false, 1));
            fruits.add(new Fruit(130, 120, false, 1));

            fruits.add(new Fruit(300, 45, false, 1));
            fruits.add(new Fruit(320, 25, false, 1));
            fruits.add(new Fruit(340, 5, false, 1));
        } else if(Level.levelNumber == 2) {

        }
    }

    public void initEntities() {
        crabs.add(new Crab(160, 135, 1));
        crabs.add(new Crab(280, 135, 1));


        fishs.add(new Fish(792, 150, 1));
        //plants.add(new Plant(350, 115, 1));

        traps.add(new Traps(643, 170, 1));

        for (int i = 0; i < crabs.size(); i++) {
            crabs.get(i).init(this);
            addEntity(crabs.get(i));
        }

        for (int i = 0; i < fishs.size(); i++) {
            fishs.get(i).init(this);
            addEntity(fishs.get(i));
        }

        for (int i = 0; i < plants.size(); i++) {
            plants.get(i).init(this);
            addEntity(plants.get(i));
        }
    }

    public void verifEntitiesIsDisabled() {
        for (int i = 0; i < crabs.size(); i++) {
            if(crabs.get(i).getEnabled() == false)
                removeEntity(crabs.get(i));
        }

        for (int i = 0; i < fishs.size(); i++) {
            if(fishs.get(i).getEnabled() == false)
                removeEntity(fishs.get(i));
        }

        for (int i = 0; i < plants.size(); i++) {
            if(plants.get(i).getEnabled() == false)
                removeEntity(plants.get(i));
        }
    }

    public void level1Objects() {
        Header.render();

        textureFruit.bind();
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 1)
                Renderer.renderEntity(fruits.get(i).getX(), fruits.get(i).getY(), 37, 37, Colors.WHITE, 0.5f, 0, 0);
        }
        textureFruit.unbind();

        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() != true)
                boxes.get(i).render();
        }

        for (int i = 0; i < traps.size(); i++) {
            traps.get(i).render();
        }

        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 1)
                fruits.get(i).setY(fruits.get(i).getDefaultY() + animFruits.getCurrentCoord());
        }
    }

    public void level2Objects() {
        Header.render();
    }

    public void level3Objects() {
        Header.render();
    }

    public void load1AfterPlayer() {

        ScreenLoader.render();

        if(levelFinished == true) {
            Renderer.renderBlackOut(1);
        }
    }

    public void load2AfterPlayer() {

    }

    public void load3AfterPlayer() {

    }

    public void reloadObject() {
        // Reload fruits
        for (int i = 0; i < fruits.size(); i++) {
            if(fruits.get(i).getEat() == true) fruits.get(i).setEat(false);
        }

        // Reload boxes
        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() == true)
                boxes.get(i).setBreak(false);
        }
    }

    public static boolean checkCollideAllBoxesX() {
        for (int i = 0; i < boxes.size(); i++) {
            if(!boxes.get(i).getBreak()) {
                if(Level.boxes.get(i).getCollideX()) {
                    if(boxes.get(i).getTornadoBreak() && Player.tornadoAttack) {
                        boxes.get(i).setBreak(true);
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public static boolean checkCollideAllBoxesY() {
        for (int i = 0; i < boxes.size(); i++) {
            if(!boxes.get(i).getBreak()) {
                if(Level.boxes.get(i).getCollideY()) return true;
            }
        }

        return false;
    }

    public static Player getPlayer() {
        return player;
    }

}
