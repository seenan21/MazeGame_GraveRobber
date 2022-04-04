package items;

import Clock.BonusTreasureClock;
import Constants.Constants;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusTreasureTest {
    BonusTreasure bonusTreasure;
    int positionX;
    int positionY;
    public BonusTreasureTest() {
        bonusTreasure = new BonusTreasure(positionX, positionY);
    }

    /**
     * Test that the point is 1
     */
    @Test
    public void testBonusTreasurePoint(){
        assertEquals(Constants.HEART_BONUS_POINTS, bonusTreasure.getPoints());
    }

    /**
     * Test that getImage() is working
     */
    @Test
    public void getImageTest() {
        bonusTreasure.getImage();
    }

    /**
     * Test that the update() is working
     */
    @Test
    public void testUpdate() throws InterruptedException {
        bonusTreasure.update();
        boolean bonusTreasureAvailable = bonusTreasure._available;

        assertFalse(bonusTreasureAvailable);
    }

}