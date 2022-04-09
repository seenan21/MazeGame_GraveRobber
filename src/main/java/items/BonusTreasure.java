package items;

import Clock.BonusTreasureClock;
import Constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Bonus Treasure will randomly appear and disappear on the map.
 * Players will only be able to collect the reward when the item is visible.
 */
public class BonusTreasure extends Item{

    private BonusTreasureClock clock;
    private Thread clockThread;

    /**
     * Constructor for the treasure class.
     *
     * @param positionX - X coordinate of item location
     * @param positionY - Y coordinate of item location
     */
    public BonusTreasure(int positionX, int positionY) {
        super("Reward", positionX, positionY);
        setPoints(Constants.HEART_BONUS);
        getImage();

        clock = new BonusTreasureClock();
        clockThread = new Thread(clock);
        clockThread.start(); // Calls this.run()
    }

    @Override
    public BufferedImage getImage() {
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/item/treasure_bonus_128.png")));
            return this._image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (clock.isVisible()) {
            super.draw(g2);
        }
    }

    @Override
    public void update() {
        if(clock.isVisible()) {
            setAvailable(true);
        } else {
            setAvailable(false);
        }
    }
}
