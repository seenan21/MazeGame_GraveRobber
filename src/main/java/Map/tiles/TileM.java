package Map.tiles;

import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileM {

    Grid gr;
    Tile[] tile;
    int typesOfTiles = 100;
    int tileMap[][];
    String map1 = "/map/tile.txt";

    public TileM(Grid gr){
        this.gr = gr;
        tile = new Tile[typesOfTiles];
        tileMap = new int[gr.getVerticalTiles()][gr.getHorizontalTiles()];

        getTileImage();
        loadMap(map1);
    }

    public void getTileImage(){
        try{
            // grass type 1
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_1_128.png"));

            // grass type 2
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_2_128.png"));

            // grass type 3
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_3_128.png"));

            // grass type 4
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/map/grass_4_128.png"));

            // wall horizontal 1
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_1_128.png"));

            // wall horizontal 2
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_2_128.png"));

            // wall horizontal 3
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_3_128.png"));

            // wall horizontal 4
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_horizontal_4_128.png"));

            // wall vertical 1
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_1_128.png"));

            // wall vertical 2
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/map/wall_vertical_2_128.png"));

            // grave type 1
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_1_128.png"));

            // grave type 2
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_2_128.png"));

            // grave type 3 top
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_top_128.png"));

            // grave type 3 bottom
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/map/grave_3_bottom_128.png"));
        }catch (IOException e){

        }
    }

    public void loadMap(String mapFilePath){
        try {
            InputStream map = getClass().getResourceAsStream("/map/tile.txt");
            BufferedReader mapB = new BufferedReader(new InputStreamReader(map));

            int col = 0;
            int row = 0;

            while(col < gr.getVerticalTiles() && row < gr.getHorizontalTiles()){
                String line = mapB.readLine();

                while(col < gr.getVerticalTiles()){
                    String theMap[] = line.split(" ");
                    int i = Integer.parseInt(theMap[col]);
                    tileMap[col][row] = i;
                    col++;
                }
                if(col == gr.getVerticalTiles()){
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
//        g2.drawImage(tile[0].image, 0, 0, gr.getTileSize(), gr.getTileSize(), null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gr.getVerticalTiles() && row < gr.getHorizontalTiles()){

            int tilesNum = tileMap[col][row];
            g2.drawImage(tile[tilesNum].image, x * gr.getTileSize(), y * gr.getTileSize(), gr.getTileSize(), gr.getTileSize(), null);
            col++;
            x++;

            if (col == gr.getVerticalTiles()){
                col = 0;
                x = 0;
                row++;
                y++;
            }
        }
    }

}
