package items;

import Constants.Constants;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TrapTest {
    Trap trap;
    int positionX, positionY;

    public TrapTest(){
        trap = new Trap(positionX, positionY);
    }

    /**
     * Test that the point is -1
     */
    @Test
    public void testTrapPoint(){
        assertEquals(-1, trap.getPoints());
    }

    /**
     * Test that the trap image is accessible by simply getImage()
     */
    @Test
    public void testGetImage() throws IOException {
        trap.getImage();
    }

    /**
     * Test that the update() is working
     */
    @Test
    public void testUpdate(){
        trap.update();
        boolean trapAvailable = trap._available;
        Assert.assertTrue(trapAvailable);
    }
}