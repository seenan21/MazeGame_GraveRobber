package items;

import org.junit.Test;

import javax.swing.text.Position;

import static org.junit.Assert.*;

public class TrapTest {
    Trap trap;
    int positionX;
    int positionY;
    public TrapTest(){trap = new Trap(positionX,positionY);}

    @Test
    public void getImage() {
        assertEquals(trap.getImage(),"/item/trap_128.png");
    }
}