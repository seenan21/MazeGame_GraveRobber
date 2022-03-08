import Characters.Direction;
import Characters.PlayerActor;
import Map.Grid;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        int[] startState = new int[2];
        startState[0] = 5;
        startState[1] = 5;

        int[] endState = new int[2];
        endState[0] = 10;
        endState[1] = 10;

        Grid map = new Grid(100,100,startState,endState);
        PlayerActor playerOne = new PlayerActor(100, Direction.NORTH, map,false);

        System.out.println(playerOne.getStartState()[0]);
        System.out.println(playerOne.getStartState()[1]);
        System.out.println(playerOne.getHealth());
        System.out.println(playerOne.getDirectionFacing());
        System.out.println(playerOne.getHasBossReward());
        System.out.println(playerOne);

        // Testing player movement
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.SOUTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.EAST);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.EAST);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.WEST);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.NORTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
        playerOne.moveCharacter(Direction.NORTH);
        System.out.println(playerOne.getDirectionFacing());
        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);

        // Main GUI Window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Grave Robber");

        // Add Our main game screen to window
        Screen mainScreen = new Screen();
        window.add(mainScreen);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Listen for changes in the thread
        mainScreen.startThread();

    }
}
