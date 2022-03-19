package Map;
import Constants.Constants;
import GUI.UI;
import IO.Keyboard;
import Map.tiles.CreateBackground;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The grid represents the layout of the map. Maps are quadrilaterals made of tiles.
 */
public class Grid extends JPanel implements Runnable{

    private final int TILE_SIZE = 32; // Size of an individual tile in pixels
    private final int TILE_SIZE_BACKGROUND = 128;
    private final int HORIZONTAL_TILES = 24; // Total tiles horizontally on map
    private final int VERTICAL_TILES = 24; // Total tiles vertically on map
    private final int HORIZONTAL_TILES_BACKGROUND = 6;
    private final int VERTICAL_TILES_BACKGROUND = 6;
    private final double FRAMES_PER_SECOND = 60;
    private final int _screenWidth = TILE_SIZE * HORIZONTAL_TILES;
    private final int _screenHeight = TILE_SIZE * VERTICAL_TILES;
    private int[] _startTile = new int[2]; // Starting tile for player when the game begins
    private int[] _endTile = new int[2]; // Ending tile for player when all treasures have been collected
    private Keyboard keyboard = new Keyboard();
    private Thread screenThread;
    private String path = "/level/level_1_foreground.fg";
    private Level level = new Level(this, keyboard, path);
    private UI ui = new UI(this, keyboard, level.getHero());
    Sound sound = new Sound();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int endState = 2;

    public boolean win = false;

    CreateBackground tilem = new CreateBackground(this);

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
     *
     * @return the number of vertical tiles for background art.
     */
    public int getVerticalTilesBackground() {
        return VERTICAL_TILES_BACKGROUND;
    }

    /**
     *
     * @return the number of horizontal tiles for background art.
     */
    public int getHorizontalTilesBackground() {
        return HORIZONTAL_TILES_BACKGROUND;
    }

    /**
     * Returns the number of maps tile size for background art.
     *
     * @return
     */
    public int getTileSizeBackground() {
        return TILE_SIZE_BACKGROUND;
    }

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
        playMusic(0);
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

        if (gameState == titleState) {
            // Title Page
            ui.draw(g2);
            g2.dispose();

        }
        if (gameState == playState) {

            // Background
            tilem.draw(g2);

            // Walls and characters
            level.draw(g2);

            // UI: health, time
            ui.draw(g2);
            g2.dispose(); // Saves memory
        }
        if (gameState == endState) {
            keyboard.changeGameState = 2;
            ui.draw(g2);
            g2.dispose();
        }
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSound(int i) {
        sound.setFile(i);
        sound.play();
    }
}
