package Map;

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

    public void setSprite(ObstacleSpecifier obstacleSpecifier) {
        this._sprite = getSprite(obstacleSpecifier);
    }

    /**
     *
     * @param obstacleSpecifier
     * @return the image for the obstacle
     */
    public BufferedImage getSprite(ObstacleSpecifier obstacleSpecifier) {

        if (obstacleSpecifier == ObstacleSpecifier.GRAVE_1) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_1_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.GRAVE_2) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_2_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.GRAVE_3_TOP) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_top_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.GRAVE_3_BOTTOM) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_bottom_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.WALL_TOP) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_top_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.WALL_BOTTOM) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_middle_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.WALL_BOTTOM) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_bottom_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.WALL_VERTICAL) {
            try {
                this._sprite = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_128.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (obstacleSpecifier == ObstacleSpecifier.WALL_HORIZONTAL_1) {
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
