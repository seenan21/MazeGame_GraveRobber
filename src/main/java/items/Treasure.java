package items;

/**
 * When a player reaches a reward on the map, they will receive extra points when they walk over the treasure.
 * After walking over the treasure, it will disappear on the map.
 */
public class Treasure extends Item{
    private int points;

    /**
     * Constructor for the treasure class.
     *
     * @param name - Name of the item
     * @param locationX - X coordinate of item location
     * @param locationY - Y coordinate of item location
     * @param points - Points that the item will reward
     */
    public Treasure(String name, int locationX, int locationY, int points) {
        super(name, locationX, locationY);
        setPoints(points);
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
