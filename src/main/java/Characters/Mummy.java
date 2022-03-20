package Characters;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.lang.*;

public class Mummy extends Character {

    PlayerActor target;
    Position position;
    Boolean sleep;

    public Mummy(Grid grid, Keyboard keyboard, int positionX, int positionY, Level level) {
        super(grid, keyboard, level);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(1); //Testing speed
        target = level.getHero();
        position = new Position(positionX, positionY);
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

    private Position bestMove(Character Hero){
        int[] heroPosition = Hero.getPosition();
        Position targetPosition = new Position(heroPosition[Constants.X], heroPosition[Constants.Y]);
        Position skeletonPosition = this.position;

        Position[] positions = new Position[]{
                new Position(skeletonPosition.x + this.getSpeed(), skeletonPosition.y),
                new Position(skeletonPosition.x - this.getSpeed(), skeletonPosition.y),
                new Position(skeletonPosition.x, skeletonPosition.y + this.getSpeed()),
                new Position(skeletonPosition.x, skeletonPosition.y - this.getSpeed()),
        };

        int min = 9999;
        Position bestPosition = positions[0];
        for(Position position: positions){
            int num = (int)Math.sqrt((targetPosition.y - position.y) * (targetPosition.y - position.y) + (targetPosition.x - position.x) * (targetPosition.x - position.x));
            if(level.collisionCheck(this, position.x,position.y)){
                continue;
            }
            if (num < min) {
                min = num;
                bestPosition = position;
            }
        }

        if (bestPosition.x < skeletonPosition.x) {
            setDirectionFacing(Direction.WEST);
        } else if ((bestPosition.x > skeletonPosition.x)) {
            setDirectionFacing(Direction.EAST);
        } else if ((bestPosition.y < skeletonPosition.y)) {
            setDirectionFacing(Direction.NORTH);
        } else if ((bestPosition.y > skeletonPosition.y)) {
            setDirectionFacing(Direction.SOUTH);
        }


        return bestPosition;

    }

    public void update() {
        Position move = bestMove(level.getHero());
        this.position = move;
        this.setPosition(move.x,move.y);
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
        else {
            sprite = getSprite(Direction.SOUTH);
        }
        g2.drawImage(sprite,getPosition()[Constants.X],getPosition()[Constants.Y], _grid.getTileSize(), _grid.getTileSize(), null);
    }




}


