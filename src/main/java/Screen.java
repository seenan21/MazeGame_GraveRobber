import javax.swing.*;
import java.awt.*;

/**
 * The grid represents the layout of the map. Maps are quadrilaterals made of tiles.
 */
public class Screen extends JPanel implements Runnable{

    private static final int TILE_SIZE = 16; // Size of an individual tile in pixels
    private static final int HORIZONTAL_TILES = 48; // Total tiles horizontally on map
    private static final int VERTICAL_TILES = 48; // Total tiles vertically on map
    private final double FRAMES_PER_SECOND = 60;
    private final int _screenWidth = TILE_SIZE * HORIZONTAL_TILES;
    private final int _screenHeight = TILE_SIZE * VERTICAL_TILES;
    private int[] _startTile = new int[2]; // Starting tile for player when the game begins
    private int[] _endTile = new int[2]; // Ending tile for player when all treasures have been collected

    Keyboard keyboard = new Keyboard();
    Thread screenThread;

    // TEMP PLAYER VARIABLES FOR TESTING
    int x = 100;
    int y = 100;
    int speed = 2;

    /**
     * Creates the game screen and sets up a keyboard listener.
     */
    public Screen() {
        this.setPreferredSize(new Dimension(_screenWidth, _screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    /**
     * Returns the map's width.
     */
    public int getScreenWidth() {
        return _screenWidth;
    }

    /**
     * Returns the map's height.
     */
    public int getScreenHeight() {
        return _screenHeight;
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
        if(keyboard.upKeyPressed) {
            y -= speed;
        }
        else if(keyboard.downKeyPressed) {
            y += speed;
        }
        else if(keyboard.leftKeyPressed) {
            x -= speed;
        }
        else if(keyboard.rightKeyPressed) {
            x += speed;
        }
    }

    /**
     * Java standard module for updating GUI
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLUE);
        g2.fillRect(this.x,this.y, TILE_SIZE, TILE_SIZE);
        g2.dispose(); // Saves memory
    }
}
