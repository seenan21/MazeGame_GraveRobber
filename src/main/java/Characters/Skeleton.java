package Characters;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import Map.Level;


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

        int numberOfSprites = 3;

        String spriteListNorth[] = {
                "Mummy up-1.png",
                "Mummy up-2.png",
                "Mummy up-3.png"
        };
        String spriteListSouth[] = {
                "Mummy down-1.png",
                "Mummy down-2.png",
                "Mummy down-3.png"
        };
        String spriteListEast[] = {
                "Mummy right-1.png",
                "Mummy right-2.png",
                "Mummy right-3.png",
        };
        String spriteListWest[] = {
                "Mummy left-1.png",
                "Mummy left-2.png",
                "Mummy left-3.png"
        };

        try{
            for (int i = 0; i < numberOfSprites; i++) {
                String filePath = "/sprite/mummy/";
                setSprite(Direction.NORTH, i, ImageIO.read(getClass().getResourceAsStream(filePath + spriteListNorth[i])));
                setSprite(Direction.SOUTH, i, ImageIO.read(getClass().getResourceAsStream(filePath + spriteListSouth[i])));
                setSprite(Direction.EAST, i, ImageIO.read(getClass().getResourceAsStream(filePath + spriteListEast[i])));
                setSprite(Direction.WEST, i, ImageIO.read(getClass().getResourceAsStream(filePath + spriteListWest[i])));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
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
        if (level.heroKill(this)){
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
}


