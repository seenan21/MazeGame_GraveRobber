package Constants;

/**
 * Contains all constants for the project.
 */
public class Constants {
    // Position
    public final static int X = 0;
    public final static int Y = 1;

    // Screen Specifications
    public final static int TILE_SIZE = 32;
    public final static int HORIZONTAL_TILES = 24;
    public final static int VERTICAL_TILES = 24;
    public final static int HORIZONTAL_TILES_BACKGROUND = 6;
    public final static int VERTICAL_TILES_BACKGROUND = 6;
    public final static int TILE_SIZE_BACKGROUND = 128;
    public final static int FRAMES_PER_SECOND = 60;
    public final static int SCREEN_WIDTH = Constants.TILE_SIZE * Constants.HORIZONTAL_TILES;
    public final static int SCREEN_HEIGHT = Constants.TILE_SIZE * Constants.VERTICAL_TILES;

    // Map Squares
    public final static int GRASS = 0;

    // Item points value
    public final static int HEART_POINTS = 1;
    public final static int HEART_BONUS_POINTS = 3;
    public final static int EXIT_CELL = 3;

    // Total regular reward number
    public final static int REGULAR_REWARD = 20;

    // Time
    // Adjust these speeds to manipulate tick movement
    public final static int TICK = 384; // milliseconds
    public final static int WALK_TIME = TICK/32; // milliseconds

    // Asset Specifier //

    // Characters
    public final static char ZOMBIE_FOREGROUND = 'Z';

    // Obstacles
    public final static char GRAVE_1 = '0';
    public final static char GRAVE_2 = '1';
    public final static char GRAVE_3_TOP = '2';
    public final static char GRAVE_4_BOTTOM = '3';
    public final static char WALL_HORIZONTAL_1 = '4';
    public final static char WALL_HORIZONTAL_2 = '5';
    public final static char WALL_HORIZONTAL_3 = '6';
    public final static char WALL_HORIZONTAL_4 = '7';
    public final static char WALL_VERTICAL_1 = '8';
    public final static char WALL_VERTICAL_2 = '9';
    public final static char WALL_VERTICAL_3 = '-';

    // Items
    public final static char HEART_FOREGROUND = 'H';
    public final static char BONUS_FOREGROUND = 'B';

    // Traps
    public final static char TRAP_FOREGROUND = 'T';

    // Map Tiles
    public final static char START_FOREGROUND = 'S';
    public final static char EXIT_FOREGROUND = 'X';

    // Mummy
    public final static char MUMMY = 'M';


}
