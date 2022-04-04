package items;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreasureTest {


    Treasure treasure;
    int positionX;
    int positionY;
    public TreasureTest(){treasure = new Treasure(positionX, positionY);
    }



    @Test
    public void getImageTest() {
        assertEquals(treasure._image,treasure.getImage());
    }


}