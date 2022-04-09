package Map.tiles;

import Constants.Constants;
import Map.Grid;
import Map.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**]
 * Creates the background grid using background tiles.
 */
public class CreateBackground {

    private BackgroundTile[] _tile;
    private int _typesOfTiles = 14;
    private int _tileMap[][];
    private String _pathMap1 = "/level/level_1_background.bg";

    /**
     * Creates the background grid using background tiles.
     */
    public CreateBackground() throws IOException {
        _tile = new BackgroundTile[_typesOfTiles];
        _tileMap = new int[Constants.VERTICAL_TILES][Constants.HORIZONTAL_TILES];

        getTileImage();
        loadMap(_pathMap1);
    }

    /**
     * Loads the background tiles.
     */
    public void getTileImage() throws IOException {

        String backgroundImages[] = {"/map/grass_1_128.png",
                "/map/grass_2_128.png",
                "/map/grass_3_128.png",
                "/map/grass_4_128.png",
                "/map/wall_horizontal_1_128.png",
                "/map/wall_horizontal_2_128.png",
                "/map/wall_horizontal_3_128.png",
                "/map/wall_horizontal_4_128.png",
                "/map/wall_vertical_1_128.png",
                "/map/wall_vertical_2_128.png",
                "/map/grave_1_128.png",
                "/map/grave_2_128.png",
                "/map/grave_3_top_128.png",
                "/map/grave_3_bottom_128.png"
        };

        for (int i = 0; i < backgroundImages.length; i++) {
            _tile[i] = new BackgroundTile();
            _tile[i].image = ImageIO.read(getClass().getResourceAsStream(backgroundImages[i]));
        }
    }

    /**
     * Loads a new map.
     * @param mapFilePath - file path to map background file
     */
    public void loadMap(String mapFilePath){
        try {
            InputStream map = getClass().getResourceAsStream(mapFilePath);
            BufferedReader mapB = new BufferedReader(new InputStreamReader(map));

            int col = 0;
            int row = 0;

            while(col < Constants.VERTICAL_TILES_BACKGROUND && row < Constants.HORIZONTAL_TILES_BACKGROUND){
                String line = mapB.readLine();

                while(col < Constants.VERTICAL_TILES_BACKGROUND){
                    String theMap[] = line.split(" ");
                    int i = Integer.parseInt(theMap[col]);
                    _tileMap[col][row] = i;
                    col++;
                }
                if(col == Constants.VERTICAL_TILES_BACKGROUND){
                    col = 0;
                    row++;
                }
            }
            mapB.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < Constants.VERTICAL_TILES_BACKGROUND && row < Constants.HORIZONTAL_TILES_BACKGROUND){

            int tilesNum = _tileMap[col][row];
            g2.drawImage(_tile[tilesNum].image, x * Constants.TILE_SIZE_BACKGROUND, y * Constants.TILE_SIZE_BACKGROUND, Constants.TILE_SIZE_BACKGROUND, Constants.TILE_SIZE_BACKGROUND, null);
            col++;
            x++;

            if (col == Constants.VERTICAL_TILES_BACKGROUND){
                col = 0;
                x = 0;
                row++;
                y++;
            }
        }
    }

}
