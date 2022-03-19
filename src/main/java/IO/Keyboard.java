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
    public boolean choosingTitleMenu = true;
    public boolean choosingEndMenu = true;
    /**
     * changeGameState:
     * 0: in the title page.
     * 1: playing state.
     * 2: end state.
     */
    public int changeGameState = 0; // 0: in the title page. 1: playing state. 2:


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
            if (changeGameState == 0) {
                choosingTitleMenu = true;
            }
            if (changeGameState == 1) {
                upKeyPressed = true;
                downKeyPressed = false;
                leftKeyPressed = false;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_S) {
            if (changeGameState == 0) {
                choosingTitleMenu = false;
            }
            if (changeGameState == 1) {
                upKeyPressed = false;
                downKeyPressed = true;
                leftKeyPressed = false;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_A) {
            if (changeGameState == 1) {
                upKeyPressed = false;
                downKeyPressed = false;
                leftKeyPressed = true;
                rightKeyPressed = false;
            }
        }
        if (userPressed == KeyEvent.VK_D) {
            if (changeGameState == 1) {
                upKeyPressed = false;
                downKeyPressed = false;
                leftKeyPressed = false;
                rightKeyPressed = true;
            }
        }
        if (userPressed == KeyEvent.VK_ENTER) {
            if (changeGameState == 0) {
                if (choosingTitleMenu) {
                    changeGameState = 1;
                } else {
                    System.exit(0);
                }
            }
            if (changeGameState == 2) {
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

    /**
     * Listens for when a player releases the W,A,S,D keys.
     */
    //@Override
    //public void keyReleased(KeyEvent e) {
//        int userReleased = e.getKeyCode(); // Returns number of released key
//
//        if (userReleased == KeyEvent.VK_W) {
//            upKeyPressed = false;
//        }
//        if (userReleased == KeyEvent.VK_S) {
//            downKeyPressed = false;
//        }
//        if (userReleased == KeyEvent.VK_A) {
//            leftKeyPressed = false;
//        }
//        if (userReleased == KeyEvent.VK_D) {
//            rightKeyPressed = false;
//        }
    //}
}
