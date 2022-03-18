package items;

import Characters.Direction;
import Constants.Constants;
import Map.Grid;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains methods that are applicable to all items.
 */
public abstract class Item {

    protected Grid _grid;
    private String _name;
    private int[] _position = new int[2];
    private int _points = 1;
    protected BufferedImage _image;
    private Rectangle _itemBody;
    boolean _available = false;

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
        this._itemBody = new Rectangle(0,0,_grid.getTileSize()/2,_grid.getTileSize()/2);
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
     * Changes the points that the treasure rewards.
     *
     * @param points - Points that the item will reward
     */
    public void setPoints(int points) {
        this._points = points;
    }

    /**
     * Returns the points that the treasure rewards.
     */
    public int getPoints() {
        return _points;
    }

    /**
     * Returns item's name.
     */
    public String getName() {
        return _name;
    }

    public Rectangle getItemBody() {
        return _itemBody;
    }

    public void setAvailable(boolean available) {
        this._available = available;
    }

    public boolean isAvailable() {
        return this._available;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(getImage(),getPosition()[Constants.X],getPosition()[Constants.Y], _grid.getTileSize(), _grid.getTileSize(), null);
    }

    public abstract void update();
}
