package Map;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ObstacleTest {
    Obstacle obstacle;

    public ObstacleTest() {
    }

    /**
     * Tests that the obstacles' position is properly set.
     */
    @Test
    public void testGetPosition() {
        obstacle = new Obstacle(5,6);
        assertEquals(5,obstacle.getPosX());
        assertEquals(6,obstacle.getPosY());
    }
}