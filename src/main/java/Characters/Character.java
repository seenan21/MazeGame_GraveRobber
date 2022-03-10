package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;
import items.Item;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains methods that are applicable to all npc and human players.
 */
public abstract class Character {

    Grid _grid;
    Keyboard _keyboard;

    private int health;
    private ArrayList<Item> bag = new ArrayList<>();
    private int[] position = new int[2];
    protected int _speed;
    private int[] startState = new int[2];
    private Direction directionFacing;
    protected BufferedImage sprite;
    Level level;



    /**
     * Constructor for the character class.
     *
//     * @param health - Character's total health
//     * @param directionFacing - Direction the character is facing
//     * @param map - Map the character is being added to
     */
    public Character(Grid grid, Keyboard keyboard) {
        this._keyboard = keyboard;
        this._grid = grid;
    }

    /**
     * Changes the health of the player.
     *
     * @param health - Character's total health
     */
    public void setHealth(int health) {
        if (health >= 0 && health <= 100) {
            this.health = health;
        }
        else {
            System.out.println("ERROR: Health must be between 0 and 100");
            System.exit(-1);
        }
    }

    public void setSpeed(int _speed) {
        this._speed = _speed;
    }



    /**
     * Returns player's current health.
     *
     */
    public int getHealth() {
        return health;
    }

    /**
     * Adds an item from the character's bag.
     *
     * @param item - item to be added to character's bag
     */
    public void addToBag(Item item) {
        this.bag.add(item);
    }

    /**
     * Removes an item from the character's bag.
     *
     * @param item - item to be removed to character's bag
     */
    public void removeFromBag(Item item) {
        this.bag.remove(item);
    }

    /**
     * Changes the position of the player.
     *
     * @param x - X coordinate of character's starting position
     * @param y - Y coordinate of character's starting position
     */
    public void setPosition(int x, int y) {
        this.position[Constants.X] = x;
        this.position[Constants.Y] = y;
    }

    /**
     * Returns player's current position.
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Changes the initial position of the player.
     *
     * @param x - X coordinate of character's position
     * @param y - X coordinate of character's position
     */
    public void setStartState(int x, int y) {
        this.startState[Constants.X] = x;
        this.startState[Constants.Y] = y;
    }

    /**
     * Returns player's original position.
     */
    public int[] getStartState() {
        return startState;
    }

    /**
     * Changes the direction the player is facing on the map.
     *
     * @param directionFacing - The direcetion the character is currently facing
     */
    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }

    /**
     * Returns player's direction they are facing on the map.
     */
    public Direction getDirectionFacing() {
        return directionFacing;
    }

    /**
     * Attempts to move the character's position on the map.
     * Always changes the player's direction facing.
     *
     * @param direction - Player has achieved the final boss award
     */
    public void moveCharacter(Direction direction) {

        if (direction == Direction.NORTH) {
            moveNorth();
        }else if (direction == Direction.SOUTH) {
            moveSouth();
        } else if (direction == Direction.EAST) {
            moveEast();
        } else if (direction == Direction.WEST) {
            moveWest();
        }

        this.setDirectionFacing(direction);
    }

    /**
     * Moves character's position one tile north
     */
    private void moveNorth() {
//        if (this.getPosition()[Constants.Y] < _grid.getHeight()) {
//
//        }

        this.setPosition(getPosition()[Constants.X],getPosition()[Constants.Y] - _speed);
    }

    /**
     * Moves character's position one tile south
     */
    private void moveSouth() {
//        if (this.getPosition()[1] > 0) {
//
//        }
        this.setPosition(getPosition()[Constants.X],getPosition()[Constants.Y] + _speed);
    }

    /**
     * Moves character's position one tile east
     */
    private void moveEast() {
//        if (this.getPosition()[0] < _grid.getWidth()) {
//
//        }
        this.setPosition(getPosition()[Constants.X] + _speed, getPosition()[Constants.Y]);
    }

    /**
     * Moves character's position one tile west
     */
    private void moveWest() {
//        if (this.getPosition()[Constants.X] > 0) {
//
//        }
        this.setPosition(getPosition()[Constants.X] - _speed, getPosition()[Constants.Y]);
    }

    /**To Check if character is colliding with another character or wall**/
    public boolean isCollide(int[] position){
        return this.position[0] == position[0] && this.position[1] == position[1];
    }

    /** For the sprites of the characters **/
    public void draw(Graphics2D g2) {}

    public void render(Graphics g)
    {}


}

