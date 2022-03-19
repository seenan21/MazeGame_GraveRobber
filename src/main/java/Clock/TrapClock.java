package Clock;

public class TrapClock implements Runnable{
    private boolean isHurting = false;

    public boolean isHurting() { return isHurting; }

    public void setIsHurting(boolean setter) { this.isHurting = setter; }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isHurting = false;
        }
    }
}
