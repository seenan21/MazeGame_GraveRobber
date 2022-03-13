package IO;

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
    public int choosingMenu = 0;


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

        if (userPressed == KeyEvent.VK_W) {
            upKeyPressed = true;
        }
        if (userPressed == KeyEvent.VK_S) {
            downKeyPressed = true;
        }
        if (userPressed == KeyEvent.VK_A) {
            leftKeyPressed = true;
        }
        if (userPressed == KeyEvent.VK_D) {
            rightKeyPressed = true;
        }
    }

    /**
     * Listens for when a player releases the W,A,S,D keys.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int userReleased = e.getKeyCode(); // Returns number of released key

        if (userReleased == KeyEvent.VK_W) {
            upKeyPressed = false;
        }
        if (userReleased == KeyEvent.VK_S) {
            downKeyPressed = false;
        }
        if (userReleased == KeyEvent.VK_A) {
            leftKeyPressed = false;
        }
        if (userReleased == KeyEvent.VK_D) {
            rightKeyPressed = false;
        }
    }
}
