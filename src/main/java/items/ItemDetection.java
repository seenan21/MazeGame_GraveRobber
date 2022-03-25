package items;

import Characters.PlayerActor;
import Clock.TrapClock;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import Map.Level;
import Map.Sound;

import java.awt.*;
import java.util.ArrayList;

/**
 * When the player steps on an item, it should disappear and award the player points based on the item.
 */
public class ItemDetection {

    Level _level;
    private TrapClock clock;
    private Thread clockThread;
    private GameState _gameState;
    private Sound sound = new Sound();

    /**
     * Constructor for ItemDetection
     */
    public ItemDetection(Level level, GameState gameState) {

        this._level = level;
        this._gameState = gameState;
        clock = new TrapClock();
        clockThread = new Thread(clock);
        clockThread.start();
    }

    /**
     * If the player is on top of an item, the item will award the player points, and the item will be removed.
     *
     * @param playerActor - Player's character.
     */
    public void onItem(PlayerActor playerActor) {
        // Cycle though Items on Grid

        ArrayList<Item>  itemList = _level.getItemList();

        int i=0;
        while (i < itemList.size()) {
            if(itemList.get(i) != null) {

                // Get current position of the Item
                int[] positionItem = new int[2];
                positionItem = itemList.get(i).getPosition();

                // Find where the player is on the Grid
                Rectangle itemBodyGrid = (itemList.get(i)).getItemBody();
                itemBodyGrid.x = positionItem[Constants.X];
                itemBodyGrid.y = positionItem[Constants.Y];

                // Get current position of the Player
                int[] positionPlayer = new int[2];
                positionPlayer = playerActor.getPosition();

                // Find where the player is on the Grid
                Rectangle playerBodyGrid = playerActor.getSpriteBody();
                playerBodyGrid.x = positionPlayer[Constants.X];
                playerBodyGrid.y = positionPlayer[Constants.Y];

                // Remove item from map and award points to player
                if(playerBodyGrid.intersects(itemBodyGrid) && itemList.get(i).isAvailable()) {
                    if ((itemList.get(i)).getPoints() == -1) {
                        if (!clock.isHurting()) {
                            if (_gameState.getGameState() == GameStateType.PLAY) {
                                sound.playSound(13);
                                System.out.println("Trap triggered.");
                                playerActor.addToHealth((itemList.get(i)).getPoints());
                                clock.setIsHurting(true);
                            }
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 1) {
                        if (_gameState.getGameState() == GameStateType.PLAY) {
                            sound.playSound(1);
                            System.out.println("Heart collected.");
                            playerActor.addToHealth((itemList.get(i)).getPoints());
                            itemList.set(i, null);
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 3) {
                        if (_gameState.getGameState() == GameStateType.PLAY) {
                            sound.playSound(2);
                            System.out.println("BIG Heart collected.");
                            playerActor.addToHealth((itemList.get(i)).getPoints());
                            itemList.set(i, null);
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 999) {
                        if (playerActor.regularHeartCollected >= Constants.regReward){
                            _gameState.setGameState(GameStateType.END);
                            _gameState.setWin(true);
                        }
                        else {
                            System.out.println("You need to collect all the regular reward to win.");
                        }
                    }

                }
            }
            i++;
        }

    }
}
