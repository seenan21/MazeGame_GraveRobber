package items;

import Map.Grid;

import java.awt.image.BufferedImage;

/**
 * Cell which player exits the game through.
 */
public class ExitCell extends Item{

    public ExitCell(Grid grid, int positionX, int positionY) {
        super(grid, "Exit", positionX, positionY);
        setPoints(999);
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
