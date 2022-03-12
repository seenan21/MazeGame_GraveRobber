package Map;

import Constants.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Wall {
    private int posX;
    private int posY;
    protected BufferedImage sprite;
    Grid _grid;

    public Wall(int posX, int posY, Grid grid){
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
        _grid = grid;

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY(){return posY;}

    public void render(){} //Must implement this

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(posX, posY, _grid.getTileSize(), _grid.getTileSize());
    }

}
