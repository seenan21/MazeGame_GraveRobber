package Clock;

/**
 * The trap should only hurt the player periodically.
 */
public class TrapClock implements Runnable{
    private boolean isHurting = false;

    /**
     *
     * @return if the trap is hurting the player
     */
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
