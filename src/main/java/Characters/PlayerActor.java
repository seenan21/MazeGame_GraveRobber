package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;

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
            if(level.wallCheck(getPosition()[0], getPosition()[1] - this.getSpeed(), Direction.NORTH) == false) {
                moveCharacter(Direction.NORTH);
            }

        } else if (_keyboard.downKeyPressed) {
            if(level.wallCheck(getPosition()[0], getPosition()[1] + this.getSpeed(), Direction.SOUTH) == false) {
                moveCharacter(Direction.SOUTH);
            }
        } else if (_keyboard.leftKeyPressed) {
            if(level.wallCheck(getPosition()[0] - this.getSpeed(), getPosition()[1], Direction.WEST) == false) {
                moveCharacter(Direction.WEST);
            }
        } else if (_keyboard.rightKeyPressed) {
            if(level.wallCheck(getPosition()[0] + this.getSpeed(), getPosition()[1], Direction.EAST) == false) {
                moveCharacter(Direction.EAST);
            }
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
