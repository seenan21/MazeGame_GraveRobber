package GUI;

import Characters.PlayerActor;
import IO.Keyboard;
import Map.Level;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class GameStateTest {

    /**
     * Test if when player wins the game.
     * @throws IOException
     */
    @Test
    public void testGameStateWin() throws IOException {
        int [] i = {0, 1};
        GameState gameState = new GameState(GameStateType.TITLE);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        PlayerActor playerActor = new PlayerActor(keyboard, i, level);
        // UI ui = new UI(gameState, playerActor);



        gameState.setWin(true);
        boolean win = gameState.isWin();
        gameState.setGameState(GameStateType.END);
        assertTrue(win);
        assertEquals(GameStateType.END, gameState.getGameState());
    }

    @Test
    public void testGameStateLose() throws IOException {

    }

    /**
     * Test if the player is playing the game
     * @throws IOException
     */
    @Test
    public void testGameStatePLAY() throws IOException {
        int [] i = {0, 1};
        GameState gameState = new GameState(GameStateType.TITLE);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        PlayerActor playerActor = new PlayerActor(keyboard, i, level);
        // UI ui = new UI(gameState, playerActor);
        gameState.setGameState(GameStateType.PLAY);
        boolean notWin = gameState.isWin();
        assertFalse(notWin);
        assertEquals(GameStateType.PLAY, gameState.getGameState());
    }

    /**
     * Test if the player is in the title page
     * @throws IOException
     */
    @Test
    public void testGameStateTitle() throws IOException {
        int [] i = {0, 1};
        GameState gameState = new GameState(GameStateType.TITLE);
        Keyboard keyboard = new Keyboard(gameState);
        String path = "/level/level_1_foreground.fg";
        Level level = new Level(gameState, keyboard, path);
        PlayerActor playerActor = new PlayerActor(keyboard, i, level);
        // UI ui = new UI(gameState, playerActor);
        boolean notWin = gameState.isWin();
        assertFalse(notWin);
        assertEquals(GameStateType.TITLE, gameState.getGameState());
    }
}