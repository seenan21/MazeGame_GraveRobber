import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    final int tileSize = 16;
    final int HORIZONTAL_TILES = 48;
    final int VERTICAL_TILES = 48;
    final int screenWidth = tileSize * HORIZONTAL_TILES;
    final int screenHeight = tileSize * VERTICAL_TILES;

    public Screen() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improves rendering
    }
}
