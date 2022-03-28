package items;

import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * If the play steps on a trap they will receive damage.
 */
public class Trap extends Item{

    public Trap(int positionX, int positionY) {
        super("Trap", positionX, positionY);
        setPoints(-1);
        getImage();
    }

    @Override
    public BufferedImage getImage() {
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/item/trap_128.png")));
            return this._image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update() {
        setAvailable(true);
    }
}
