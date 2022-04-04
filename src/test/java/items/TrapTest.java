package items;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.text.Position;

import java.io.IOException;

import static org.junit.Assert.*;

public class TrapTest {
    Trap trap;
    int positionX;
    int positionY;

    public TrapTest() {
        trap = new Trap(positionX, positionY);
    }

    @Test
    public void getImage() {


        assertEquals("/item/trap_128.png", trap._image);
    }
}
