package Characters;

import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import java.util.Random;

import java.awt.*;

public class Zombie extends Character {

    public Zombie(Grid grid, Keyboard keyboard, int positionX, int positionY) {
        super(grid, keyboard);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(3); //Testing speed

    }

    public void render(Graphics g)
    {
        int[] position = this.getPosition();
        g.drawImage(sprite, position[0], position[1], null);
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
        g2.setColor(Color.GREEN);
        g2.fillRect(this.getPosition()[0],this.getPosition()[1], _grid.getTileSize(), _grid.getTileSize());
    }






}
