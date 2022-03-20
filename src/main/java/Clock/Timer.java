package Clock;

/**
 * The timer keeps incrementing time every 10 milliseconds.
 */
public class Timer implements Runnable{

    private double elapsedTime = 0;
    private boolean startTimer = false;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(startTimer) {
                elapsedTime = elapsedTime + 0.01;
            }

        }
    }

    /**
     *
     * @param startTimer - starts the timer count.
     */
    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    /**
     *
     * @return time since the game began in seconds
     */
    public double getElapsedTime() {
        return elapsedTime;
    }
}
