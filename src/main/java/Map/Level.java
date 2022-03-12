package Map;
import Characters.PlayerActor;
import Characters.Zombie;
import IO.Keyboard;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Level {
    public int[][] walls;
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
        walls = new int[24][24];

        BufferedReader myReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
        int y = 0;
        while (myReader.readLine() != null  ) {
            String str = myReader.readLine();           //One line of map
            System.out.println(str);
            char[] chars = str.toCharArray();           //Turn line into char array for easy traversal
            int x = 0;
            while (x < str.length()){
                if (chars[x] == '#'){
                    walls[x][y] = 1;
                }
                else if(chars[x] == 'Z'){
                    zombies.add(new Zombie(grid, keyboard, x*grid.getTileSize(), y*grid.getTileSize(), this));
                }else if (chars[x] == 'S'){
                    int[] position = new int[2];
                    position[0] = x* grid.getTileSize();
                    position[1] = y* grid.getTileSize();
                    Hero = new PlayerActor(grid,keyboard, position, this ); //Should position be passed onto the hero here from the map? If so new paramter                }
                }
                x++;
            }
            y++;
        }
        myReader.close();

        wallsGenerate();


    }

    public PlayerActor getHero() {
        return Hero;
    }

    public void wallsGenerate(){
        for(int i=0; i < walls.length; i++) {
            for(int j=0; j< walls.length; j++) {
                if (walls[i][j] == 1){
                    Wall wall = new Wall(i* grid.getTileSize(),j* grid.getTileSize(), grid);
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

    public void draw(Graphics2D g2){
        for (Zombie zombie: zombies){
            zombie.draw(g2);
        }

        for (Wall wall: wallsList){
            wall.draw(g2);
        }

        Hero.draw(g2);

    }




}
