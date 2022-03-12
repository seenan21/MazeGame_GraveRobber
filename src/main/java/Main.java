import Characters.PlayerActor;
import GUI.PopUpWindow;
import Map.Grid;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Main {
    public static void main(String[] args) throws IOException {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setTitle("Grave Robber");

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
