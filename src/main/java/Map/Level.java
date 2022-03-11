package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import IO.Keyboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private int[][] walls;
    ArrayList<Zombie> zombies;
    private PlayerActor Hero;
    private Grid grid;
    private Keyboard keyboard;
    ArrayList<Wall> wallsList;

    //May need to refactor in the future in order to make it safer for User
    protected Level(Grid grid, Keyboard keyboard, File level) throws FileNotFoundException {
        this.grid = grid;
        this.keyboard = keyboard;
//        walls = new int[grid.getHorizontalTiles()][grid.getVerticalTiles()];
        zombies = new ArrayList<Zombie>();
        wallsList = new ArrayList<Wall>();


        Scanner myReader = new Scanner(level);
        int y = 0;
        while (myReader.hasNextLine()) {
            String str = myReader.nextLine();           //One line of map
            char[] chars = str.toCharArray();           //Turn line into char array for easy traversal
            int x = 0;
            while (x <= str.length()){
                switch (chars[x]) {                     //What to do in different cases
                    case '#':
                        walls[x][y] = 1;
                        break;
                    case 'Z':
                        zombies.add(new Zombie(grid, keyboard, x, y));
                        break;
                    case 'S':
                        Hero = new PlayerActor(grid,keyboard); //Should position be passed onto the hero here from the map? If so new paramter
                }
                x++;
            }
            y++;
        }
        myReader.close();


    }

    public void wallsGenerate(){
        for(int i=0; i < walls.length; i++) {
            for(int j=0; j< walls.length; j++) {
                if (walls[i][j] == 1){
                    //Wall wall = new Wall(i,j, ); //Fix this when
//                    wallsList.add(wall);
                }
            }
        }


    }




}
