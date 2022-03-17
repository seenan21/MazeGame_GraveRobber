package Map;
import Characters.Character;
import Characters.PlayerActor;
import Characters.Zombie;
import Constants.Constants;
import GUI.PopUpWindow;
import GUI.UI;
import IO.Keyboard;
import Map.tiles.TileM;
import items.Item;
import items.ItemDetection;
import items.Treasure;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The grid represents the layout of the map. Maps are quadrilaterals made of tiles.
 */
public class Grid extends JPanel implements Runnable{

    private final int TILE_SIZE = 32; // Size of an individual tile in pixels
    private final int HORIZONTAL_TILES = 24; // Total tiles horizontally on map
    private final int VERTICAL_TILES = 24; // Total tiles vertically on map
    private final double FRAMES_PER_SECOND = 60;
    private final int _screenWidth = TILE_SIZE * HORIZONTAL_TILES;
    private final int _screenHeight = TILE_SIZE * VERTICAL_TILES;
    private int[] _startTile = new int[2]; // Starting tile for player when the game begins
    private int[] _endTile = new int[2]; // Ending tile for player when all treasures have been collected
    private Keyboard keyboard = new Keyboard();
    private UI ui = new UI(this, keyboard);
    private Thread screenThread;
    private GridSquareFactory gridSquareFactory = new GridSquareFactory(this);
    private String path = "/level/level_1";
    private Level level = new Level(this, keyboard, path);

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int endState = 2;

    TileM tilem = new TileM(this);



    /**
     * Creates the game screen and sets up a keyboard listener.
     */
    public Grid() throws IOException {
        this.setPreferredSize(new Dimension(_screenWidth, _screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        this.setDefault();
    }

    /**
     * Returns the map's width.
     */
    public int getScreenWidth() {
        return _screenWidth;
    }

    /**
     * Returns the number of horizontal tiles on the map.
     *
     * @return
     */
    public int getHorizontalTiles(){return HORIZONTAL_TILES;}

    /**
     * Returns the number of vertical tiles on the map.
     *
     * @return
     */
    public int getVerticalTiles() { return VERTICAL_TILES;    }

    /**
     * Returns the map's height.
     */
    public int getScreenHeight() {
        return _screenHeight;
    }

    /**
     * Returns the number of maps tile size.
     *
     * @return
     */
    public int getTileSize() {
        return TILE_SIZE;
    }

    /**
     * Returns the player's starting tile.
     */
    public int[] getStartTile() {
        return _startTile;
    }

    /**
     * Returns the player's ending tile.
     */
    public int[] getEndTile() {
        return _endTile;
    }

    /**
     * Sets the map's default values.
     */
    public void setDefault() {
        this._startTile[Constants.X] = 0;
        this._startTile[Constants.Y] = 0;
        this._endTile[Constants.X] = 0;
        this._endTile[Constants.Y] = 0;
    }

    /**
     * Starts the thread for the map.
     */
    public void startThread() {
        screenThread = new Thread(this);
        screenThread.start(); // Calls this.run()
    }

    /**
     * @return current level on grid.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Loop which tells the UI to update every tick.
     * Also checks if character locations have changed.
     */
    @Override
    public void run() {
        while(screenThread != null) {

            double tick = 1000000000/FRAMES_PER_SECOND;
            double nextTick = System.nanoTime() + tick;

            while(screenThread != null) {

                update();
                repaint(); // Calls this.paintComponent

                    try {
                        double timeTillNextTick = nextTick - System.nanoTime();
                        timeTillNextTick = timeTillNextTick / 1000000;// Nanoseconds to milliseconds conversion

                        // Error check
                        if (timeTillNextTick < 0) {
                            timeTillNextTick = 0;
                        }

                        Thread.sleep((long) timeTillNextTick); // Sleep until tick is over

                        nextTick += tick;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    /**
     * Updates the character and enemy movements.
     */
    public void update() {
        level.update();

    }

    /**
     * Java standard module for updating GUI
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Title Page
        if (gameState == titleState) {
            ui.draw(g2);
            g2.dispose();
        // Background
        }
        if (gameState == playState) {
            tilem.draw(g2);
            level.draw(g2);

            //gridSquareFactory.draw(g2);
            //level.draw(g2);


            // UI
            ui.draw(g2);
            if (ui.time >= Constants.TIME_LIMIT) {
                ui.draw(g2);
            }
            g2.dispose(); // Saves memory
        }
        if (gameState == endState) {
            System.exit(0);
        }
    }
}
