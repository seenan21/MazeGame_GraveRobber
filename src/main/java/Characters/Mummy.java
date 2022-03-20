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
        int[] t = Hero.getPosition();
        Position target = new Position(t[0], t[1]);
        Position p0 = this.position;


        Position[] positions = new Position[]{
                new Position(p0.x + this.getSpeed(), p0.y),
                new Position(p0.x - this.getSpeed(), p0.y),
                new Position(p0.x, p0.y + this.getSpeed()),
                new Position(p0.x, p0.y - this.getSpeed()),
        };


        int min = 9999;
        Position bestP = positions[0];
        for(Position p: positions){
            int num = (int)Math.sqrt((target.y - p.y) * (target.y - p.y) + (target.x - p.x) * (target.x - p.x));
            if(level.collisionCheck(this, p.x,p.y)){
                continue;
            }
            if (num < min) {
                min = num;
                bestP = p;
            }
        }
        //System.out.println(bestP.x);
        return bestP;

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


