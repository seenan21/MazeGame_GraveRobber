import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
