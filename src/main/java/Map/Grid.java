package Map;
import Characters.Character;
import Characters.Mummy;
import Characters.PlayerActor;
import Characters.Zombie;
import Constants.Constants;
import IO.Keyboard;
import items.Item;
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
    private ArrayList<Character> characters = new ArrayList<Character>();
    //Might get rid of this. This list will help us keep track of entities that need to be updated in the game loop.

    Keyboard keyboard = new Keyboard();
    Thread screenThread;
    PlayerActor playerActor = new PlayerActor(this, this.keyboard);
    GridSquareFactory gridSquareFactory = new GridSquareFactory(this);
//    public Item item[] = new Item[5];
//    item[0] = new Treasure();

    Zombie zombo = new Zombie(this, this.keyboard, _screenWidth/2, _screenHeight/2); // Just for testing rn
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
    }

    /**
     * Returns the map's width.
     */
    public int getScreenWidth() {
        return _screenWidth;
    }

    public int getHorizontalTiles(){return HORIZONTAL_TILES;}

    public int getVerticalTiles() { return VERTICAL_TILES;    }

    /**
     * Returns the map's height.
     */
    public int getScreenHeight() {
        return _screenHeight;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    /**
     * Returns the player's starting tile.
     */
    public int[] getStartTile() {
        return _startTile;
    }

    public int[] getEndTile() {
        return _endTile;
    }

    public void setDefault() {
        this._startTile[Constants.X] = 0;
        this._startTile[Constants.Y] = 0;
        this._endTile[Constants.X] = 0;
        this._endTile[Constants.Y] = 0;
    }

    /**
     * INFO HERE
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
        zombo.update();
//        mum.update(playerActor);
    }

    /**
     * Java standard module for updating GUI
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        gridSquareFactory.draw(g2);

        playerActor.draw(g2);
        zombo.draw(g2);
//        mum.draw(g2);

        g2.dispose(); // Saves memory
    }
}
