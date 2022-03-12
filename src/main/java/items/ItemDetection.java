package items;

import Characters.PlayerActor;
import Constants.Constants;
import Map.Grid;

import java.awt.*;

public class ItemDetection {

    Grid _grid;

    public ItemDetection(Grid grid) {
        this._grid = grid;
    }

    public void onItem(PlayerActor playerActor) {

        // Cycle though Items on Grid
        int i=0;
        while (i < _grid.getItemLimit()) {
            if(_grid.treasure[i] != null) {

                // Get current position of the Item
                int[] positionItem = new int[2];
                positionItem = _grid.treasure[i].getPosition();

                // Find where the player is on the Grid
                Rectangle itemBodyGrid = (_grid.treasure[i]).getItemBody();
                itemBodyGrid.x = positionItem[Constants.X];
                itemBodyGrid.y = positionItem[Constants.Y];


                // Get current position of the Player
                int[] positionPlayer = new int[2];
                positionPlayer = playerActor.getPosition();

                // Find where the player is on the Grid
                Rectangle playerBodyGrid = playerActor.getSpriteBody();
                playerBodyGrid.x = positionPlayer[Constants.X];
                playerBodyGrid.y = positionPlayer[Constants.Y];

                System.out.println("Player");
                System.out.println(playerBodyGrid.x);
                System.out.println(playerBodyGrid.y);
                System.out.println("Item");
                System.out.println(itemBodyGrid.x);
                System.out.println(itemBodyGrid.y);

                if(playerBodyGrid.intersects(itemBodyGrid)) {
                    System.out.println("ON");
                    playerActor.addToScore((_grid.treasure[i]).getPoints());
                    _grid.treasure[i] = null;
                }

            }
            i++;
        }

    }
}
