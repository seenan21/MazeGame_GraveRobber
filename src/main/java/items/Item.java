package items;

import Characters.Direction;
import Map.Grid;

import java.awt.image.BufferedImage;

/**
 * Contains methods that are applicable to all items.
 */
public abstract class Item {

    protected Grid _grid;
    private String _name;
    private int[] _position = new int[2];
    protected BufferedImage _image;



    /**
     * Constructor for the item class.
     *
     * @param name - Name of the item
     * @param positionX - X coordinate of item location
     * @param positionY - Y coordinate of item location
     */
    public Item(Grid grid, String name, int positionX, int positionY){
        this.setPosition(positionX,positionY);
        this.setName(name);
        this._grid = grid;
    }

    /**
     * Finds the sprite image for the item.
     */
    public abstract BufferedImage getImage();

    /**
     * Assigns the item's image.
     */
    public void setImage(BufferedImage image) {
        this._image = image;
    }

    /**
     * Changes the location of an item.
     *
     * @param x - X coordinate of item location
     * @param y - Y coordinate of item location
     */
    public void setPosition(int x, int y) {
        this._position[0] = x;
        this._position[1] = y;
    }

    /**
     * Returns item's location.
     */
    public int[] getPosition() {
        return _position;
    }

    /**
     * Changes the name of an item.
     *
     * @param name - Name of the item
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Returns item's name.
     */
    public String getName() {
        return _name;
    }
}
