package Characters;

import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import Map.Level;
import junit.framework.TestCase;

import java.io.IOException;

public class SkeletonTest extends TestCase {

    public void testSetPosition() throws IOException {
        // Dependencies
        GameState gameState = new GameState(GameStateType.PLAY);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        // Creating Skeleton
        Skeleton skeleton = new Skeleton(gameState,0,0,level);

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
        // Dependencies
        GameState gameState = new GameState(GameStateType.PLAY);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        // Creating Skeleton
        Skeleton skeleton = new Skeleton(gameState,0,0,level);

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