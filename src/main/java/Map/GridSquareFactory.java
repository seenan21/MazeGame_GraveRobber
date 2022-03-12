package Map;

import Constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * INFO HERE
 */
public class GridSquareFactory {
    Grid grid;
    GridSquare[] gridSquare;

    /**
     * INFO HERE
     */
    public GridSquareFactory(Grid grid) {
        this.grid = grid;

        gridSquare = new GridSquare[1];

        getGridSquareImage();

    }

    /**
     * INFO HERE
     */
    public void getGridSquareImage() {
        try {
            gridSquare[Constants.GRASS] = new GridSquare();

            gridSquare[Constants.GRASS].setSquare(ImageIO.read(getClass().getResourceAsStream("/map/grass_1_128.png")));
        } catch(IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * INFO HERE
     */
    public void draw(Graphics2D g2) {
        for (int i = 0; i < grid.getHorizontalTiles(); i++) {
            for (int j = 0; j < grid.getVerticalTiles(); j++) {
                g2.drawImage(gridSquare[Constants.GRASS].getSquare(), i * grid.getTileSize(), j * grid.getTileSize(), grid.getTileSize(), grid.getTileSize(),null);
            }
        }
    }
}
