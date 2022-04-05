package items;

import Characters.PlayerActor;
import Clock.TrapClock;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Level;
import Map.Sound;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemDetectionTest {

    GameState _gameState;
    Sound sound;
    ArrayList<Item> _itemList;
    PlayerActor playerActor;
    Keyboard keyboard;
    Level level;
    String path = "/level/level_1_foreground.fg";
    int[] position = new int[2];

    public ItemDetectionTest() throws IOException {
        _itemList = new ArrayList<Item>();
        _gameState = new GameState(GameStateType.PLAY);
        sound = new Sound();
        keyboard = new Keyboard(_gameState);
        level = new Level(_gameState, keyboard, path);
        playerActor = new PlayerActor(keyboard, position, level);
        ItemDetection itemDetection = new ItemDetection(_itemList, _gameState);
    }

    /**
     * Test that the player intersects with the trap in position 1, 1
     */
    @Test
    public void testDetectionWithTrap() {
        Trap trap = new Trap(1, 1);
        trap.setAvailable(true);
        playerActor.setPosition(1, 1);

        int[] positionItem = new int[2];
        positionItem = trap.getPosition();

        // Find where the player is on the Grid
        Rectangle itemBodyGrid = trap.getItemBody();
        itemBodyGrid.x = positionItem[Constants.X];
        itemBodyGrid.y = positionItem[Constants.Y];

        int[] positionPlayer = new int[2];
        positionPlayer = playerActor.getPosition();
        Rectangle playerBodyGrid = playerActor.getSpriteBody();
        playerBodyGrid.x = positionPlayer[Constants.X];
        playerBodyGrid.y = positionPlayer[Constants.Y];

        assertTrue(playerBodyGrid.intersects(itemBodyGrid));
    }

    /**
     * Test that the player intersects with the treasure
     */
    @Test
    public void testDetectionWithTreasure() {
        Treasure treasure = new Treasure(1, 1);
        treasure.setAvailable(true);
        playerActor.setPosition(1, 1);

        int[] positionItem = new int[2];
        positionItem = treasure.getPosition();

        // Find where the player is on the Grid
        Rectangle itemBodyGrid = treasure.getItemBody();
        itemBodyGrid.x = positionItem[Constants.X];
        itemBodyGrid.y = positionItem[Constants.Y];

        int[] positionPlayer = new int[2];
        positionPlayer = playerActor.getPosition();
        Rectangle playerBodyGrid = playerActor.getSpriteBody();
        playerBodyGrid.x = positionPlayer[Constants.X];
        playerBodyGrid.y = positionPlayer[Constants.Y];

        assertTrue(playerBodyGrid.intersects(itemBodyGrid));
    }

    /**
     * Test that the player intersects with the BonusTreasure
     */
    @Test
    public void testDetectionWithBonusTreasure() {
        BonusTreasure bonusTreasure = new BonusTreasure(1, 1);
        bonusTreasure.setAvailable(true);
        playerActor.setPosition(1, 1);

        int[] positionItem = new int[2];
        positionItem = bonusTreasure.getPosition();

        // Find where the player is on the Grid
        Rectangle itemBodyGrid = bonusTreasure.getItemBody();
        itemBodyGrid.x = positionItem[Constants.X];
        itemBodyGrid.y = positionItem[Constants.Y];

        int[] positionPlayer = new int[2];
        positionPlayer = playerActor.getPosition();
        Rectangle playerBodyGrid = playerActor.getSpriteBody();
        playerBodyGrid.x = positionPlayer[Constants.X];
        playerBodyGrid.y = positionPlayer[Constants.Y];

        assertTrue(playerBodyGrid.intersects(itemBodyGrid));
    }

    /**
     * Test that the player intersects with the ExitCell
     */
    @Test
    public void testDetectionWithExitCell() {
        ExitCell exitCell = new ExitCell(1, 1);
        exitCell.setAvailable(true);
        playerActor.setPosition(1, 1);

        int[] positionItem = new int[2];
        positionItem = exitCell.getPosition();

        // Find where the player is on the Grid
        Rectangle itemBodyGrid = exitCell.getItemBody();
        itemBodyGrid.x = positionItem[Constants.X];
        itemBodyGrid.y = positionItem[Constants.Y];

        int[] positionPlayer = new int[2];
        positionPlayer = playerActor.getPosition();
        Rectangle playerBodyGrid = playerActor.getSpriteBody();
        playerBodyGrid.x = positionPlayer[Constants.X];
        playerBodyGrid.y = positionPlayer[Constants.Y];

        assertTrue(playerBodyGrid.intersects(itemBodyGrid));
    }
}