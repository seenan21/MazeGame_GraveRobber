package IO;

import GUI.GameState;
import GUI.GameStateType;
import Map.Sound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * IO.Keyboard listens for keyboard input from the user. Specifically the W,A,S,D keys.
 */
public class Keyboard implements KeyListener {

    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;
    private GameState gameState;

    Sound sound = new Sound();

    public Keyboard(GameState gameState) {
        super();
        this.gameState = gameState;
    }

    public void playSound(int i) {
        sound.setFile(i);
        sound.play();
    }

    /**
     * Unused, but required by KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Listens for when a player presses the W,A,S,D keys.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int userPressed = e.getKeyCode(); // Returns number of pressed key

        System.out.println(gameState.getGameState());
        System.out.println("C");

        if (userPressed == KeyEvent.VK_W) {
            if (gameState.getGameState() == GameStateType.TITLE) {
                playSound(3);
                gameState.setTitleMenuChosen(true);
                System.out.println("A");
            }
            if (gameState.getGameState() == GameStateType.PLAY) {
                upKeyPressed = true;
                downKeyPressed = false;
                leftKeyPressed = false;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_S) {
            if (gameState.getGameState() == GameStateType.TITLE) {
                playSound(3);
                gameState.setTitleMenuChosen(false);
                System.out.println("B");
            }
            if (gameState.getGameState() == GameStateType.PLAY) {
                upKeyPressed = false;
                downKeyPressed = true;
                leftKeyPressed = false;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_A) {
            if (gameState.getGameState() == GameStateType.PLAY) {
                upKeyPressed = false;
                downKeyPressed = false;
                leftKeyPressed = true;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_D) {
            if (gameState.getGameState() == GameStateType.PLAY) {
                upKeyPressed = false;
                downKeyPressed = false;
                leftKeyPressed = false;
                rightKeyPressed = true;
            }
        }
        if (userPressed == KeyEvent.VK_ENTER) {
            if (gameState.getGameState() == GameStateType.TITLE) {
                if (gameState.isTitleMenuChosen()) {
                    gameState.setGameState(GameStateType.PLAY);
                    playSound(4);
                } else {
                    System.exit(0);
                }
            }
            if (gameState.getGameState() == GameStateType.END) {
                System.exit(0);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        upKeyPressed = false;
        downKeyPressed = false;
        leftKeyPressed = false;
        rightKeyPressed = false;
    }
}
