package Clock;

import Characters.Skeleton;
import Characters.PlayerActor;
import Characters.Zombie;
import Constants.Constants;

import java.util.ArrayList;

/**
 * Every Character should move at the same time according to this tick clock. To adjust settings for character movement
 * time, please see the Time section in Constants.Constants.java
 */
public class TickClock implements Runnable{

    private PlayerActor _playerActor;
    private ArrayList<Zombie> _zombieList;
    private Skeleton _boss;

    public TickClock(PlayerActor playerActor, ArrayList<Zombie> zombieList, Skeleton boss) {
        this._playerActor = playerActor;
        this._zombieList = zombieList;
        this._boss = boss;
    }

    /**
     * Calls a thread for each character which instructs them to move every TICK.
     */
    @Override
    public void run() {
        while(true) {

            try {
                Thread.sleep(Constants.TICK);

                // Ask player to move
                if(_playerActor.isWalking() == false) {
                    Thread tempThread = new Thread(_playerActor);
                    tempThread.start(); // Calls this.run()
                }

                // Ask zombies to move
                for (int i = 0; i < _zombieList.size(); i++) {
                    Thread tempThread = new Thread(_zombieList.get(i));
                    tempThread.start(); // Calls this.run()
                }

                {
                    _boss.safeMoveCharacter(_boss.followPlayer(_playerActor));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
