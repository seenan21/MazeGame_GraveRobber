package Map;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class GridTest{

    Grid grid;

    public GridTest() throws IOException {
        grid = new Grid();
    }

    /**
     * Tests that the created level is not null.
     */
    @Test
    public void testGetLevel() {
        assertNotNull(grid.getLevel());
    }
}