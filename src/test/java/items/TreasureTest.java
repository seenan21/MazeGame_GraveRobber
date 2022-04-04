package items;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureTest {


    Treasure treasure;
    int positionX;
    int positionY;
    public TreasureTest(){treasure = new Treasure(positionX, positionY);
    }

    /**
     * Test that the point is 1
     */
    @Test
    public void testTrapPoint(){
        assertEquals(1, treasure.getPoints());
    }

    /**
     * Test that getImage() is working
     */
    @Test
    public void testGetImage() {
        treasure.getImage();
    }

    /**
     * Test that the update() is working
     */
    @Test
    public void testUpdate(){
        treasure.update();
        boolean trapAvailable = treasure._available;
        Assert.assertTrue(trapAvailable);
    }

}