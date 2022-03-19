package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import java.awt.*;

/**
 *
 */
public class Zombie extends Character implements Runnable {
    private boolean _rush;
    private Direction _rushDirection;

    public Zombie(Grid grid, Keyboard keyboard, int positionX, int positionY, Level level) {
        super(grid, keyboard, level);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(2); //Testing speed
        this.setStepsAllowed(1);
        this.getImage();
        this._rush = false;
        this.setCharacterType(CharacterType.ENEMY);
    }

    /**
     *
     */
    public void getImage(){
        try{
            setSprite(Direction.NORTH, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie up-1.png")));
            setSprite(Direction.NORTH, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie up-2.png")));
            setSprite(Direction.NORTH, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie up-3.png")));
            setSprite(Direction.SOUTH,0, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie down-1.png")));
            setSprite(Direction.SOUTH,1, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie down-2.png")));
            setSprite(Direction.SOUTH,2, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie down-3.png")));
            setSprite(Direction.EAST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie right-1.png")));
            setSprite(Direction.EAST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie right-2.png")));
            setSprite(Direction.EAST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie right-3.png")));
            setSprite(Direction.WEST, 0, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie left-1.png")));
            setSprite(Direction.WEST, 1, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie left-2.png")));
            setSprite(Direction.WEST, 2, ImageIO.read(getClass().getResourceAsStream("/sprite/zombie/Zombie left-3.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param hero
     * @return
     */
    public boolean heroKill(PlayerActor hero){
        int[] position = new int[2];
        position = hero.getPosition();

        Rectangle z = new Rectangle(this.getPosition()[0],this.getPosition()[1],_grid.getTileSize()-10,_grid.getTileSize()-10);
        Rectangle h = new Rectangle(position[0], position[1], _grid.getTileSize()-10, _grid.getTileSize()-10);

        return z.intersects(h);

    }

    /**
     *
     * @param rushDirection
     * @return
     */
    public boolean rush(Direction rushDirection) {
        if(_rush){
            if(this._rushDirection == Direction.NORTH){
                if(level.collisionCheck(this, getPosition()[0], getPosition()[1] - this.getSpeed()) == false){
                    moveCharacter(this._rushDirection);
                    return true;
                }
            }
            else if(this._rushDirection == Direction.SOUTH){
                if(level.collisionCheck(this, getPosition()[0], getPosition()[1] + this.getSpeed()) == false){
                    moveCharacter(this._rushDirection);
                    return true;
                }
            }
            else if(this._rushDirection == Direction.WEST){
                if(level.collisionCheck(this,getPosition()[0] - this.getSpeed(), getPosition()[1]) == false) {
                    moveCharacter(this._rushDirection);
                    return true;
                }
            }
            else if(this._rushDirection == Direction.EAST){
                if(level.collisionCheck(this,getPosition()[0] + this.getSpeed(), getPosition()[1]) == false) {
                    moveCharacter(this._rushDirection);
                    return true;
                }
            }
            _rush = false;
        }
        return false;
    }

    public void update() {

        if (heroKill(level.getHero())){
            //_keyboard.changeGameState = 2;
            _grid.gameState = 2;

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
        g2.drawImage(sprite,getPosition()[Constants.X],getPosition()[Constants.Y], _grid.getTileSize(), _grid.getTileSize(), null);
    }


    @Override
    public void run() {
        rush(_rushDirection);
    }
}
