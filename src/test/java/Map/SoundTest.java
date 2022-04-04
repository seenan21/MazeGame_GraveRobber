package Map;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class SoundTest{
    Sound sound;

    public SoundTest() {
        sound = new Sound();
    }

    /**
     * Tests that all sound files work
     * @throws InterruptedException
     */
    @Test
    public void testPlay() throws InterruptedException {
        for (int i = 0; i < 14; i++) {
            sound.playSound(i);
            Thread.sleep(1000);
        }

    }

    /**
     * Tests that sounds can loop and stop looping.
     * @throws InterruptedException
     */
    @Test
    public void testStop() throws InterruptedException {
        sound.playMusic(1);
        Thread.sleep(1000);
        sound.stop();

    }
}