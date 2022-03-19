package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import Clock.TickClock;
import Constants.Constants;
import IO.Keyboard;
import items.*;

import java.awt.Rectangle;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Level {
    private int[][] grave1;
    private int[][] grave2;
    private int[][] grave3Top;
    private int[][] grave3Bottom;
    private int[][] wallHorizontal1;
    private int[][] wallHorizontal2;
    private int[][] wallHorizontal3;
    private int[][] wallHorizontal4;
    private int[][] wallVertical1;
    private int[][] wallVertical2;
    private int[][] wallVertical3;
    private ArrayList<Zombie> zombieList;
    private PlayerActor Hero;
    private Grid grid;
    private Keyboard keyboard;
    private ItemDetection itemDetection;
    private ArrayList<Obstacle> obstacleList;
    private ArrayList<Item> itemList;
    private final int ITEM_LIMIT = 5;
    private TickClock tickClock;
    private Thread tickClockThread;

    //May need to refactor in the future in order to make it safer for User
    protected Level(Grid grid, Keyboard keyboard, String path) throws IOException {
        this.grid = grid;
        this.keyboard = keyboard;

        this.zombieList = new ArrayList<Zombie>();
        this.obstacleList = new ArrayList<Obstacle>();
        this.itemList = new ArrayList<Item>();
        this.grave1 = new int[24][24];
        this.grave2 = new int[24][24];
        this.grave3Top = new int[24][24];
        this.grave3Bottom = new int[24][24];
        this.wallHorizontal1 = new int[24][24];
        this.wallHorizontal2 = new int[24][24];
        this.wallHorizontal3 = new int[24][24];
        this.wallHorizontal4 = new int[24][24];
        this.wallVertical1 = new int[24][24];
        this.wallVertical2 = new int[24][24];
        this.wallVertical3 = new int[24][24];
        this.itemDetection = new ItemDetection(this, grid);

        BufferedReader myReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
        int y = 0;
        String str = myReader.readLine();
        while (str != null  ) {
            //One line of map
            System.out.println(str);
            char[] chars = str.toCharArray();           //Turn line into char array for easy traversal
            int x = 0;
            while (x < str.length()){
                switch (chars[x]) {
                    case Constants.GRAVE_1:
                        grave1[x][y] = 1;
                        break;
                    case Constants.GRAVE_2:
                        grave2[x][y] = 1;
                        break;
                    case Constants.GRAVE_3_TOP:
                        grave3Top[x][y] = 1;
                        break;
                    case Constants.GRAVE_4_BOTTOM:
                        grave3Bottom[x][y] = 1;
                        break;
                    case Constants.WALL_HORIZONTAL_1:
                        wallHorizontal1[x][y] = 1;
                        break;
                    case Constants.WALL_HORIZONTAL_2:
                        wallHorizontal2[x][y] = 1;
                        break;
                    case Constants.WALL_HORIZONTAL_3:
                        wallHorizontal3[x][y] = 1;
                        break;
                    case Constants.WALL_HORIZONTAL_4:
                        wallHorizontal4[x][y] = 1;
                        break;
                    case Constants.WALL_VERTICAL_1:
                        wallVertical1[x][y] = 1;
                        break;
                    case Constants.WALL_VERTICAL_2:
                        wallVertical2[x][y] = 1;
                        break;
                    case Constants.WALL_VERTICAL_3:
                        wallVertical3[x][y] = 1;
                        break;
                    case Constants.ZOMBIE_FOREGROUND:
                        zombieList.add(new Zombie(grid, keyboard, x * grid.getTileSize(), y * grid.getTileSize(), this));
                        break;
                    case Constants.HEART_FOREGROUND:
                        itemList.add(0, new Treasure(grid, x * grid.getTileSize(), y * grid.getTileSize()));
                        break;
                    case Constants.TRAP_FOREGROUND:
                        itemList.add(0, new Trap(grid, x * grid.getTileSize(), y * grid.getTileSize()));
                        break;
                    case Constants.EXIT_FOREGROUND:
                        itemList.add(0, new ExitCell(grid, x*grid.getTileSize(), y * grid.getTileSize()));
                        break;
                    case Constants.BONUS_FOREGROUND:
                        itemList.add(0, new BonusTreasure(grid, x * grid.getTileSize(), y * grid.getTileSize()));
                        break;
                    case Constants.START_FOREGROUND:
                        int[] position = new int[2];
                        position[0] = x * grid.getTileSize();
                        position[1] = y * grid.getTileSize();
                        Hero = new PlayerActor(grid, keyboard, position, this);
                        break;
                    default:
                        break;
                }

                x++;
            }
            y++;
            str = myReader.readLine();
        }

        // Characters are put in this thread so that they can only move on clock ticks
        this.tickClock = new TickClock(Hero, zombieList);
        this.tickClockThread = new Thread(tickClock);
        this.tickClockThread.start();

        myReader.close();

        generateObstacle(Constants.GRAVE_1);
        generateObstacle(Constants.GRAVE_2);
        generateObstacle(Constants.GRAVE_3_TOP);
        generateObstacle(Constants.GRAVE_4_BOTTOM);
        generateObstacle(Constants.WALL_HORIZONTAL_1);
        generateObstacle(Constants.WALL_HORIZONTAL_2);
        generateObstacle(Constants.WALL_HORIZONTAL_3);
        generateObstacle(Constants.WALL_HORIZONTAL_4);
        generateObstacle(Constants.WALL_VERTICAL_1);
        generateObstacle(Constants.WALL_VERTICAL_2);
        generateObstacle(Constants.WALL_VERTICAL_3);
    }

    public PlayerActor getHero() {
        return Hero;
    }

    /**
     * Returns the map's item limit.
     */
    public int getItemLimit() {
        return ITEM_LIMIT;
    }

    /**
     *
     * @return the items available in the level
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * Generates the specified obstacles on the map based on what has been found in the level file
     * @param assetSpecifier - Which obstacle should be generated
     */
    public void generateObstacle(char assetSpecifier){
        int obstacleArray[][] = null;

        switch (assetSpecifier) {
            case Constants.GRAVE_1:
                obstacleArray = grave1;
                break;
            case Constants.GRAVE_2:
                obstacleArray = grave2;
                break;
            case Constants.GRAVE_3_TOP:
                obstacleArray = grave3Top;
                break;
            case Constants.GRAVE_4_BOTTOM:
                obstacleArray = grave3Bottom;
                break;
            case Constants.WALL_HORIZONTAL_1:
                obstacleArray = wallHorizontal1;
                break;
            case Constants.WALL_HORIZONTAL_2:
                obstacleArray = wallHorizontal2;
                break;
            case Constants.WALL_HORIZONTAL_3:
                obstacleArray = wallHorizontal3;
                break;
            case Constants.WALL_HORIZONTAL_4:
                obstacleArray = wallHorizontal4;
                break;
            case Constants.WALL_VERTICAL_1:
                obstacleArray = wallVertical1;
                break;
            case Constants.WALL_VERTICAL_2:
                obstacleArray = wallVertical2;
                break;
            case Constants.WALL_VERTICAL_3:
                obstacleArray = wallVertical3;
                break;
            default:
                return;

        }

        for(int i = 0; i < obstacleArray.length; i++) {
            for(int j = 0; j< obstacleArray.length; j++) {
                if (obstacleArray[i][j] == 1){
                    Obstacle obstacle = new Obstacle(i * grid.getTileSize(),j * grid.getTileSize(), grid);
                    obstacle.setSprite(assetSpecifier);
                    obstacleList.add(obstacle);
                }
            }
        }
    }

    /**
     *
     */
    public void update(){
        Hero.update();
        itemDetection.onItem(Hero);
        for (int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i) != null) {
                itemList.get(i).update();
            }

        }
        for (int i = 0; i < zombieList.size(); i++) {
            zombieList.get(i).update();
        }
    }

    /**
     *
     * @param g2
     */
    public void draw(Graphics2D g2){

        for (Obstacle obstacle : obstacleList){
            if (obstacle != null) {
                obstacle.draw(g2);}
        }

        for (Zombie zombie: zombieList){
            if (zombie != null) {zombie.draw(g2);}
        }

        for(Item item: itemList) {
            if (item != null) {item.draw(g2);}
        }

        if (Hero != null) {Hero.draw(g2);}
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean obstacleCheck(int x, int y){
        Rectangle character = new Rectangle(x,y,grid.getTileSize(),grid.getTileSize());

        if (y > grid.getScreenHeight()-grid.getTileSize() || y < 0 || x < 0 || x > grid.getScreenWidth() - grid.getTileSize() ){
            return true;
        }

        int wallx;
        int wally;

        if (x % grid.getTileSize() < grid.getTileSize()/2){
            wallx = x - (x% grid.getTileSize());
        }
        else {
            wallx = x - (x% grid.getTileSize()) + grid.getTileSize();
        }
        if (y % grid.getTileSize() < grid.getTileSize()/2){
            wally = y - (y% grid.getTileSize());
        }
        else {
            wally = y - (y% grid.getTileSize()) + grid.getTileSize();
        }
        Rectangle rectangle2 = new Rectangle(wallx,wally,grid.getTileSize(),grid.getTileSize());

        boolean collision = (character.intersects(rectangle2)) &&
                (grave1[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                grave2[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                grave3Top[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                grave3Bottom[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallHorizontal1[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallHorizontal2[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallHorizontal3[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallHorizontal4[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallVertical1[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallVertical2[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 ||
                wallVertical3[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1);

        return  collision;

//        return (character.intersects(rectangle2)) && (grave1[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 || wallHorizontal1[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1);
    }
}
