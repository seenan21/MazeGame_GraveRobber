package Characters;

import IO.Keyboard;
import Map.Grid;


import java.awt.*;
import java.util.Random;
import java.lang.*;
import java.util.Arrays;
import java.util.Collections;

public class Mummy extends Character {

    private int[] target = new int[2];

    public Mummy(Grid grid, Keyboard keyboard, int positionX, int positionY) {
        super(grid, keyboard);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(5); //Testing speed

    }

    public void render(Graphics g)
    {
        int[] position = this.getPosition();
        g.drawImage(sprite, position[0], position[1], null);
    }

    public double euclideanDistance(int ownPosx, int ownPosy, int posX, int posY) {
       return (Math.sqrt(Math.pow(ownPosx-posX,2) + Math.pow(ownPosy-posY,2)));

    }
    public static double getMinValue(double[] array) {
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public void update(Character targetCharacter) {
        this.target = targetCharacter.getPosition();
        int[] target = this.target;

        int x = this.getPosition()[0];
        int y = this.getPosition()[1];


        double[] bestChoice = new double[4];

        double currentPos = euclideanDistance(x, y, target[0], target[1]);
        double north = euclideanDistance(x, y+this._speed, target[0], target[1]) - currentPos;
        double east = euclideanDistance(x+this._speed, y, target[0], target[1]) - currentPos;
        double south = euclideanDistance(x, y-this._speed, target[0], target[1]) - currentPos;
        double west = euclideanDistance(x-this._speed, y, target[0], target[1]) - currentPos;

        bestChoice[0]=north;
        bestChoice[1]=east;
        bestChoice[2]=south;
        bestChoice[3]=west;


        Double min = getMinValue(bestChoice);



        if(bestChoice[0] == min) {
            moveCharacter(Direction.NORTH);
            moveCharacter(Direction.NORTH);
        }
        else if(bestChoice[1] == min) {
            moveCharacter(Direction.SOUTH);
            moveCharacter(Direction.SOUTH);
        }
        else if(bestChoice[2] == min) {
            moveCharacter(Direction.WEST);
            moveCharacter(Direction.WEST);
        }
        else if(bestChoice[3] == min) {
            moveCharacter(Direction.EAST);
            moveCharacter(Direction.EAST);
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(this.getPosition()[0],this.getPosition()[1], Grid.getTileSize(), Grid.getTileSize());
    }




}


