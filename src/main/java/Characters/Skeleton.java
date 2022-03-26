package Characters;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import Map.Level;
import Map.Sound;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.lang.*;

/**
 * The skeleton enemy will follow the main character.
 */
public class Skeleton extends Character {

    PlayerActor target;
    Boolean sleep;
    private Sound sound = new Sound();
    private int moveCounter = 0;
    private GameState _gameState;

    public Skeleton(GameState gameState, int positionX, int positionY, Level level) {
        super(level);
        this._gameState = gameState;
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(1); //Testing speed
        target = level.getHero();
        sleep = true;
        getImage();
    }

    @Override
    public void getImage(){
        try{
            setSprite(Direction.NORTH, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy up-1.png")));
            setSprite(Direction.NORTH, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy up-2.png")));
            setSprite(Direction.NORTH, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy up-3.png")));
            setSprite(Direction.SOUTH,0, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy down-1.png")));
            setSprite(Direction.SOUTH,1, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy down-2.png")));
            setSprite(Direction.SOUTH,2, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy down-3.png")));
            setSprite(Direction.EAST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy right-1.png")));
            setSprite(Direction.EAST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy right-2.png")));
            setSprite(Direction.EAST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy right-3.png")));
            setSprite(Direction.WEST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy left-1.png")));
            setSprite(Direction.WEST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy left-2.png")));
            setSprite(Direction.WEST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/mummy/Mummy left-3.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Kills the player when on the same tile.
     * @param playerActor - main character
     * @return if the player has died
     */
    public boolean heroKill(PlayerActor playerActor){
        int[] position = new int[2];
        position = playerActor.getPosition();

        Rectangle z = new Rectangle(this.getPosition()[0],this.getPosition()[1],Constants.TILE_SIZE-10,Constants.TILE_SIZE-10);
        Rectangle h = new Rectangle(position[0], position[1], Constants.TILE_SIZE-10, Constants.TILE_SIZE-10);

        int i = 0;
        if (i == 0 && z.intersects(h)) {
            sound.playSound(6);
            i++;
        }
        return z.intersects(h);
    }

    /**
     * Tells the enemy which way to move to find the player
     * @param playerActor - Main Characer
     * @return the direction the enemy should move
     */
    public Direction followPlayer(PlayerActor playerActor) {
        int heroX = playerActor.getPosition()[Constants.X];
        int heroY = playerActor.getPosition()[Constants.Y];
        int skeletonX = this.getPosition()[Constants.X];
        int skeletonY = this.getPosition()[Constants.Y];
        Direction direction;

        // Toggle back and forth between horizontal and vertical
        if(moveCounter > 2) {
            if (heroX > skeletonX) {
                direction = Direction.EAST;
            } else {
                direction = Direction.WEST;
            }
        } else {
            if (heroY > skeletonY) {
                direction = Direction.SOUTH;
            } else {
                direction = Direction.NORTH;
            }
        }
        moveCounter++;
        if(moveCounter > 4) {
            moveCounter = 0;
        }

        return direction;
    }

    public void update() {
        if (heroKill(level.getHero())){
        _gameState.setGameState(GameStateType.END);
        }
    }

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
        g2.drawImage(sprite,getPosition()[Constants.X],getPosition()[Constants.Y], Constants.TILE_SIZE, Constants.TILE_SIZE, null);
    }

    /**
     * Checks for collision when moving the character.
     * @param direction - direction the character should move.
     */
    public void safeMoveCharacter(Direction direction) {
        if(direction == Direction.NORTH){
            if(level.collisionCheck(this, getPosition()[0], getPosition()[1] - this.getSpeed()) == false){
                moveCharacter(direction);
            }
        }
        else if(direction == Direction.SOUTH){
            if(level.collisionCheck(this, getPosition()[0], getPosition()[1] + this.getSpeed()) == false){
                moveCharacter(direction);
            }
        }
        else if(direction == Direction.WEST){
            if(level.collisionCheck(this,getPosition()[0] - this.getSpeed(), getPosition()[1]) == false) {
                moveCharacter(direction);
            }
        }
        else if(direction == Direction.EAST){
            if(level.collisionCheck(this,getPosition()[0] + this.getSpeed(), getPosition()[1]) == false) {
                moveCharacter(direction);
            }
        }
    }
}


