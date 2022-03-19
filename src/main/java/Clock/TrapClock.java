package Clock;

public class TrapClock implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000); // Sleep for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
