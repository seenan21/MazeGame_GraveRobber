package Characters;

import Clock.TickClock;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import java.awt.*;

public class Zombie extends Character {
    boolean rush;
    Direction rushDirection;

    public Zombie(Grid grid, Keyboard keyboard, int positionX, int positionY, Level level, TickClock tickClock) {
        super(grid, keyboard, level, tickClock);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(1); //Testing speed
        this.getImage();
        rush = false;
    }

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

    public boolean heroKill(PlayerActor hero){
        int[] position = new int[2];
        position = hero.getPosition();

        Rectangle z = new Rectangle(this.getPosition()[0],this.getPosition()[1],_grid.getTileSize(),_grid.getTileSize());
        Rectangle h = new Rectangle(position[0], position[1], _grid.getTileSize(), _grid.getTileSize());

        return z.intersects(h);

    }

    public void update() {

        if (heroKill(level.getHero())){
            System.out.println("LLLLLLLLLLLL");
        }
        Random rand = new Random();

        int n = rand.nextInt(4);

        if(rush){
            if(rushDirection == Direction.NORTH){
                if(level.wallCheck(getPosition()[0], getPosition()[1] - this.getSpeed()) == false){
                    setNextMovement(rushDirection);
                    moveCharacter(rushDirection);
                    return;
                }
            }
            if(rushDirection == Direction.SOUTH){
                if(level.wallCheck(getPosition()[0], getPosition()[1] + this.getSpeed()) == false){
                    moveCharacter(rushDirection);
                    setNextMovement(rushDirection);
                    return;
                }
            }
            if(rushDirection == Direction.WEST){
                if(level.wallCheck(getPosition()[0] - this.getSpeed(), getPosition()[1]) == false) {
                    moveCharacter(rushDirection);
                    setNextMovement(rushDirection);
                    return;
                }
            }
            if(rushDirection == Direction.EAST){
                if(level.wallCheck(getPosition()[0] + this.getSpeed(), getPosition()[1]) == false) {
                    moveCharacter(rushDirection);
                    setNextMovement(rushDirection);
                    return;
                }
            }
            rush = false;
        }

        if(n == 0) {
            if(level.wallCheck(getPosition()[0], getPosition()[1] - this.getSpeed()) == false) {
                moveCharacter(Direction.NORTH);
                rushDirection = Direction.NORTH;
                rush = true;
            }
        }
        else if(n == 1) {
            if(level.wallCheck(getPosition()[0], getPosition()[1] + this.getSpeed()) == false) {
                moveCharacter(Direction.SOUTH);
                rushDirection = Direction.SOUTH;
                rush = true;
            }
        }
        else if(n == 2) {
            if(level.wallCheck(getPosition()[0] - this.getSpeed(), getPosition()[1]) == false) {
                moveCharacter(Direction.WEST);
                rushDirection = Direction.WEST;
                rush = true;
            }
        }
        else if(n == 3) {
            if(level.wallCheck(getPosition()[0] + this.getSpeed(), getPosition()[1]) == false) {
                moveCharacter(Direction.EAST);
                rushDirection = Direction.EAST;
                rush = true;
            }
        }


        if (heroKill(level.getHero())){
            System.out.println("LLLLLLLLLLLL");
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






}
