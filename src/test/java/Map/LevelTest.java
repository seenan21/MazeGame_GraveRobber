package Map;

import Characters.PlayerActor;
import Constants.Constants;
import GUI.GameState;
import GUI.GameStateType;
import IO.Keyboard;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;


public class LevelTest extends TestCase {
    GameState gameState;
    Keyboard keyboard;
    String path = "/level/level_1_foreground.fg";
    Level level;

    public LevelTest() throws IOException {
        gameState = new GameState(GameStateType.PLAY);
        keyboard = new Keyboard(gameState);
        level = new Level(gameState, keyboard, path);
    }

    @Test
    /**
     * Tests if the Character will collide with a wall.
     */
    public void testCollisionCheck(){
        int[] position = new int[2];
        position[Constants.X] = 0;
        position[Constants.Y] = 0;
        PlayerActor playerActor = new PlayerActor(keyboard, position, level);
        assertEquals(true,level.collisionCheck(playerActor,position[Constants.X],position[Constants.Y]));
    }
}