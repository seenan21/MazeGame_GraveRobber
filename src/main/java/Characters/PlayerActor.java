package Characters;

import Clock.TickClock;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import items.ItemDetection;
import Map.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This PlayerActor refers to the main character controlled by the user. The player should be able to pickup items,
 * move north, south, east, and west, and have a set amount of health. The player can only be on one map at a time.
 *
 * The player will always spawn at the map's startCell.
 */
public class PlayerActor extends Character implements Runnable{

    private boolean _hasBossReward;
    public int _health = 3;

    /**
     * Constructor for the character class.
     *  @param grid - Grid which is used for game map
     * @param keyboard - Listener for keyboard
     * @param position
     */
    public PlayerActor(Grid grid, Keyboard keyboard, int[] position, Level level) {
        super(grid, keyboard, level);
        this.setStartState(position[0], position[1]);
        this.setPosition(position[0], position[1]);
        this.setDefault();
        this.getImage();
    }

    /**
     * Changes the status of final boss award for the player
     *
     * @param hasBossReward - Player has achieved the final boss award
     */
    public void setHasBossReward(boolean hasBossReward) {
        this._hasBossReward = hasBossReward;
    }

    /**
     * @return if the player has the final boss's reward
     */
    public boolean getHasBossReward() {
        return this._hasBossReward;
    }

    /**
     * Default values for player
     */
    public void setDefault() {
        this.setHealth(100);
        this.setSpeed(2);
        this.setHasBossReward(false);
        this.setPosition(getStartState()[Constants.X],getStartState()[Constants.Y]);
        this.setDirectionFacing(Direction.SOUTH);
    }

    /**
     * Updates the player's position when player presses W,A,S,D keys on keyboard.
     */
    public void update() {
        if (_keyboard.upKeyPressed) {
            setNextMovement(Direction.NORTH);
        }
        else if (_keyboard.downKeyPressed) {
            setNextMovement(Direction.SOUTH);
        }
        else if (_keyboard.leftKeyPressed) {
            setNextMovement(Direction.WEST);
        }
        else if (_keyboard.rightKeyPressed) {
            setNextMovement(Direction.EAST);
        }
    }

    /**
     * Finds the sprite image for the player.
     */
    public void getImage(){
        try{
            setSprite(Direction.NORTH, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_up_0.png")));
            setSprite(Direction.NORTH, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_up_1.png")));
            setSprite(Direction.NORTH, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_up_2.png")));
            setSprite(Direction.SOUTH,0, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_down_0.png")));
            setSprite(Direction.SOUTH,1, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_down_1.png")));
            setSprite(Direction.SOUTH,2, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_down_2.png")));
            setSprite(Direction.EAST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_right_0.png")));
            setSprite(Direction.EAST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_right_1.png")));
            setSprite(Direction.EAST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_right_2.png")));
            setSprite(Direction.WEST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_left_0.png")));
            setSprite(Direction.WEST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_left_1.png")));
            setSprite(Direction.WEST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_left_2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds to the player's total health.
     *
     * @param addedPoints - Points to be added to the player's health
     */
    public void addToHealth(int addedPoints) { this._health = this._health + addedPoints; }

    /**
     * Returns the player's current health.
     */
    public int getHealth() {
        return _health;
    }

    /**
     * Draws the player's position when player presses W,A,S,D keys on keyboard.
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage sprite = null;
        if (getDirectionFacing() == Direction.NORTH) {
            sprite = getSprite(Direction.NORTH);
        }
        else if (getDirectionFacing() == Direction.SOUTH) {
            sprite = getSprite(Direction.SOUTH);
        }
        else if (getDirectionFacing() == Direction.EAST) {
            sprite = getSprite(Direction.EAST);
        }
        else if (getDirectionFacing() == Direction.WEST) {
            sprite = getSprite(Direction.WEST);
        } else {
            sprite = getSprite(Direction.SOUTH);
        }
        g2.drawImage(sprite,getPosition()[Constants.X],getPosition()[Constants.Y], _grid.getTileSize(), _grid.getTileSize(), null);
    }

    @Override
    public void run() {
        this.moveCharacter(getNextMovement());
    }
}
