package Map;

import Constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Obstacle {
    private int posX;
    private int posY;
    protected BufferedImage _sprite;
    Grid _grid;

    public Obstacle(int posX, int posY, Grid grid){
        this.posX = posX;
        this.posY = posY;
        _grid = grid;

    }

    public void setSprite(char assetSpecifier) {
        this._sprite = getSprite(assetSpecifier);
    }

    /**
     *
     * @param assetSpecifier
     * @return the image for the obstacle
     */
    public BufferedImage getSprite(char assetSpecifier) {

        if (assetSpecifier == Constants.GRAVE_1) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_1_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.GRAVE_2) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_2_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.GRAVE_3) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_top_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.GRAVE_4) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_bottom_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.WALL_VERTICAL_1) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_top_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.WALL_VERTICAL_2) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_middle_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.WALL_VERTICAL_3) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_bottom_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.WALL_VERTICAL_4) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (assetSpecifier == Constants.WALL_HORIZONTAL_1) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_1_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _sprite;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY(){return posY;}

    public void render(){} //Must implement this

    public void draw(Graphics2D g2) {
        g2.drawImage(_sprite,posX,posY, _grid.getTileSize(), _grid.getTileSize(), null);
    }

}
