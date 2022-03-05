package Map;

/**
 * The grip represents the layout of the map. Maps are quadrilaterals made of tiles.
 */
public class Grid {
    private static final int MAX_HEIGHT = 100;
    private static final int MAX_WIDTH = 100;
    private int height;
    private int width;
    private int[] startTile = new int[2];
    private int[] endTile = new int[2];
    // Rooms list here
    // Create Grid Function here

    /**
     * Constructor for the grid of the map.
     *
     * @param height - Height of the map
     * @param width - Width of the map
     * @param startTile - Starting tile for character on the map
     * @param endTile - Sending tile for character on the map
     */
    public Grid(int height, int width, int[] startTile, int[] endTile) {
        if (width > MAX_WIDTH || width < 0 || height > MAX_HEIGHT || height < 0 )
        {
            System.out.println("ERROR: Max Width must be between 0 and " + MAX_WIDTH);
            System.out.println("ERROR: Max Height must be between 0 and " + MAX_HEIGHT);
            System.exit(-1);
        }
        this.height = height;
        this.width = width;
        if (startTile[0] > MAX_WIDTH || startTile[0] < 0 || startTile[1] > MAX_HEIGHT || startTile[1] < 0 )
        {
            System.out.println("ERROR: Invalid Start Cell");
            System.out.println("ERROR: Max Height must be between 0 and " + MAX_HEIGHT);
            System.out.println("ERROR: Max Width must be between 0 and " + MAX_WIDTH);
            System.exit(-1);
        }
        this.startTile = startTile;
        if (endTile[0] > MAX_WIDTH || endTile[0] < 0 || endTile[1] > MAX_HEIGHT || endTile[1] < 0 )
        {
            System.out.println("ERROR: Invalid End Cell");
            System.out.println("ERROR: Max Height must be between 0 and " + MAX_HEIGHT);
            System.out.println("ERROR: Max Width must be between 0 and " + MAX_WIDTH);
            System.exit(-1);
        }
        this.endTile = endTile;
    }


    private void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the map's width.
     */
    public int getWidth() {
        return width;
    }


    private void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the map's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the player's starting tile.
     */
    public int[] getStartTile() {
        return startTile;
    }
}
