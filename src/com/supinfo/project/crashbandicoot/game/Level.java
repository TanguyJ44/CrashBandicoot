package com.supinfo.project.crashbandicoot.game;

import com.supinfo.project.crashbandicoot.entities.*;
import com.supinfo.project.crashbandicoot.game.tiles.Tile;
import com.supinfo.project.crashbandicoot.graphics.Colors;
import com.supinfo.project.crashbandicoot.graphics.Header;
import com.supinfo.project.crashbandicoot.graphics.Renderer;
import com.supinfo.project.crashbandicoot.graphics.Texture;
import com.supinfo.project.crashbandicoot.objects.Boxes;
import com.supinfo.project.crashbandicoot.objects.CheckPoint;
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
    public static ArrayList<CheckPoint> checkpoints = new ArrayList<CheckPoint>();

    ArrayList<Crab> crabs = new ArrayList<>();
    ArrayList<Fish> fishs = new ArrayList<>();
    ArrayList<Plant> plants = new ArrayList<>();

    ArrayList<Traps> traps = new ArrayList<>();

    private static Player player = new Player(10, 80);
    public static AkuAku akuaku = new AkuAku(8, 90);

    ObjectsAnimation animFruits;

    public static AudioControl wompasSound;
    public static AudioControl lvl1Sound;
    public static AudioControl lvl2Sound;
    public static AudioControl lvl3Sound;

    int loadLevel = 0;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        generate();
        spawner();

        animFruits = new ObjectsAnimation(5, 6, true);

        lvl1Sound = new AudioControl();
        lvl2Sound = new AudioControl();
        lvl3Sound = new AudioControl();
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
    }

    public void mapInit() {
        solidTile = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 7; j < 15; j++) {
                if(Level.levelNumber == 1 && i != 28 && i != 29 /* && i != 28 && i != 48 && i != 49*/) {
                    solidTile[i][j] = new Tile(i, j, 0, 0, Tile.Tiles.COL);
                } else if(Level.levelNumber == 2 && i != 23 && i != 24 && i != 34 && i != 35 && i != 72 && i != 73){
                    solidTile[i][j] = new Tile(i, j, 0, 0, Tile.Tiles.COL);
                } else if(Level.levelNumber == 3){
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

        for (int i = 0; i < checkpoints.size(); i++) {
            checkpoints.get(i).update();
        }

        animFruits.update();

        if(loadLevel < Level.levelNumber) {
            loadLevel = Level.levelNumber;
            mapInit();
            initObjects();
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

        ScreenLoader.render();

        if(levelFinished == true) Renderer.renderBlackOut(1);
        if(Player.playerLife == 0) Renderer.renderBlackOut(2);
    }

    public static void initObjects() {
        fruits.clear();
        boxes.clear();
        checkpoints.clear();

        if(Level.levelNumber == 1) {
            fruits.add(new Fruit(70, 120, false, 1));
            fruits.add(new Fruit(100, 90, false, 1));
            fruits.add(new Fruit(130, 120, false, 1));
            fruits.add(new Fruit(300, 45, false, 1));
            fruits.add(new Fruit(320, 25, false, 1));
            fruits.add(new Fruit(340, 5, false, 1));
            fruits.add(new Fruit(445, 95, false, 1));
            fruits.add(new Fruit(470, 80, false, 1));
            fruits.add(new Fruit(495, 95, false, 1));
            fruits.add(new Fruit(735, 80, false, 1));
            fruits.add(new Fruit(805, 80, false, 1));
            fruits.add(new Fruit(870, 105, false, 1));
            fruits.add(new Fruit(925, 80, false, 1));
            fruits.add(new Fruit(925, 50, false, 1));
            fruits.add(new Fruit(925, 20, false, 1));

            boxes.add(new Boxes(250, 130,0,true,false, Boxes.BoxType.BASIC,1));
            boxes.add(new Boxes(280, 100,0,false,false, Boxes.BoxType.IRON,1));
            boxes.add(new Boxes(380, 130,0,true,false, Boxes.BoxType.AKU,1));
            boxes.add(new Boxes(540, 130,1,true,false, Boxes.BoxType.BASIC,1));
            boxes.add(new Boxes(660, 130,1,false,false, Boxes.BoxType.TNT,1));
            boxes.add(new Boxes(800, 130,0,true,false, Boxes.BoxType.BASIC,1));
            boxes.add(new Boxes(920, 130,5,true,false, Boxes.BoxType.ARROW,1));

            checkpoints.add(new CheckPoint(425, 121, false, 1));

        } else if(Level.levelNumber == 2) {
            fruits.add(new Fruit(100, 100, false, 2));
            fruits.add(new Fruit(130, 100, false, 2));
            fruits.add(new Fruit(160, 100, false, 2));
            fruits.add(new Fruit(455, 90, false, 2));
            fruits.add(new Fruit(495, 60, false, 2));
            fruits.add(new Fruit(725, 100, false, 2));
            fruits.add(new Fruit(845, 100, false, 2));
            fruits.add(new Fruit(875, 100, false, 2));
            fruits.add(new Fruit(905, 100, false, 2));
            fruits.add(new Fruit(1260, 100, false, 2));
            fruits.add(new Fruit(1290, 100, false, 2));
            fruits.add(new Fruit(1750, 110, false, 2));
            fruits.add(new Fruit(1780, 100, false, 2));
            fruits.add(new Fruit(1810, 110, false, 2));
            fruits.add(new Fruit(1840, 120, false, 2));
            fruits.add(new Fruit(1870, 110, false, 2));
            fruits.add(new Fruit(1900, 100, false, 2));

            boxes.add(new Boxes(450, 130,0,false,false, Boxes.BoxType.IRON,2));
            boxes.add(new Boxes(490, 100,0,false,false, Boxes.BoxType.IRON,2));
            boxes.add(new Boxes(780, 130,0,true,false, Boxes.BoxType.CRASH,2));
            boxes.add(new Boxes(970, 130,1,false,false, Boxes.BoxType.TNT,2));
            boxes.add(new Boxes(1010, 100,10,true,false, Boxes.BoxType.JUMP,2));
            boxes.add(new Boxes(1340, 130,0,true,false, Boxes.BoxType.AKU,2));
            boxes.add(new Boxes(1460, 130,1,false,false, Boxes.BoxType.NITRO,2));
            boxes.add(new Boxes(1510, 130,0,false,false, Boxes.BoxType.IRON,2));
            boxes.add(new Boxes(1540, 100,10,true,false, Boxes.BoxType.JUMP,2));
            //boxes.add(new Boxes(1540, 100,0,false,false, Boxes.BoxType.IRON,2));
            //boxes.add(new Boxes(1570, 70,0,false,false, Boxes.BoxType.IRON,2));
            //boxes.add(new Boxes(1600, 40,10,true,false, Boxes.BoxType.JUMP,2));
            boxes.add(new Boxes(1690, 130,1,false,false, Boxes.BoxType.TNT,2));

            checkpoints.add(new CheckPoint(1280, 125, false, 2));
        } else if(Level.levelNumber == 3) {
            fruits.add(new Fruit(70, 120, false, 3));
            fruits.add(new Fruit(100, 90, false, 3));
            fruits.add(new Fruit(130, 120, false, 3));
            fruits.add(new Fruit(495, 40, false, 3));
            fruits.add(new Fruit(525, 40, false, 3));
            fruits.add(new Fruit(555, 70, false, 3));
            fruits.add(new Fruit(585, 100, false, 3));
            fruits.add(new Fruit(685, 20, false, 3));
            fruits.add(new Fruit(910, 120, false, 3));
            fruits.add(new Fruit(940, 90, false, 3));
            fruits.add(new Fruit(970, 120, false, 3));
            fruits.add(new Fruit(1090, 70, false, 3));
            fruits.add(new Fruit(1090, 100, false, 3));
            fruits.add(new Fruit(1090, 130, false, 3));
            fruits.add(new Fruit(1490, 130, false, 3));
            fruits.add(new Fruit(1540, 100, false, 3));
            fruits.add(new Fruit(1590, 70, false, 3));
            fruits.add(new Fruit(1610, 120, false, 3));
            fruits.add(new Fruit(1630, 70, false, 3));
            fruits.add(new Fruit(1680, 100, false, 3));
            fruits.add(new Fruit(1730, 130, false, 3));

            boxes.add(new Boxes(350, 130,0,true,false, Boxes.BoxType.AKU,3));
            boxes.add(new Boxes(410, 130,0,false,false, Boxes.BoxType.IRON,3));
            boxes.add(new Boxes(450, 100,0,true,false, Boxes.BoxType.BASIC,3));
            //boxes.add(new Boxes(490, 70,0,false,false, Boxes.BoxType.IRON,3));
            boxes.add(new Boxes(580, 130,1,false,false, Boxes.BoxType.TNT,3));
            boxes.add(new Boxes(630, 130,5,true,false, Boxes.BoxType.ARROW,3));
            boxes.add(new Boxes(680, 60,0,true,false, Boxes.BoxType.BASIC,3));
            boxes.add(new Boxes(1020, 130,0,true,false, Boxes.BoxType.AKU,3));
            boxes.add(new Boxes(1160, 130,1,false,false, Boxes.BoxType.NITRO,3));
            boxes.add(new Boxes(1230, 130,0,false,false, Boxes.BoxType.IRON,3));
            boxes.add(new Boxes(1280, 100,0,true,false, Boxes.BoxType.BASIC,3));
            boxes.add(new Boxes(1330, 70,0,false,false, Boxes.BoxType.IRON,3));

            checkpoints.add(new CheckPoint(1320, 115, false, 3));
        }
    }

    public void initEntities() {
        // Level 1
        crabs.add(new Crab(160, 135, 1));
        crabs.add(new Crab(280, 135, 1));
        // Level 2
        crabs.add(new Crab(100, 135, 2));
        crabs.add(new Crab(1070, 135, 2));
        crabs.add(new Crab(1580, 135, 2));
        // Level 3
        crabs.add(new Crab(250, 135, 3));
        crabs.add(new Crab(740, 135, 3));
        crabs.add(new Crab(1800, 135, 3));

        // Level 1
        fishs.add(new Fish(733, 150, 1));
        // Level 3
        //fishs.add(new Fish(205, 150, 3));
        fishs.add(new Fish(525, 150, 3));

        // Level 1
        plants.add(new Plant(600, 115, 1));
        // Level 2
        plants.add(new Plant(250, 115, 2));
        plants.add(new Plant(650, 115, 2));
        plants.add(new Plant(1950, 115, 2));
        // Level 3
        plants.add(new Plant(850, 115, 3));
        plants.add(new Plant(1410, 115, 3));

        // Level 2
        traps.add(new Traps(720, 170, 2));
        traps.add(new Traps(1400, 170, 2));

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

        for (int i = 0; i < checkpoints.size(); i++) {
            checkpoints.get(i).render();
        }

        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 1)
                fruits.get(i).setY(fruits.get(i).getDefaultY() + animFruits.getCurrentCoord());
        }
    }

    public void level2Objects() {
        Header.render();

        textureFruit.bind();
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 2)
                Renderer.renderEntity(fruits.get(i).getX(), fruits.get(i).getY(), 37, 37, Colors.WHITE, 0.5f, 0, 0);
        }
        textureFruit.unbind();

        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() != true)
                boxes.get(i).render();
        }

        for (int i = 0; i < checkpoints.size(); i++) {
            checkpoints.get(i).render();
        }

        for (int i = 0; i < traps.size(); i++) {
            traps.get(i).render();
        }

        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 2)
                fruits.get(i).setY(fruits.get(i).getDefaultY() + animFruits.getCurrentCoord());
        }
    }

    public void level3Objects() {
        Header.render();

        textureFruit.bind();
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 3)
                Renderer.renderEntity(fruits.get(i).getX(), fruits.get(i).getY(), 37, 37, Colors.WHITE, 0.5f, 0, 0);
        }
        textureFruit.unbind();

        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getBreak() != true)
                boxes.get(i).render();
        }

        for (int i = 0; i < checkpoints.size(); i++) {
            checkpoints.get(i).render();
        }

        for (int i = 0; i < traps.size(); i++) {
            traps.get(i).render();
        }

        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getEat() == false && fruits.get(i).getLevel() == 3)
                fruits.get(i).setY(fruits.get(i).getDefaultY() + animFruits.getCurrentCoord());
        }
    }

    public void load1AfterPlayer() {


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

        for (int i = 0; i < crabs.size(); i++) {
            if(crabs.get(i).getEnabled() == false) crabs.get(i).setEnabled(true);
        }

        for (int i = 0; i < plants.size(); i++) {
            if(plants.get(i).getEnabled() == false) plants.get(i).setEnabled(true);
        }
    }

    public static void reloadGameOver() {
        Level.levelNumber = 1;
        initObjects();

        for (int i = 0; i < checkpoints.size(); i++) {
            if(checkpoints.get(i).getChecked() == true) checkpoints.get(i).setChecked(false);
        }
    }

    public static void levelDischarge() {}

    public static boolean checkCollideAllBoxesX() {
        for (int i = 0; i < boxes.size(); i++) {
            if(!boxes.get(i).getBreak()) {
                if(Level.boxes.get(i).getCollideX()) {
                    if(boxes.get(i).getTornadoBreak() && Player.tornadoAttack) {
                        boxes.get(i).setBreak(true);
                        boxes.get(i).onAction();
                        return false;
                    } else {
                        if(Player.tornadoAttack) boxes.get(i).onAction();
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

    static double gain;

    public static void startLevelSound(int lvl) {
        switch (lvl) {
            case 1:
                gain = 10;
                lvl1Sound.init(new File("./res/sounds/lvl1.wav"));
                lvl1Sound.play();
                lvl1Sound.setVolume((float) gain / 100);
            /*case 2:
                gain = 10;
                lvl2Sound.init(new File("./res/sounds/lvl2.wav"));
                lvl2Sound.play();
                lvl2Sound.setVolume((float) gain / 100);
                break;
            case 3:
                gain = 10;
                lvl3Sound.init(new File("./res/sounds/lvl3.wav"));
                lvl3Sound.play();
                lvl3Sound.setVolume((float) gain / 100);
                break;*/
            default:
                break;
        }
    }

    public static Player getPlayer() {
        return player;
    }

}
