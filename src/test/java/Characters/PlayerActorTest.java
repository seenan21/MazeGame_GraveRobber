package Characters;

import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Level;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests the playerActor class
 */
public class PlayerActorTest{
    // Dependencies
    PlayerActor playerActor;
    GameState gameState;
    Keyboard keyboard;
    String path = "/level/level_1_foreground.fg";
    Level level;
    int[] position = new int[2];

    public PlayerActorTest() throws IOException {
        gameState = new GameState(GameStateType.PLAY);
        keyboard = new Keyboard(gameState);
        level = new Level(gameState, keyboard, path);
        playerActor = new PlayerActor(keyboard, position, level);
    }

    /**
     * Test the assignment of Character Type
     */
    @Test
    public void testSetCharacterType() {
        playerActor.setCharacterType(CharacterType.PLAYER);
        assertEquals(CharacterType.PLAYER,playerActor.getCharacterType());
    }

    /**
     * Test the assignment of Player Health
     */
    @Test
    public void testSetHealth() {
        playerActor.setHealth(100);
        assertEquals(100,playerActor.getHealth());

        playerActor.setHealth(0);
        assertEquals(0,playerActor.getHealth());
    }

    /**
     * Test the assignment of Player Health out of bounds
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthExecption() {
        playerActor.setHealth(200);
        assertNotEquals(200,playerActor.getHealth());
    }

    /**
     * Test the assignment of Player Speed
     */
    @Test
    public void testSetSpeed() {
        playerActor.setSpeed(2);
        assertEquals(2,playerActor.getSpeed());
    }

    /**
     * Test the assignment of Player Walking
     */
    @Test
    public void testSetWalking() {
        playerActor.setWalking(true);
        assertTrue(playerActor.isWalking());
        playerActor.setWalking(false);
        assertFalse(playerActor.isWalking());
    }

    /**
     * Test the assignment of Player Steps Allowed
     */
    @Test
    public void testSetStepsAllowed() {
        playerActor.setStepsAllowed(1);
        assertEquals(Constants.TILE_SIZE-1,playerActor.getStepsAllowed());
    }

    /**
     * Test the assignment of Player Steps Allowed when -1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStepsAllowedException() {
        playerActor.setStepsAllowed(-1);
    }

    /**
     * Test the assignment of Player Position
     */
    @Test
    public void testSetPosition() {
        playerActor.setPosition(3,5);
        assertEquals(3, playerActor.getPosition()[Constants.X]);
        assertEquals(5, playerActor.getPosition()[Constants.Y]);
    }

    /**
     * Test the assignment of Player Start State
     */
    @Test
    public void testSetStartState() {
        position[Constants.X] = 6;
        position[Constants.Y] = 8;
        PlayerActor playerActorTest = new PlayerActor(keyboard, position, level);
        assertEquals(6, playerActorTest.getPosition()[Constants.X]);
        assertEquals(8, playerActorTest.getPosition()[Constants.Y]);
    }

    /**
     * Test the assignment of Player Direction Facing
     */
    @Test
    public void testSetDirectionFacing() {
        playerActor.setDirectionFacing(Direction.NORTH);
        assertEquals(Direction.NORTH, playerActor.getDirectionFacing());
    }

    /**
     * Test the assignment of Player Next Movement
     */
    @Test
    public void testSetNextMovement() {
        playerActor.setNextMovement(Direction.NORTH);
        assertEquals(Direction.NORTH, playerActor.getNextMovement());
    }

    /**
     * Test the movement of a Player
     */
    @Test
    public void testMoveCharacter() {
        playerActor.setPosition(0,0);
        playerActor.setSpeed(1);

        // Test 1
        assertEquals(0,playerActor.getPosition()[Constants.X]);
        assertEquals(0,playerActor.getPosition()[Constants.Y]);

        // Test 2
        playerActor.moveSouth();

        assertEquals(0,playerActor.getPosition()[Constants.X]);
        assertEquals(1,playerActor.getPosition()[Constants.Y]);

        // Test 3
        playerActor.moveEast();

        assertEquals(1,playerActor.getPosition()[Constants.X]);
        assertEquals(1,playerActor.getPosition()[Constants.Y]);

        // Test 4
        playerActor.moveWest();

        assertEquals(0,playerActor.getPosition()[Constants.X]);
        assertEquals(1,playerActor.getPosition()[Constants.Y]);

        // Test 5
        playerActor.moveNorth();

        assertEquals(0,playerActor.getPosition()[Constants.X]);
        assertEquals(0,playerActor.getPosition()[Constants.Y]);
    }

    /**
     * Test the adding of health to a player
     */
    @Test
    public void testAddToHealth() {
        for (int i = 0; i < 10; i++) {
            playerActor.addToHealth(Constants.HEART);
            assertEquals(i+1, playerActor.regularHeartCollected);
        }
        for (int i = 0; i < 10; i++) {
            playerActor.addToHealth(Constants.HEART_BONUS);
            assertEquals(i+1, playerActor.bigHeartCollected);
        }
    }
}