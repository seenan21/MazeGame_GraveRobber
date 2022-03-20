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

public class Mummy extends Character implements Runnable {

    PlayerActor target;
    Position position;
    Boolean sleep;
    private int moveCounter = 0;

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

    public Direction followPlayer(Character hero) {
        int heroX = hero.getPosition()[Constants.X];
        int heroY = hero.getPosition()[Constants.Y];
        int skeletonX = this.getPosition()[Constants.X];
        int skeletonY = this.getPosition()[Constants.Y];
        Direction direction;

        if(moveCounter > 2) {
            if (heroX > skeletonX) {
                System.out.println("EAST");
                direction = Direction.EAST;
            } else {
                System.out.println("WEST");
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

        System.out.println(moveCounter);
        return direction;
    }

//    private Direction bestMove(Character Hero){
//        int[] heroPosition = Hero.getPosition();
//        Position targetPosition = new Position(heroPosition[Constants.X], heroPosition[Constants.Y]);
//        Position skeletonPosition = this.position;
//
//        Position[] positions = new Position[]{
//                new Position(skeletonPosition.x + this.getSpeed(), skeletonPosition.y),
//                new Position(skeletonPosition.x - this.getSpeed(), skeletonPosition.y),
//                new Position(skeletonPosition.x, skeletonPosition.y + this.getSpeed()),
//                new Position(skeletonPosition.x, skeletonPosition.y - this.getSpeed()),
//        };
//
//        int min = 9999;
//        Position bestPosition = positions[0];
//        for(Position position: positions){
//            int num = (int)Math.sqrt((targetPosition.y - position.y) * (targetPosition.y - position.y) + (targetPosition.x - position.x) * (targetPosition.x - position.x));
////            if(level.collisionCheck(this, position.x,position.y)){
////                continue;
////            }
//            if (num < min) {
//                min = num;
//                bestPosition = position;
//            }
//        }
//
//        Direction direction = Direction.NORTH;
//
//        if(moveToggle) {
//            if (bestPosition == positions[0]) {
//                direction = Direction.EAST;
//            } else if (bestPosition == positions[1]) {
//                direction = Direction.WEST;
//            }
//        } else {
//            if (bestPosition == positions[2]) {
//                direction = Direction.SOUTH;
//            } else if (bestPosition == positions[3]) {
//                direction = Direction.NORTH;
//            }
//        }
//
//        moveToggle = !moveToggle;
//        System.out.println(direction);
//
//        return direction;
//
//    }

    public void update() {
//        setNextMovement(bestMove(level.getHero()));
//        Position move = bestMove(level.getHero());
//        this.position = move;
//        this.setPosition(move.x,move.y);
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
        moveCharacter(followPlayer(level.getHero()));
    }
}


