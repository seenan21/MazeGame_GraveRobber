package Map;
import Characters.Character;
import Characters.PlayerActor;
import Characters.Zombie;
import Constants.Constants;
import IO.Keyboard;
import items.Item;
import items.ItemDetection;
import items.Treasure;

import javax.swing.*;
import java.awt.*;
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
    private ArrayList<Character> characters = new ArrayList<>();
    private final int ITEM_LIMIT = 5;

    private Keyboard keyboard = new Keyboard();
    private Thread screenThread;
    private PlayerActor playerActor = new PlayerActor(this, this.keyboard);
    private ItemDetection itemDetection = new ItemDetection(this);
    private GridSquareFactory gridSquareFactory = new GridSquareFactory(this);
    public Item treasure[] = new Item[ITEM_LIMIT]; // Need to solve later so this does not need to be public
    private Zombie zombo = new Zombie(this, this.keyboard, _screenWidth/2, _screenHeight/2); // Just for testing rn
//    Mummy mum =new Mummy(this, this.keyboard, _screenWidth/2+20, _screenHeight/2+20); // Just for testing rn

    /**
     * Creates the game screen and sets up a keyboard listener.
     */
    public Grid() {
        this.setPreferredSize(new Dimension(_screenWidth, _screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        this.setDefault();

        // Later this should be generated when the map is created.
        treasure[0] = new Treasure(this, 5*TILE_SIZE, 5*TILE_SIZE);
        treasure[1] = new Treasure(this, 10*TILE_SIZE, 4*TILE_SIZE);
        treasure[2] = new Treasure(this, 22*TILE_SIZE, 3*TILE_SIZE);
        treasure[3] = new Treasure(this, 11*TILE_SIZE, 20*TILE_SIZE);
        treasure[4] = new Treasure(this, 20*TILE_SIZE, 20*TILE_SIZE);
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
     * Returns the map's item limit.
     */
    public int getItemLimit() {
        return ITEM_LIMIT;
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
                    timeTillNextTick = timeTillNextTick/1000000;// Nanoseconds to milliseconds conversion

                    // Error check
                    if(timeTillNextTick < 0) {
                        timeTillNextTick = 0;
                    }

                    Thread.sleep((long)timeTillNextTick); // Sleep until tick is over

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
        playerActor.update();
        itemDetection.onItem(playerActor);
        zombo.update();
//        mum.update(playerActor);
    }

    /**
     * Java standard module for updating GUI
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Background
        gridSquareFactory.draw(g2);

        // Treasures
        for (int i = 0; i < ITEM_LIMIT; i++) {
            if (treasure[i] != null) {treasure[i].draw(g2);}
        }

        // Enemies
        if (zombo != null) {zombo.draw(g2);}
//        if (mum != null) {mum.draw(g2);}

        // Player
        if (playerActor != null) {playerActor.draw(g2);}

        g2.dispose(); // Saves memory
    }
}
