package Characters;

import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Grid;
import Map.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import java.awt.*;

/**
 * The zombie enemy runs in straight lines.
 */
public class Zombie extends Character implements Runnable {
    private boolean _rush;
    private Direction _rushDirection;

    private GameState _gameState;

    public Zombie(GameState gameState, int positionX, int positionY, Level level) {
        super(level);
        this._gameState = gameState;
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(2); //Testing speed
        this.setStepsAllowed(1);
        this.getImage();
        this._rush = false;
        this.setCharacterType(CharacterType.ENEMY);
    }

    /**
     * Returns the sprite image for the zombie
     */


    public void getImage(){

        int numberOfSprites = 3;

        String spriteListNorth[] = {
                "Zombie up-1.png",
                "Zombie up-2.png",
                "Zombie up-3.png"
        };
        String spriteListSouth[] = {
                "Zombie down-1.png",
                "Zombie down-2.png",
                "Zombie down-3.png"
        };
        String spriteListEast[] = {
                "Zombie right-1.png",
                "Zombie right-2.png",
                "Zombie right-3.png",
        };
        String spriteListWest[] = {
                "Zombie left-1.png",
                "Zombie left-2.png",
                "Zombie left-3.png"
        };

        try{
            for (int i = 0; i < numberOfSprites; i++) {
                String filePath = "/sprite/zombie/";
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
     * The zombie rushes in a single direction
     * @param rushDirection - direction the zombie is running
     * @return the direction the zombie is running
     */
    public boolean rush(Direction rushDirection) {
        if(_rush){

            moveCharacter(rushDirection);

            if(!isClear) {
                _rush = false;
            }

        }
        return false;
    }

    public void update() {

        if (level.heroKill(this)){
            _gameState.setGameState(GameStateType.END);
        }

        // If the zombie is rushing forward, then we do not want to change direction
        if (_rush) {
            return;
        }

        _rush = true;

        Random rand = new Random();
        int n = rand.nextInt(4);

        if(n == 0) {
            _rushDirection = Direction.NORTH;
        }
        else if(n == 1) {
            _rushDirection = Direction.SOUTH;
        }
        else if(n == 2) {
            _rushDirection = Direction.WEST;
        }
        else if(n == 3) {
            _rushDirection = Direction.EAST;
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


    @Override
    public void run() {
        rush(_rushDirection);
    }

    public Direction get_rushDirection(){
        return _rushDirection;
    }
    public void set_rushDirection(Direction d){
        this._rushDirection = d;
    }

}
