package items;

import Clock.BonusRewardClock;
import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BonusTreasure extends Item{

    private BonusRewardClock clock;
    private Thread clockThread;

    /**
     * Constructor for the treasure class.
     *
     * @param positionX - X coordinate of item location
     * @param positionY - Y coordinate of item location
     */
    public BonusTreasure(Grid grid, int positionX, int positionY) {
        super(grid,"Reward", positionX, positionY);
        setPoints(500);
        getImage();

        clock = new BonusRewardClock();
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
