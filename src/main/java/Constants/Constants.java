package Constants;

import java.awt.*;

public class Constants {
    // Position
    public final static int X = 0;
    public final static int Y = 1;

    // Map Squares
    public final static int GRASS = 0;

    // Time
    // Adjust these speeds to manipulate tick movement
    public final static int TICK = 256; // milliseconds
    public final static int WALK_TIME = TICK/32; // milliseconds

    public final static int TIME_LIMIT = 10; // seconds
}
