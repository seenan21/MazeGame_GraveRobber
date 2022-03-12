package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import IO.Keyboard;

import java.io.*;
import java.util.ArrayList;

public class Level {
    private int[][] walls;
    ArrayList<Zombie> zombies;
    private PlayerActor Hero;
    private Grid grid;
    private Keyboard keyboard;
    ArrayList<Wall> wallsList;

    //May need to refactor in the future in order to make it safer for User
    protected Level(Grid grid, Keyboard keyboard, String path) throws IOException {
        this.grid = grid;
        this.keyboard = keyboard;

        zombies = new ArrayList<Zombie>();
        wallsList = new ArrayList<Wall>();

        BufferedReader myReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
        int y = 0;
        while (myReader.readLine() != null  ) {
            String str = myReader.readLine();           //One line of map
            System.out.println(str);
            char[] chars = str.toCharArray();           //Turn line into char array for easy traversal
            int x = 0;
            while (x <= str.length()){
//                switch (chars[x]) {                     //What to do in different cases
//                    case '#':
//                        walls[x][y] = 1;
//                    case 'Z':
//                        zombies.add(new Zombie(grid, keyboard, x, y));
//                    case 'S':
//                        Hero = new PlayerActor(grid,keyboard); //Should position be passed onto the hero here from the map? If so new paramte
//                }
                x++;
            }
            y++;
        }
        myReader.close();

//        wallsGenerate();


    }

    public void wallsGenerate(){
        for(int i=0; i < walls.length; i++) {
            for(int j=0; j< walls.length; j++) {
                if (walls[i][j] == 1){
                    Wall wall = new Wall(i,j, grid);
                    wallsList.add(wall);
                }
            }
        }


    }

    public void update(){
        Hero.update();
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).update();
        }
    }

    public void draw(){

    }




}
