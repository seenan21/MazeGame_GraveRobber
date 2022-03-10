package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import IO.Keyboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Level {
    private int[][] Walls;
    ArrayList<Zombie> zombies;
    private PlayerActor Hero;
    private Grid grid;
    private Keyboard keyboard;

    //May need to refactor in the future in order to make it safer for User
    protected Level(Grid grid, Keyboard keyboard, File level) throws FileNotFoundException {
        this.grid = grid;
        this.keyboard = keyboard;
        Walls = new int[grid.getHorizontalTiles()][grid.getVerticalTiles()];
        zombies = new ArrayList<Zombie>();


        Scanner myReader = new Scanner(level);
        int y = 0;
        while (myReader.hasNextLine()) {
            String str = myReader.nextLine();           //One line of map
            char[] chars = str.toCharArray();           //Turn line into char array for easy traversal
            int x = 0;
            while (x <= str.length()){
                switch (chars[x]) {                     //What to do in different cases
                    case '#':
                        Walls[x][y] = 1;
                        break;
                    case 'Z':
                        zombies.add(new Zombie(grid, keyboard, x, y));
                        break;
                    case 'P':
                        Hero = new PlayerActor(grid,keyboard); //Should position be passed onto the hero here from the map?
                }
                x++;
            }
            y++;
        }
        myReader.close();
    }




}
