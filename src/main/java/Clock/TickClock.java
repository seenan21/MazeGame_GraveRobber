package Clock;

import Constants.Constants;

public class TickClock implements Runnable{

    private boolean _isReady;

    public TickClock() {
    }

    @Override
    public void run() {
        while(true) {
            _isReady = true;
            System.out.println("Ready");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            _isReady = false;
            System.out.println("Pause");

            try {
                Thread.sleep(Constants.TICK - 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public boolean isReady() {
        return _isReady;
    }
}
