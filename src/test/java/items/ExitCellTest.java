package items;

import org.junit.Assert;
import org.junit.Test;
import Constants.Constants;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.awt.image.BufferedImage;

public class ExitCellTest {
    ExitCell exitCell;
    int positionX, positionY;
    public ExitCellTest(){
        exitCell = new ExitCell(positionX, positionY);
    }

    /**
     * Test that the point is Constants.EXIT_CELL
     */
    @Test
    public void testExitCellPoint(){
        assertEquals(Constants.EXIT_CELL, exitCell.getPoints());
    }

    /**
     * Test that getImage() is working
     */
    @Test
    public void getImageTest() {
        BufferedImage t = exitCell.getImage();
        assertNull(t);
    }

    /**
     * Test that the update() is working
     */
    @Test
    public void testUpdate(){
        exitCell.update();
        boolean trapAvailable = exitCell._available;
        Assert.assertTrue(trapAvailable);
    }


}