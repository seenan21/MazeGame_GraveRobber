import javax.swing.*;
import java.awt.*;

/**
 * INFO HERE
 */
public class Screen extends JPanel implements Runnable{

    final int tileSize = 16;
    final int HORIZONTAL_TILES = 48;
    final int VERTICAL_TILES = 48;
    final double FRAMES_PER_SECOND = 60;
    final int screenWidth = tileSize * HORIZONTAL_TILES;
    final int screenHeight = tileSize * VERTICAL_TILES;

    Keyboard keyboard = new Keyboard();
    Thread screenThread;

    int x = 100;
    int y = 100;
    int speed = 2;

    /**
     * INFO HERE
     */
    public Screen() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    /**
     * INFO HERE
     */
    public void startThread() {
        screenThread = new Thread(this);
        screenThread.start(); // Calls this.run()
    }

    /**
     * INFO HERE
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
     * INFO HERE
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
        g2.fillRect(this.x,this.y,tileSize,tileSize);
        g2.dispose(); // Saves memory
    }
}
