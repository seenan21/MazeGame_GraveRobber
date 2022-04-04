package items;

import Clock.BonusTreasureClock;
import Constants.Constants;
import org.junit.Test;

import static org.junit.Assert.*;

public class BonusTreasureTest {
    BonusTreasure bonusTreasure;
    int positionX;
    int positionY;
    public BonusTreasureTest() {
        bonusTreasure = new BonusTreasure(positionX, positionY);
    }



    @Test
    public void getImage() {
        assertEquals(bonusTreasure._image,bonusTreasure.getImage());
    }
}