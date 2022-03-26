package Map.tiles;

import Constants.Constants;
import Map.Grid;

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
    public CreateBackground(){
        _tile = new BackgroundTile[_typesOfTiles];
        _tileMap = new int[Constants.VERTICAL_TILES][Constants.HORIZONTAL_TILES];

        getTileImage();
        loadMap(_pathMap1);
    }

    /**
     * Loads the background tiles.
     */
    public void getTileImage(){
        try{
            // grass type 1
            _tile[0] = new BackgroundTile();
            _tile[0].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_1_128.png"));

            // grass type 2
            _tile[1] = new BackgroundTile();
            _tile[1].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_2_128.png"));

            // grass type 3
            _tile[2] = new BackgroundTile();
            _tile[2].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_3_128.png"));

            // grass type 4
            _tile[3] = new BackgroundTile();
            _tile[3].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_4_128.png"));

            // wall horizontal 1
            _tile[4] = new BackgroundTile();
            _tile[4].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_1_128.png"));

            // wall horizontal 2
            _tile[5] = new BackgroundTile();
            _tile[5].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_2_128.png"));

            // wall horizontal 3
            _tile[6] = new BackgroundTile();
            _tile[6].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_3_128.png"));

            // wall horizontal 4
            _tile[7] = new BackgroundTile();
            _tile[7].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_4_128.png"));

            // wall vertical 1
            _tile[8] = new BackgroundTile();
            _tile[8].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_1_128.png"));

            // wall vertical 2
            _tile[9] = new BackgroundTile();
            _tile[9].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_2_128.png"));

            // grave type 1
            _tile[10] = new BackgroundTile();
            _tile[10].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_1_128.png"));

            // grave type 2
            _tile[11] = new BackgroundTile();
            _tile[11].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_2_128.png"));

            // grave type 3 top
            _tile[12] = new BackgroundTile();
            _tile[12].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_top_128.png"));

            // grave type 3 bottom
            _tile[13] = new BackgroundTile();
            _tile[13].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_bottom_128.png"));
        }catch (IOException e){
            e.printStackTrace();
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
