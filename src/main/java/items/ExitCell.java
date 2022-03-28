package items;

import Constants.Constants;

import java.awt.image.BufferedImage;

/**
 * Cell which player exits the game through.
 */
public class ExitCell extends Item{

    public ExitCell(int positionX, int positionY) {
        super( "Exit", positionX, positionY);
        setPoints(Constants.EXIT_CELL);
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void update() {
        setAvailable(true);
    }
}
