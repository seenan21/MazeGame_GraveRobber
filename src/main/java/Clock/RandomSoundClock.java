package Clock;

import Map.Sound;

/**
 * The game will play a random sound.
 */
public class RandomSoundClock implements Runnable{
    Sound sound = new Sound();
    private boolean stop = false;

    public void stop() {
        sound.stop();
    }

    @Override
    public void run() {
        int lowerBound = 7;
        int upperBound = 12;
        int random_int = (int)Math.floor(Math.random()*(upperBound-lowerBound+1)+lowerBound);

        while(!stop) {
            try {
                Thread.sleep(30000); // Sleep for 15 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sound.playSound(random_int);
        }
    }
}
