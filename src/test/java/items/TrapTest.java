package items;

import Clock.Timer;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class TrapTest {
    Trap trap;


    @Test
    public void getImage() {

        assertEquals(true,"/item/trap_128.png",trap.getImage());
    }



    @Test
    public void update() {
    }
}