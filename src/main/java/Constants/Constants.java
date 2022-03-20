package Constants;

public class Constants {
    // Position
    public final static int X = 0;
    public final static int Y = 1;

    // Map Squares
    public final static int GRASS = 0;

    // Total regular reward number
    public final static int regReward = 20;

    // Time
    // Adjust these speeds to manipulate tick movement
    public final static int TICK = 256; // milliseconds
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
