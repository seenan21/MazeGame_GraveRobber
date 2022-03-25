package Map;
import Clock.RandomSoundClock;
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

    private Keyboard keyboard = new Keyboard();
    private Thread screenThread;
    private String path = "/level/level_1_foreground.fg";
    private Level level = new Level(this, keyboard, path);
    private UI ui = new UI(this, keyboard, level.getHero());
    Sound sound = new Sound();
    private RandomSoundClock soundClock;
    private Thread soundThread;
    private int i = 0;

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
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        soundClock = new RandomSoundClock();
        soundThread = new Thread(soundClock);
        soundThread.start();
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
            if (i == 0) {
                playSound(5);
                i++;
            }
            ui.draw(g2);
            g2.dispose();
        }
    }

    /**
     * Plays music
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    /**
     * Stops music
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Plays a sound
     */
    public void playSound(int i) {
        sound.setFile(i);
        sound.play();
    }
}
