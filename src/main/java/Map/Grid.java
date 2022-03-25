package Map;
import Clock.RandomSoundClock;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
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

    private GameState gameState = new GameState(GameStateType.TITLE);
    private Keyboard keyboard = new Keyboard(gameState);;
    private Thread screenThread;
    private String path = "/level/level_1_foreground.fg";
    private Level level;
    private UI ui;
    private Sound sound = new Sound();
    private RandomSoundClock soundClock;
    private Thread soundThread;
    private int i = 0;

    CreateBackground tilem = new CreateBackground(this);

    /**
     * Creates the game screen and sets up a keyboard listener.
     */
    public Grid() throws IOException {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        soundClock = new RandomSoundClock();
        soundThread = new Thread(soundClock);
        soundThread.start();
        level = new Level(gameState, keyboard, path);
        ui = new UI(gameState, level.getHero());
    }

    /**
     * Starts the thread for the map.
     */
    public void startThread() {
        screenThread = new Thread(this);
        screenThread.start(); // Calls this.run()
        sound.playMusic(0);
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

            double tick = 1000000000/Constants.FRAMES_PER_SECOND;
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

        if (gameState.getGameState() == GameStateType.TITLE) {
            // Title Page
            ui.draw(g2);
            g2.dispose();

        }
        if (gameState.getGameState() == GameStateType.PLAY) {

            // Background
            tilem.draw(g2);

            // Walls and characters
            level.draw(g2);

            // UI: health, time
            ui.draw(g2);
            g2.dispose(); // Saves memory
        }
        if (gameState.getGameState() == GameStateType.END) {
            gameState.setGameState(GameStateType.END);
            if (i == 0) {
                sound.playSound(5);
                i++;
            }
            ui.draw(g2);
            g2.dispose();
        }
    }
}
