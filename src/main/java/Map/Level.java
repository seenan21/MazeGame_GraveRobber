package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import Clock.TickClock;
import IO.Keyboard;
import items.BonusTreasure;
import items.Item;
import items.ItemDetection;
import items.Treasure;

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
                if (chars[x] == '#'){
                    graves[x][y] = 1;
                }
                else if (chars[x] == '='){
                    wallHorizontal[x][y] = 1;
                }
                else if(chars[x] == 'Z'){
                    zombieList.add(new Zombie(grid, keyboard, x*grid.getTileSize(), y*grid.getTileSize(), this));
                }
                else if(chars[x] == 'T'){
                    itemList.add(0, new Treasure(grid, x*grid.getTileSize(), y*grid.getTileSize()));
                }
                else if(chars[x] == 'B'){
                    itemList.add(0, new BonusTreasure(grid, x*grid.getTileSize(), y*grid.getTileSize()));
                }
                else if (chars[x] == 'S'){
                    int[] position = new int[2];
                    position[0] = x* grid.getTileSize();
                    position[1] = y* grid.getTileSize();
                    Hero = new PlayerActor(grid,keyboard, position, this); //Should position be passed onto the hero here from the map? If so new paramter                }
                }
                x++;
            }
            y++;
            str = myReader.readLine();
        }

        this.tickClock = new TickClock(Hero, zombieList);
        this.tickClockThread = new Thread(tickClock);
        this.tickClockThread.start();

        myReader.close();

        gravesGenerate();
        wallHorizontalGenerate();
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
    public void gravesGenerate(){
        for(int i = 0; i < graves.length; i++) {
            for(int j = 0; j< graves.length; j++) {
                if (graves[i][j] == 1){
                    Obstacle obstacle = new Obstacle(i * grid.getTileSize(),j * grid.getTileSize(), grid);
                    obstacle.setSprite(ObstacleSpecifier.GRAVE_1);
                    obstacleList.add(obstacle);
                }
            }
        }
    }

    /**
     *
     */
    public void wallHorizontalGenerate(){
        for(int i = 0; i < wallHorizontal.length; i++) {
            for(int j = 0; j< wallHorizontal.length; j++) {
                if (wallHorizontal[i][j] == 1){
                    Obstacle obstacle = new Obstacle(i * grid.getTileSize(),j * grid.getTileSize(), grid);
                    obstacle.setSprite(ObstacleSpecifier.WALL_HORIZONTAL_1);
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
