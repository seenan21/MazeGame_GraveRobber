package Map;
import Characters.Character;
import Characters.CharacterType;
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

/**
 * Contains all level data for a particular map.
 */
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
     * Updates the position of Characters and Items on the map level.
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
     * Draw Obstacles, Items, and Characters
     * @param g2 - Graphics2D
     */
    public void draw(Graphics2D g2){

        for (Obstacle obstacle : obstacleList){
            if (obstacle != null) {
                obstacle.draw(g2);}
        }

        for(Item item: itemList) {
            if (item != null) {item.draw(g2);}
        }

        for (Zombie zombie: zombieList){
            if (zombie != null) {zombie.draw(g2);}
        }

        if (Hero != null) {Hero.draw(g2);}
    }

    /**
     * Creates a bounding box for an obstacle array to see if there will be any collisions.
     * @param obstacleArray - Array of obstacles on the map
     * @param characterBody - The bounding box of the character
     * @return if collision was detected
     */
    private boolean collisionCheck(int[][] obstacleArray, Rectangle characterBody) {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                if (obstacleArray[i][j] == 1) {
                    Rectangle temp = new Rectangle(i * grid.getTileSize(),j * grid.getTileSize(), grid.getTileSize(), grid.getTileSize());
                    if (characterBody.intersects(temp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates a bounding box for a character to see if there will be any collisions.
     * @param position - Position of the character
     * @param characterBody - The bounding box of the character
     * @return if collision was detected
     */
    private boolean collisionCheck(int[] position, Rectangle characterBody) {

        Rectangle temp = new Rectangle(position[Constants.X] ,position[Constants.Y] , grid.getTileSize(), grid.getTileSize());
        if (characterBody.intersects(temp)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the character's next movement will collide with an obstacle.
     * @param characterX - Character's X position
     * @param characterY - Character's Y position
     * @return
     */
    public boolean collisionCheck(Character character, int characterX, int characterY){
        Rectangle characterBody = new Rectangle(characterX,characterY,grid.getTileSize(),grid.getTileSize());

        // Prevents character from leaving the screen
        if (characterY > grid.getScreenHeight()-grid.getTileSize() || characterY < 0 || characterX < 0 || characterX > grid.getScreenWidth() - grid.getTileSize() ){
            return true;
        }

        if (collisionCheck(grave1, characterBody)) {
            return true;
        } else if (collisionCheck(grave2, characterBody)) {
            return true;
        } else if (collisionCheck(grave3Bottom, characterBody)) {
            return true;
        } else if (collisionCheck(grave3Top, characterBody)) {
            return true;
        } else if (collisionCheck(wallHorizontal1, characterBody)) {
            return true;
        } else if (collisionCheck(wallHorizontal2, characterBody)) {
            return true;
        } else if (collisionCheck(wallHorizontal3, characterBody)) {
            return true;
        } else if (collisionCheck(wallHorizontal4, characterBody)) {
            return true;
        } else if (collisionCheck(wallVertical1, characterBody)) {
            return true;
        } else if (collisionCheck(wallVertical2, characterBody)) {
            return true;
        }  else if (collisionCheck(wallVertical3, characterBody)) {
            return true;
        }  else if (character.getCharacterType() == CharacterType.ENEMY) {
            for (Zombie zombie: zombieList){
                if (zombie != null && zombie != character) {
                    if (collisionCheck(zombie.getPosition(), characterBody)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
