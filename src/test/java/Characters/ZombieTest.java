package Characters;

import static org.junit.Assert.*;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Level;
import junit.framework.TestCase;

import java.io.IOException;

public class ZombieTest {
    // Dependencies
    PlayerActor playerActor;

    GameState gameState;
    Keyboard keyboard;
    String path = "/level/level_1_foreground.fg";
    Level level;
    int[] position = new int[2];
    Zombie zombie;

    public ZombieTest() throws IOException {
        gameState = new GameState(GameStateType.PLAY);
        keyboard = new Keyboard(gameState);
        level = new Level(gameState, keyboard, path);
        playerActor = new PlayerActor(keyboard, position, level);
        zombie = new Zombie(gameState, position[0], position[1], level );

    }

    /**
     * Test the heroKill function
     */
    @Test
    public void testheroKill() {
        playerActor.setPosition(0,0);
        zombie.setPosition(0,0);
        assertTrue(zombie.heroKill(playerActor));

    }

    /**
     * Test the heroKill function when it shouldn't work
     */
    @Test
    public void testNOTheroKill() {
        playerActor.setPosition(0,0);
        zombie.setPosition(2*Constants.TILE_SIZE,2*Constants.TILE_SIZE);
        assertTrue(!zombie.heroKill(playerActor));

    }

    /**
     * Test the assignment of Zombie Position
     */
    @Test
    public void testSetPosition() {
        zombie.setPosition(3,5);
        assertEquals(3, playerActor.getPosition()[Constants.X]);
        assertEquals(5, playerActor.getPosition()[Constants.Y]);
    }

    /**
     * Testing the rushing of a zombie
     */
    @Test
    public void rushTest() {
        Zombie zombie2 = new Zombie(gameState, 3*Constants.TILE_SIZE, 2*Constants.TILE_SIZE, level );
        zombie.setPosition(2*Constants.TILE_SIZE,2*Constants.TILE_SIZE);
        zombie.set_rushDirection(Direction.EAST);
        zombie.update();
        assertTrue(zombie.get_rushDirection() != Direction.EAST);

    }


    /**
     * Testing the movement of zombie
     */
    @Test
    public void testMoveCharacter() throws IOException {
        // Dependencies
        GameState gameState = new GameState(GameStateType.PLAY);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        // Creating Zombie
        Zombie zombie= new Zombie(gameState,0,0,level);

        // Test 1
        assertEquals(0,zombie.getPosition()[Constants.X]);
        assertEquals(0,zombie.getPosition()[Constants.Y]);

        // Test 2
        zombie.moveSouth();

        assertEquals(0,zombie.getPosition()[Constants.X]);
        assertEquals(1,zombie.getPosition()[Constants.Y]);

        // Test 3
        zombie.moveEast();

        assertEquals(1,zombie.getPosition()[Constants.X]);
        assertEquals(1,zombie.getPosition()[Constants.Y]);

        // Test 4
        skeleton.moveWest();

        assertEquals(0,zombie.getPosition()[Constants.X]);
        assertEquals(1,zombie.getPosition()[Constants.Y]);

        // Test 5
        zombie.moveNorth();

        assertEquals(0,zombie.getPosition()[Constants.X]);
        assertEquals(0,zombie.getPosition()[Constants.Y]);

    }

}