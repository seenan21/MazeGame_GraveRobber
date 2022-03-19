package Clock;

/**
 * The timer keeps incrementing time every 10 milliseconds.
 */
public class Timer implements Runnable{

    private double elapsedTime = 0;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedTime = elapsedTime + 0.01;
        }
    }

    /**
     *
     * @return time since the game began in seconds
     */
    public double getElapsedTime() {
        return elapsedTime;
    }
}
