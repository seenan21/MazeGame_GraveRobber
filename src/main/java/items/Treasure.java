package items;

import Characters.Direction;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * When a player reaches a reward on the map, they will receive extra points when they walk over the treasure.
 * After walking over the treasure, it will disappear on the map.
 */
public class Treasure extends Item{
    private int points;

    /**
     * Constructor for the treasure class.
     *
     * @param positionX - X coordinate of item location
     * @param positionY - Y coordinate of item location
     */
    public Treasure(int positionX, int positionY) {
        super("Reward", positionX, positionY);
        setPoints(100);
        getImage();
    }

    @Override
    public void getImage() {
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/item/treasure_128.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the points that the treasure rewards.
     *
     * @param points - Points that the item will reward
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns the points that the treasure rewards.
     */
    public int getPoints() {
        return points;
    }
}
