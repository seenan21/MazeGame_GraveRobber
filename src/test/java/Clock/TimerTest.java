package Clock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Timer Class.
 */
public class TimerTest {
    Timer timer;

    public TimerTest() {
        timer = new Timer();
    }

    /**
     * Tests if the timer properly starts and stops.
     */
    @Test
    public void testSetStartTimer() throws InterruptedException {
        Thread timerThread = new Thread(timer);
        timerThread.start();

        // Timer starts at 0
        assertEquals(0.0, timer.getElapsedTime(), 0);

        // Timer should not be counting yet
        Thread.sleep(1000);
        assertEquals(0.0, timer.getElapsedTime(), 0);

        // Timer set to start counting
        timer.setStartTimer(true);
        Thread.sleep(1000);
        assertTrue(timer.getElapsedTime() > 0.0);

        // Timer is asked to stop
        Thread.sleep(1000);
        timer.setStartTimer(false);
        double stoppedTime = timer.getElapsedTime();
        Thread.sleep(1000);
        assertEquals(stoppedTime, timer.getElapsedTime(), 0);
    }
}