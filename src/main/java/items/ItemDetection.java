package items;

import Characters.PlayerActor;
import Clock.TrapClock;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import Map.Sound;

import java.awt.*;
import java.util.ArrayList;

/**
 * When the player steps on an item, it should disappear and award the player points based on the item.
 */
public class ItemDetection {

    private TrapClock clock;
    private Thread clockThread;
    private GameState _gameState;
    private Sound sound = new Sound();
    private ArrayList<Item> _itemList;

    /**
     * Constructor for ItemDetection
     */
    public ItemDetection(ArrayList<Item>  itemList, GameState gameState) {
        _itemList = itemList;
        this._gameState = gameState;
        clock = new TrapClock();
        clockThread = new Thread(clock);
        clockThread.start();
    }

    /**
     * Checks which item a player is standing on and adds points to them.
     *
     * @param index - The index of the item in the itemlist
     * @param playerActor - Main Player
     * @param gameState - State the game is currently in
     */
    public void playerItemInteraction(int index, PlayerActor playerActor, GameState gameState) {

        // Game State must be in play state
        if (gameState.getGameState() != GameStateType.PLAY) {
            return;
        }

        if ((_itemList.get(index)).getPoints() == Constants.TRAP && !clock.isHurting()) {
                sound.playSound(13);
                clock.setIsHurting(true);
        }
        else if ((_itemList.get(index)).getPoints() == Constants.HEART) {
                sound.playSound(1);

        }
        else if ((_itemList.get(index)).getPoints() == Constants.HEART_BONUS) {
                sound.playSound(2);
        }
        else if ((_itemList.get(index)).getPoints() == Constants.EXIT_CELL) {
            if (playerActor.regularHeartCollected >= Constants.REGULAR_REWARD){
                _gameState.setGameState(GameStateType.END);
                _gameState.setWin(true);
                return;
            }
        } else {
            // No Item Detected
            return;
        }

        playerActor.addToHealth((_itemList.get(index)).getPoints());

        if ((_itemList.get(index)).getPoints() != Constants.TRAP) {
            _itemList.set(index, null);
        }
    }

    /**
     * If the player is on top of an item, the item will award the player points, and the item will be removed.
     *
     * @param playerActor - Player's character.
     */
    public void onItem(PlayerActor playerActor) {
        // Cycle though Items on Grid

        int i=0;
        while (i < _itemList.size()) {
            if(_itemList.get(i) != null) {

                // Get current position of the Item
                int[] positionItem = new int[2];
                positionItem = _itemList.get(i).getPosition();

                // Find where the player is on the Grid
                Rectangle itemBodyGrid = (_itemList.get(i)).getItemBody();
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
                if(playerBodyGrid.intersects(itemBodyGrid) && _itemList.get(i).isAvailable()) {
                    playerItemInteraction(i, playerActor, _gameState);
                }
            }
            i++;
        }
    }
}
