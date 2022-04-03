package Characters;

import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Level;
import junit.framework.TestCase;

import java.io.IOException;

public class SkeletonTest extends TestCase {
    // Dependencies
    PlayerActor playerActor;
    GameState gameState;
    int[] position = new int[2];
    Keyboard keyboard;
    String path = "/level/level_1_foreground.fg";
    Level level;
    // Creating Skeleton
    Skeleton skeleton;

    public SkeletonTest() throws IOException {
        gameState = new GameState(GameStateType.PLAY);
        keyboard = new Keyboard(gameState);
        level = new Level(gameState, keyboard, path);
        playerActor = new PlayerActor(keyboard, position, level);

    }

    /**
     * Test the heroKill function
     */
    @Test
    public void testheroKill() {
        skeleton = new Skeleton(gameState,0,0,level);
        assertTrue(zombie.heroKill(playerActor));

    }

    /**
     * Test the heroKill function when it shouldn't work
     */
    @Test
    public void testNOTheroKill() {
        skeleton = new Skeleton(gameState,0,0,level);
        playerActor.setPosition(3*Constants.TILE_SIZE,3*Constants.TILE_SIZE);
        assertTrue(!zombie.heroKill(playerActor));

    }
    /**
     * Checks if Follow player returns right direction
     */
    @Test
    public void Followplayer() {
        skeleton = new Skeleton(gameState,1*Constants.TILE_SIZE, 1*Constants.TILE_SIZE,level);
        playerActor.setPosition(1*Constants.TILE_SIZE,2*Constants.TILE_SIZE);
        assertTrue(skeleton.followPlayer(playerActor) == Direction.EAST );

        playerActor.setPosition(1*Constants.TILE_SIZE,0*Constants.TILE_SIZE);
        assertTrue(skeleton.followPlayer(playerActor) == Direction.NORTH );

        playerActor.setPosition(2*Constants.TILE_SIZE,1*Constants.TILE_SIZE);
        assertTrue(skeleton.followPlayer(playerActor) == Direction.SOUTH );

        playerActor.setPosition(0*Constants.TILE_SIZE,1*Constants.TILE_SIZE);
        assertTrue(skeleton.followPlayer(playerActor) == Direction.WEST );

    }




    public void testSetPosition() throws IOException {
        skeleton = new Skeleton(gameState,0,0,level);

        // Test 1
        assertEquals(0,skeleton.getPosition()[Constants.X]);
        assertEquals(0,skeleton.getPosition()[Constants.Y]);

        // Test 2
        skeleton.setPosition(5,5);
        assertEquals(5,skeleton.getPosition()[Constants.X]);
        assertEquals(5,skeleton.getPosition()[Constants.Y]);

        // Test 3
        skeleton.setPosition(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        assertEquals(Constants.SCREEN_WIDTH,skeleton.getPosition()[Constants.X]);
        assertEquals(Constants.SCREEN_HEIGHT,skeleton.getPosition()[Constants.Y]);
    }

    public void testMoveCharacter() throws IOException {

        skeleton = new Skeleton(gameState,0,0,level);

        // Test 1
        assertEquals(0,skeleton.getPosition()[Constants.X]);
        assertEquals(0,skeleton.getPosition()[Constants.Y]);

        // Test 2
        skeleton.moveSouth();

        assertEquals(0,skeleton.getPosition()[Constants.X]);
        assertEquals(1,skeleton.getPosition()[Constants.Y]);

        // Test 3
        skeleton.moveEast();

        assertEquals(1,skeleton.getPosition()[Constants.X]);
        assertEquals(1,skeleton.getPosition()[Constants.Y]);

        // Test 4
        skeleton.moveWest();

        assertEquals(0,skeleton.getPosition()[Constants.X]);
        assertEquals(1,skeleton.getPosition()[Constants.Y]);

        // Test 5
        skeleton.moveNorth();

        assertEquals(0,skeleton.getPosition()[Constants.X]);
        assertEquals(0,skeleton.getPosition()[Constants.Y]);

    }
}