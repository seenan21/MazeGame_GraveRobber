package items;

import Map.Grid;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * When a player reaches a reward on the map, they will receive extra points when they walk over the treasure.
 * After walking over the treasure, it will disappear on the map.
 */
public class Treasure extends Item{
    /**
     * Constructor for the treasure class.
     *
     * @param positionX - X coordinate of item location
     * @param positionY - Y coordinate of item location
     */
    public Treasure(int positionX, int positionY) {
        super("Reward", positionX, positionY);
        setPoints(1);
        getImage();
    }

    @Override
    public BufferedImage getImage() {
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/item/treasure_128.png")));
            return this._image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update() {
        setAvailable(true);
    }
}
