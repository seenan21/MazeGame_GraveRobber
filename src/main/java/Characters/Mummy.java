package Characters;

import IO.Keyboard;
import Map.Grid;
import Map.Level;


import java.util.*;
import java.awt.*;
import java.lang.*;

public class Mummy extends Character {

    private int[] target = new int[2];

    public Mummy(Grid grid, Keyboard keyboard, int positionX, int positionY
            /*Adds this parameter later for walls  Level level*/) {
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
    public double getMinValue(double[] array) {
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public Position[] getSuccessor(Position p){
        Position p1 = new Position(p.x + this._speed, p.y);
        Position p2 = new Position(p.x + this._speed, p.y);
        Position p3 = new Position(p.x + this._speed, p.y);
        Position p4 = new Position(p.x + this._speed, p.y);
        Position[] positions = new Position[4];
        positions[0] = p1;
        positions[1] = p2;
        positions[2] = p3;
        positions[3] = p4;

        return positions;
    }



    public int[] dfs(int[] starting, int[] target){
        Stack<Integer> visited = new Stack<>();




    }

    public void update(Character targetCharacter) {
        this.target = targetCharacter.getPosition();
        int[] target = this.target;

        int x = this.getPosition()[0];
        int y = this.getPosition()[1];


        double[] bestChoice = new double[4];

        double currentPos = euclideanDistance(x, y, target[0], target[1]);
        double north = euclideanDistance(x, y+this._speed, target[0], target[1]);
        double east = euclideanDistance(x+this._speed, y, target[0], target[1]);
        double south = euclideanDistance(x, y-this._speed, target[0], target[1]);
        double west = euclideanDistance(x-this._speed, y, target[0], target[1]);

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
            moveCharacter(Direction.EAST);
            moveCharacter(Direction.EAST);
        }
        else if(bestChoice[2] == min) {
            moveCharacter(Direction.SOUTH);
            moveCharacter(Direction.SOUTH);
        }
        else if(bestChoice[3] == min) {
            moveCharacter(Direction.WEST);
            moveCharacter(Direction.WEST);
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(this.getPosition()[0],this.getPosition()[1], Grid.getTileSize(), Grid.getTileSize());
    }




}


