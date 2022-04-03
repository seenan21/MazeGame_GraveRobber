package Clock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests if the clock for a trap is properly toggling
 */
public class TrapClockTest {
    TrapClock trapClock;

    public TrapClockTest() {
        trapClock = new TrapClock();
    }

    /**
     * Tests if isHurting is properly toggling.
     */
    @Test
    public void testSetIsHurting() {
        Thread timerThread = new Thread(trapClock);
        timerThread.start();

        assertFalse(trapClock.isHurting());

        trapClock.setIsHurting(true);
        assertTrue(trapClock.isHurting());

        trapClock.setIsHurting(false);
        assertFalse(trapClock.isHurting());

    }
}