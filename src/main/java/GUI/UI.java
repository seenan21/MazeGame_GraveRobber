package GUI;

import Characters.Character;
import Characters.Direction;
import Characters.PlayerActor;
import IO.Keyboard;
import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class UI{

    Grid gr;
    Font times_40;
    Font statusFont;
    double time;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public int menuNum = 0;
    Keyboard _keyboard;

    public UI(Grid gr){
        this.gr = gr;
        times_40 = new Font("Times New Roman", Font.PLAIN, 40);
        statusFont = new Font("Ariel", Font.PLAIN, 20);
    }

    public void draw(Graphics2D g2) {

        boolean lose = false;
        if (gr.gameState == gr.endState) {
            // game end
            int x, y;
            int textLength;
            String text;

            // lost the game by collision with monster
            if (lose) {
                g2.setFont(times_40);
                g2.setColor(Color.RED);
                text = "GAME OVER!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gr.getScreenWidth() / 2 - textLength / 2;
                y = gr.getScreenHeight() / 2 - (gr.getTileSize() * 3);
                g2.drawString(text, x, y);
//                gr.stop(); Need to stop the thread.
            } else {
                g2.setFont(times_40);
                g2.setColor(Color.RED);
                text = "YOU WIN!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gr.getScreenWidth() / 2 - textLength / 2;
                y = gr.getScreenHeight() / 2 - (gr.getTileSize() * 3);
                g2.drawString(text, x, y);
//                gr.stop(); Need to stop the thread.
            }
        }
        if (gr.gameState == gr.titleState) {
            try {
                drawTitlePage(g2);
            }catch(IOException e){
                e.printStackTrace();
            }

        }
        // not losing and time is not up
        else {
            g2.setFont(statusFont);
            g2.setColor(Color.BLACK);
            // health
            g2.drawString("Health: " + "100", 10, 25);

            // timer
            time += (double) 1 / 60;
            g2.drawString("Time: " + decimalFormat.format(time), gr.getTileSize() * 19, 25);

        }
    }
    public void drawTitlePage(Graphics2D g2, Keyboard keyboard) throws IOException {
        this._keyboard =
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0,0,gr.getScreenWidth(),gr.getScreenHeight());
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Grave Robber";
        int x, y, textLength;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y = gr.getScreenHeight() / 2 - (gr.getTileSize() * 3);

        // Shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+4, y+4);
        // Main color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // Image
        x = gr.getScreenWidth()/2 - gr.getTileSize()*4;
        y += gr.getTileSize()*2;
        g2.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_down_1.png"))),x , y, gr.getTileSize()*6, gr.getTileSize()*3, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

        text = "START";
        x = gr.getScreenWidth() / 2 - gr.getTileSize()*2;
        y += gr.getTileSize()*5;
        g2.drawString(text, x, y);
//        if (_keyboard.choosingMenu == 0){
////            g2.drawString(">", x-gr.getTileSize(), y);
//        }

        text = "QUIT";
        x = gr.getScreenWidth() / 2 - gr.getTileSize()*2;
        y += gr.getTileSize();
        g2.drawString(text, x, y);
//        if (_keyboard.choosingMenu == 1){
////            g2.drawString(">", x-gr.getTileSize(), y);
//        }


    }

}
