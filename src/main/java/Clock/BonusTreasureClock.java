package Clock;

/**
 * This clock is to be used with BonusTreasure. It allows the treasure to randomly disappear.
 */
public class BonusTreasureClock implements Runnable{
    private int timer;
    int random_int = 0;
    int min = 20;
    int max = 30;
    boolean isVisible = false;


    public BonusTreasureClock() {
    }

    /**
     *
     * @return current time on timer.
     */
    public int getTime() {
        return timer;
    }

    /**
     *
     * @return is the reward should be visable.
     */
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    /**
     * Loops turning the reward visible and invisible randomly
     */
    public void run() {
        while(true) {

            // Set a random number
            if (timer == 0) {
                random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }

            // Toggle isVisible
            if(random_int == timer) {
                timer = 0;
                isVisible = !isVisible;
                if(isVisible) {
                    System.out.println("Bonus reward is visible.");
                } else {
                    System.out.println("Bonus reward disappears.");
                }
            }
            else {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer++;
            }
        }
    }

}
