package Characters;

import Clock.TickClock;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;


import java.util.*;
import java.awt.*;
import java.lang.*;

public class Mummy extends Character {

    PlayerActor target;

    public Mummy(Grid grid, Keyboard keyboard, int positionX, int positionY, Level level, TickClock tickClock) {
        super(grid, keyboard, level, tickClock);
        this.setPosition(positionX, positionY);
        this.setStartState(positionX, positionY);
        this.setSpeed(5); //Testing speed
        target = level.getHero();

    }

    @Override
    public void getImage() {
        //
    }

    public void render(Graphics g)
    {
        int[] position = this.getPosition();
        g.drawImage(getSprite(Direction.SOUTH), position[0], position[1], null);
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
        Position p1 = new Position(p.x + getSpeed(), p.y);
        Position p2 = new Position(p.x - getSpeed(), p.y);
        Position p3 = new Position(p.x, p.y + getSpeed());
        Position p4 = new Position(p.x, p.y - getSpeed());
        Position[] positions = new Position[4];
        positions[0] = p1;
        positions[1] = p2;
        positions[2] = p3;
        positions[3] = p4;

        return positions;
    }

    class PositionList{
        Position position;
        ArrayList<Position> posList;

        PositionList(Position pos, ArrayList<Position> list){
            this.position =pos;
            this.posList = list;
        }

    }

    public ArrayList<Position> dfs(Position starting, Position target){
        Stack<PositionList> fringe = new Stack<>();
        ArrayList<Position> visited = new ArrayList<Position>();
        ArrayList<Position> posList = new ArrayList<Position>();

        PositionList root = new PositionList(starting, posList );
        fringe.push(root);
        while(!fringe.isEmpty()){
            PositionList v = fringe.pop();

            if (v.position == target){
                return v.posList;
            }

            if (!visited.contains(v.position)){
                visited.add(v.position);
                for (Position p: getSuccessor(v.position) ){
                    PositionList p2 = new PositionList(p, v.posList);
                    fringe.push(p2);
                }
            }

        }
        return visited;
    }

    public void update(Character targetCharacter) {
        Position targetPos = new Position(this.target.getPosition()[0], this.target.getPosition()[1]);
        Position self = new Position(this.getPosition()[0], this.getPosition()[1]);

        ArrayList<Position> moves = dfs(self, targetPos);

        this.setPosition(moves.get(0).x,moves.get(0).y);
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(this.getPosition()[0],this.getPosition()[1], _grid.getTileSize(), _grid.getTileSize());
    }




}


