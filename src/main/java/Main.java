import GUI.PopUpWindow;
import Map.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setTitle("Grave Robber");
        JPanel status = new JPanel(new BorderLayout());
        JLabel health = new JLabel("HEALTH: " + "100");
        final JLabel time = new JLabel();

        //
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countUpStarter = 0;
            int timeLimit = 60;

            public void run() {
                String temp = "" + countUpStarter;
                time.setText("TIME: " + temp);
                countUpStarter++;

                if (countUpStarter > timeLimit) {
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
