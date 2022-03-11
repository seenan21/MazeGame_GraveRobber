import GUI.PopUpWindow;
import Map.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World");
//
//        int[] startState = new int[2];
//        startState[0] = 5;
//        startState[1] = 5;
//
//        int[] endState = new int[2];
//        endState[0] = 10;
//        endState[1] = 10;
//
//        Grid map = new Grid(100,100,startState,endState);
//        PlayerActor playerOne = new PlayerActor(100, Direction.NORTH, map,false);
//
//        System.out.println(playerOne.getStartState()[0]);
//        System.out.println(playerOne.getStartState()[1]);
//        System.out.println(playerOne.getHealth());
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println(playerOne.getHasBossReward());
//        System.out.println(playerOne);
//
//        // Testing player movement
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.SOUTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.EAST);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.EAST);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.WEST);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.NORTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);
//        playerOne.moveCharacter(Direction.NORTH);
//        System.out.println(playerOne.getDirectionFacing());
//        System.out.println("X= " + playerOne.getPosition()[0] + " Y= " + playerOne.getPosition()[1]);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setTitle("Grave Robber");
        JPanel status = new JPanel(new BorderLayout());
        JLabel health = new JLabel("100");
        final JLabel time = new JLabel();

        //
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 10;

            public void run() {
                String temp = "" + countdownStarter;
                time.setText(temp);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                PopUpWindow pop = new PopUpWindow();
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        //

        JLabel score = new JLabel();
        status.add(health, BorderLayout.WEST);
        status.add(time, BorderLayout.EAST);

        // Add status
        window.add(status, BorderLayout.NORTH);

        // Add Our main game screen to window
        Grid mainGrid = new Grid();
        window.add(mainGrid);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Listen for changes in the thread
        mainGrid.startThread();

    }
}
