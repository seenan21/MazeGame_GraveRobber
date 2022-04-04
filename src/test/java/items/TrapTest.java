package items;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TrapTest {
    Trap trap;

    public TrapTest(){
        trap = new Trap(0, 0);
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

//    @Test
//    public void testUpdate(){
//        trap.update();
//        assertTrue(trap._available);
//    }
}