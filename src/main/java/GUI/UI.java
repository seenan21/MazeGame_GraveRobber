package GUI;

import Map.Grid;
import java.awt.*;
import java.text.DecimalFormat;

public class UI{

    Grid gr;
    Font times_40;
    Font statusFont;
    double time;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public UI(Grid gr){
        this.gr = gr;
        times_40 = new Font("Times New Roman", Font.PLAIN, 40);
        statusFont = new Font("Ariel", Font.PLAIN, 20);
    }

    public void draw(Graphics2D g2){
        boolean gameEnd = false;
        boolean lose = false;
        if(gameEnd){
            // game end
            int x, y;
            int textLength;
            String text;

            // lost the game by collision with monster
            if(lose){
                g2.setFont(times_40);
                g2.setColor(Color.RED);
                text = "GAME OVER!";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gr.getScreenWidth()/2 - textLength/2;
                y = gr.getScreenHeight()/2 - (gr.getTileSize()*3);
                g2.drawString(text, x, y);
//                gr.stop(); Need to stop the thread.
            }
            else{
                g2.setFont(times_40);
                g2.setColor(Color.RED);
                text = "YOU WIN!";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gr.getScreenWidth()/2 - textLength/2;
                y = gr.getScreenHeight()/2 - (gr.getTileSize()*3);
                g2.drawString(text, x, y);
//                gr.stop(); Need to stop the thread.
            }
        }
        // not losing and time is not up
        else{
            g2.setFont(statusFont);
            g2.setColor(Color.BLACK);
            // health
            g2.drawString("Health: " + "100", 10, 25);

            // timer
            time +=(double)1/60;
            g2.drawString("Time: " + decimalFormat.format(time), gr.getTileSize()*19, 25);

        }

    }
}
