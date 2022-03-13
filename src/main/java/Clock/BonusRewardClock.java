package Clock;

public class BonusRewardClock implements Runnable{
    private int timer;
    int random_int = 0;
    int min = 20;
    int max = 30;
    boolean isVisible = false;

    public BonusRewardClock() {
    }

    public int getTime() {
        return timer;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void run() {
        while(true) {

            if (timer == 0) {
                random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
            if(random_int == timer) {
                timer = 0;
                isVisible = !isVisible;
                if(isVisible) {
                    System.out.println("Bonus reward is visible.");
                } else {
                    System.out.println("Bonus reward disappears.");
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer++;
            }
        }
    }

}
