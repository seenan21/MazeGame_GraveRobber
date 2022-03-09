package GUI;

import javax.swing.*;

public class PopUpWindow {
    public PopUpWindow(){
        super();

        JFrame frame = new JFrame("Your time is up!");

        JLabel message = new JLabel();
        message.setText("YOur fking time is up mate!");
//        frame.setSize(4000,4000);
        frame.setResizable(false);
        frame.add(message);


        frame.pack();
        frame.setSize(400,400);
        frame.setLocation(500, 300);
        frame.setVisible(true);
    }
}
