package Characters;

import items.Item;
import java.util.ArrayList;

/**
 * Contains methods that are applicable to all npc and human players.
 */
public abstract class Character {

    private int health = 0;
    private ArrayList<Item> bag = new ArrayList<>();
    private int[] position = new int[2];
    private int[] startState = new int[2];
    private Direction directionFacing;

    /**
     * Constructor for the character class.
     *
     * @param positionX - X coordinate of character's position
     * @param positionY - Y coordinate of character's position
     * @param health - Character's total health
     */
    public Character(int positionX, int positionY, int health) {
        this.setStartState(positionX, positionY);
        this.setPosition(positionX, positionY);
        this.setHealth(health);
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
        this.position[0] = x;
        this.position[1] = y;
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
        this.position[0] = x;
        this.position[1] = y;
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
}
