package Clock;

import Map.Sound;

/**
 * The game will play a random sound.
 */
public class RandomSoundClock implements Runnable{
    Sound sound = new Sound();

    @Override
    public void run() {
        int lowerbound = 7;
        int upperbound = 12;
        int random_int = (int)Math.floor(Math.random()*(upperbound-lowerbound+1)+lowerbound);

        while(true) {
            try {
                Thread.sleep(30000); // Sleep for 15 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sound.playSound(random_int);
        }
    }
}
