package items;

import Characters.PlayerActor;
import Clock.TrapClock;
import Constants.Constants;
import Map.Grid;
import Map.Level;
import Map.Sound;

import java.awt.*;
import java.util.ArrayList;

/**
 * When the player steps on an item, it should disappear and award the player points based on the item.
 */
public class ItemDetection {

    Grid _grid;
    Level _level;
    private TrapClock clock;
    private Thread clockThread;


    /**
     * Constructor for ItemDetection
     * @param grid
     */
    public ItemDetection(Level level, Grid grid) {
        this._grid = grid;
        this._level = level;

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
                            if (_grid.gameState == _grid.playState) {
                                _grid.playSound(13);
                                System.out.println("Trap triggered.");
                                playerActor.addToHealth((itemList.get(i)).getPoints());
                                clock.setIsHurting(true);
                            }
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 1) {
                        if (_grid.gameState == _grid.playState) {
                            _grid.playSound(1);
                            System.out.println("Heart collected.");
                            playerActor.addToHealth((itemList.get(i)).getPoints());
                            itemList.set(i, null);
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 3) {
                        if (_grid.gameState == _grid.playState) {
                            _grid.playSound(2);
                            System.out.println("BIG Heart collected.");
                            playerActor.addToHealth((itemList.get(i)).getPoints());
                            itemList.set(i, null);
                        }
                    }
                    else if ((itemList.get(i)).getPoints() == 999) {
                        if (playerActor.regularHeartCollected >= Constants.regReward){
                            _grid.gameState = 2;
                            _grid.win = true;
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
