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
            System.out.println("UP");
            moveCharacter(Direction.NORTH);
            System.out.println("X= " + this.getPosition()[0] + " Y= " + this.getPosition()[1]);
        } else if (_keyboard.downKeyPressed) {
            System.out.println("Down");
            moveCharacter(Direction.SOUTH);
            System.out.println("X= " + this.getPosition()[0] + " Y= " + this.getPosition()[1]);
        } else if (_keyboard.leftKeyPressed) {
            System.out.println("Left");
            moveCharacter(Direction.WEST);
            System.out.println("X= " + this.getPosition()[0] + " Y= " + this.getPosition()[1]);
        } else if (_keyboard.rightKeyPressed) {
            System.out.println("Right");
            moveCharacter(Direction.EAST);
            System.out.println("X= " + this.getPosition()[0] + " Y= " + this.getPosition()[1]);
        }
        spritecounter++;
        if (spritecounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;

            } else if (spriteNum == 2) {
                spriteNum = 3;

            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spritecounter = 0;
        }
    }
    public void getImage(){
        try{
            setSprite(Direction.NORTH, ImageIO.read(getClass().getResourceAsStream("/sprite/MaleUp-2.png")));
            setSprite(Direction.SOUTH, ImageIO.read(getClass().getResourceAsStream("/sprite/MaleDown-2.png")));
            setSprite(Direction.EAST, ImageIO.read(getClass().getResourceAsStream("/sprite/MaleRight-2.png")));
            setSprite(Direction.WEST, ImageIO.read(getClass().getResourceAsStream("/sprite/MaleLeft-2.png")));
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
