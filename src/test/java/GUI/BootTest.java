package GUI;

import Map.Grid;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BootTest {

    /**
     * Tests the system for a crash on boot.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void bootTest() throws IOException, InterruptedException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setTitle("Grave Robber");

        // Add Our main game screen to window
        Grid mainGrid = new Grid();
        window.add(mainGrid);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Listen for changes in the thread
        mainGrid.startThread();
        mainGrid.gameState.setGameState(GameStateType.END);
        Thread.sleep(2000);
        mainGrid.stop();
        window.dispose();
    }

    /**
     * Tests if the system can properly load the game.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void bootStateTest() throws IOException, InterruptedException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setTitle("Grave Robber");

        // Add Our main game screen to window
        Grid mainGrid = new Grid();
        window.add(mainGrid);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Listen for changes in the thread
        mainGrid.startThread();
        Thread.sleep(2000);
        mainGrid.gameState.setGameState(GameStateType.PLAY);
        Thread.sleep(2000);
        mainGrid.gameState.setGameState(GameStateType.TITLE);
        Thread.sleep(2000);
        mainGrid.gameState.setGameState(GameStateType.END);
        Thread.sleep(2000);
        mainGrid.stop();
        window.dispose();
    }

}
