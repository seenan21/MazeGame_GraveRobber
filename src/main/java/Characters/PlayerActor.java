package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;

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
public class PlayerActor extends Character{

    private boolean hasBossReward;

    /**
     * Constructor for the character class.
     *
     * @param grid - Grid which is used for game map
     * @param keyboard - Listener for keyboard
     */
    public PlayerActor(Grid grid, Keyboard keyboard) {
        super(grid, keyboard);
        this.setStartState(grid.getStartTile()[Constants.X], grid.getStartTile()[Constants.Y]);
        this.setPosition(grid.getStartTile()[Constants.X], grid.getStartTile()[Constants.Y]);
        this.setDefault();
        this.getImage();
    }

    /**
     * Changes the status of final boss award for the player
     *
     * @param hasBossReward - Player has achieved the final boss award
     */
    public void setHasBossReward(boolean hasBossReward) {
        this.hasBossReward = hasBossReward;
    }

    /**
     * Returns if the player has the final boss's reward
     */
    public boolean getHasBossReward() {
        return this.hasBossReward;
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
            moveCharacter(Direction.NORTH);
        } else if (_keyboard.downKeyPressed) {
            moveCharacter(Direction.SOUTH);
        } else if (_keyboard.leftKeyPressed) {
            moveCharacter(Direction.WEST);
        } else if (_keyboard.rightKeyPressed) {
            moveCharacter(Direction.EAST);
        }
    }

    /**
     * Finds the sprite image for the player.
     */
    public void getImage(){
        try{
            setSprite(Direction.NORTH, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_up_0.png")));
            setSprite(Direction.NORTH, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_up_1.png")));
            setSprite(Direction.NORTH, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_up_2.png")));
            setSprite(Direction.SOUTH,0, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_down_0.png")));
            setSprite(Direction.SOUTH,1, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_down_1.png")));
            setSprite(Direction.SOUTH,2, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_down_2.png")));
            setSprite(Direction.EAST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_right_0.png")));
            setSprite(Direction.EAST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_right_1.png")));
            setSprite(Direction.EAST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_right_2.png")));
            setSprite(Direction.WEST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_left_0.png")));
            setSprite(Direction.WEST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_left_1.png")));
            setSprite(Direction.WEST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/hero_left_2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the player's position when player presses W,A,S,D keys on keyboard.
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
        }
        g2.drawImage(sprite,getPosition()[Constants.X],getPosition()[Constants.Y], _grid.getTileSize(), _grid.getTileSize(), null);
    }
}
