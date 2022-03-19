package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import Clock.TickClock;
import Constants.Constants;
import IO.Keyboard;
import items.BonusTreasure;
import items.Item;
import items.ItemDetection;
import items.Treasure;
import items.Trap;

import java.awt.Rectangle;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Level {
    private int[][] graves;
    private int[][] wallHorizontal;
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
        this.graves = new int[24][24];
        this.wallHorizontal = new int[24][24];
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
                        graves[x][y] = 1;
                        break;
                    case Constants.WALL_HORIZONTAL_1:
                        wallHorizontal[x][y] = 1;
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
        generateObstacle(Constants.WALL_HORIZONTAL_1);
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
     * 
     */
    public void generateObstacle(char assetSpecifier){
        int obstacleArray[][] = null;


        switch (assetSpecifier) {
            case Constants.GRAVE_1:
                obstacleArray = graves;
                break;
            case Constants.WALL_HORIZONTAL_1:
                obstacleArray = wallHorizontal;
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

        return (character.intersects(rectangle2)) && (graves[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1 || wallHorizontal[wallx/grid.getTileSize()][wally/grid.getTileSize()] == 1);
    }
}
