package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import java.awt.*;

public class Zombie extends Character {

    public Zombie(Grid grid, Keyboard keyboard, int positionX, int positionY) {
        super(grid, keyboard);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(3); //Testing speed
        this.getImage();
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

    public void update() {
        Random rand = new Random();

        int n = rand.nextInt(4);

        if(n == 0) {
            moveCharacter(Direction.NORTH);
            moveCharacter(Direction.NORTH);
        }
        else if(n == 1) {
            moveCharacter(Direction.SOUTH);
            moveCharacter(Direction.SOUTH);
        }
        else if(n == 2) {
            moveCharacter(Direction.WEST);
            moveCharacter(Direction.WEST);
        }
        else if(n == 3) {
            moveCharacter(Direction.EAST);
            moveCharacter(Direction.EAST);
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