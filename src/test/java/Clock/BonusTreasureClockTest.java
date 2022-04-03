package Clock;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class BonusTreasureClockTest {
    BonusTreasureClock bonusTreasureClock;

    public BonusTreasureClockTest() {
        bonusTreasureClock = new BonusTreasureClock();
    }

    /**
     * Testing that the timer is working.
     */
    @Test
    public void testGetTime() throws InterruptedException {
        Thread timerThread = new Thread(bonusTreasureClock);
        timerThread.start();

        // Timer starts at 0
        assertEquals(0.0, bonusTreasureClock.getTime(), 0);

        Thread.sleep(2000);
        assertTrue(bonusTreasureClock.getTime() > 0);
    }

    /**
     * Tests if it is possible for the Bonus Treasure to become visible
     * @throws InterruptedException - thread sleeping
     */
    @Test
    public void testIsVisible() throws InterruptedException {
        Thread timerThread = new Thread(bonusTreasureClock);
        timerThread.start();

        System.out.println("Testing if bonus reward will become visible...");
        System.out.println("This may take up to 30 seconds...");
        for (int i = 0; i < bonusTreasureClock.getMax() + 1; i++) {
            if(bonusTreasureClock.isVisible()) {
                break;
            } else {
                Thread.sleep(1000);
            }

        }
        assertTrue(bonusTreasureClock.isVisible());
    }
}