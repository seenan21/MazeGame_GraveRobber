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

    @Test
    /**
     * Test the assignment of Character Type
     */
    public void testSetCharacterType() throws IOException {
        playerActor.setCharacterType(CharacterType.PLAYER);
        assertEquals(CharacterType.PLAYER,playerActor.getCharacterType());
    }

    @Test
    /**
     * Test the assignment of Player Health
     */
    public void testSetHealth() throws IOException {
        playerActor.setHealth(100);
        assertEquals(100,playerActor.getHealth());

        playerActor.setHealth(0);
        assertEquals(0,playerActor.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    /**
     * Test the assignment of Player Health out of bounds
     */
    public void testSetHealthExecption() throws IOException {
        playerActor.setHealth(200);
        playerActor.getHealth();
    }

    @Test
    /**
     * Test the assignment of Player Speed
     */
    public void testSetSpeed() {
        playerActor.setSpeed(2);
        assertEquals(2,playerActor.getSpeed());
    }

    @Test
    /**
     * Test the assignment of Player Walking
     */
    public void testSetWalking() {
        playerActor.setWalking(true);
        assertEquals(true,playerActor.isWalking());
        playerActor.setWalking(false);
        assertEquals(false,playerActor.isWalking());
    }

    @Test
    /**
     * Test the assignment of Player Steps Allowed
     */
    public void testSetStepsAllowed() {
        playerActor.setStepsAllowed(1);
        assertEquals(1 * Constants.TILE_SIZE-1,playerActor.getStepsAllowed());
    }

    @Test(expected = IllegalArgumentException.class)
    /**
     * Test the assignment of Player Steps Allowed when -1
     */
    public void testSetStepsAllowedException() {
        playerActor.setStepsAllowed(-1);
    }

    @Test
    /**
     * Test the assignment of Player Position
     */
    public void testSetPosition() {
        playerActor.setPosition(3,5);
        assertEquals(3, playerActor.getPosition()[Constants.X]);
        assertEquals(5, playerActor.getPosition()[Constants.Y]);
    }

    @Test
    /**
     * Test the assignment of Player Start State
     */
    public void testSetStartState() {
        position[Constants.X] = 6;
        position[Constants.Y] = 8;
        PlayerActor playerActorTest = new PlayerActor(keyboard, position, level);
        assertEquals(6, playerActorTest.getPosition()[Constants.X]);
        assertEquals(8, playerActorTest.getPosition()[Constants.Y]);
    }

    @Test
    /**
     * Test the assignment of Player Direction Facing
     */
    public void testSetDirectionFacing() {
        playerActor.setDirectionFacing(Direction.NORTH);
        assertEquals(Direction.NORTH, playerActor.getDirectionFacing());
    }

    @Test
    /**
     * Test the assignment of Player Next Movement
     */
    public void testSetNextMovement() {
        playerActor.setNextMovement(Direction.NORTH);
        assertEquals(Direction.NORTH, playerActor.getNextMovement());
    }

    @Test
    /**
     * Test the movement of a Player
     */
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

    @Test
    /**
     * Test the adding of health to a player
     */
    public void testAddToHealth() {
        for (int i = 0; i < 10; i++) {
            playerActor.addToHealth(Constants.HEART_POINTS);
            assertEquals(i+1, playerActor.regularHeartCollected);
        }
        for (int i = 0; i < 10; i++) {
            playerActor.addToHealth(Constants.HEART_BONUS_POINTS);
            assertEquals(i+1, playerActor.bigHeartCollected);
        }
    }
}