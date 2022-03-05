package items;

/**
 * Contains methods that are applicable to all items.
 */
public abstract class Item {
    private String name;
    private int[] location = new int[2];

    /**
     * Constructor for the item class.
     *
     * @param name - Name of the item
     * @param locationX - X coordinate of item location
     * @param locationY - Y coordinate of item location
     */
    public Item(String name, int locationX, int locationY){
        this.setLocation(locationX,locationY);
        this.setName(name);
    }

    /**
     * Changes the location of an item.
     *
     * @param x - X coordinate of item location
     * @param y - Y coordinate of item location
     */
    public void setLocation(int x, int y) {
        this.location[0] = x;
        this.location[1] = y;
    }

    /**
     * Returns item's location.
     */
    public int[] getLocation() {
        return location;
    }

    /**
     * Changes the name of an item.
     *
     * @param name - Name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns item's name.
     */
    public String getName() {
        return name;
    }
}
