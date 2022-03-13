package Clock;

import Characters.PlayerActor;
import Constants.Constants;

public class TickClock implements Runnable{

    private boolean _isReady;
    private PlayerActor playerActor;

    public TickClock(PlayerActor playerActor) {
        this.playerActor = playerActor;
    }

    @Override
    public void run() {
        while(true) {

            try {
                Thread.sleep(Constants.TICK);
                if(playerActor.isWalking() == false) {
                    playerActor.moveCharacter(playerActor.getNextMovement());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public boolean isReady() {
        return _isReady;
    }
}
