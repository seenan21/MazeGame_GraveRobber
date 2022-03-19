package GUI;

import Characters.Character;
import Characters.Direction;
import Characters.PlayerActor;
import Constants.Constants;
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
    PlayerActor playerActor;
    public double time = 0;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    Keyboard _keyboard;

    /**
     * Constructor for UI.
     * @param gr Grid
     * @param keyboard Keyboard we're using
     */
    public UI(Grid gr, Keyboard keyboard, PlayerActor playerActor){
        this.gr = gr;
        this._keyboard = keyboard;
        this.playerActor = playerActor;
        times_40 = new Font("Times New Roman", Font.PLAIN, 40);
        statusFont = new Font("Ariel", Font.PLAIN, 20);
    }

    /**
     * Draw UI for the game. UI includes TIMER, SCORE, HEALTH.
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2) {

        // endState
        if (gr.gameState == gr.endState) {

            try {
                drawEndPage(g2);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        // titleState
        if (gr.gameState == gr.titleState) {

            try {
                drawTitlePage(g2);
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        // playState
        if (gr.gameState == gr.playState) {

            try {
                drawPlayingUI(g2);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param g2 Graphics2D
     * @throws IOException
     */
    public void drawTitlePage(Graphics2D g2) throws IOException {

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
        if (_keyboard.choosingTitleMenu){
            g2.drawString(">", x-gr.getTileSize()*2, y);
        }
        if (_keyboard.changeGameState == 1){
            gr.gameState = 1;
        }

        text = "QUIT";
        x = gr.getScreenWidth() / 2 - gr.getTileSize()*2;
        y += gr.getTileSize();
        g2.drawString(text, x, y);
        if (!_keyboard.choosingTitleMenu){
            g2.drawString(">", x-gr.getTileSize()*2, y);
        }
        if (_keyboard.changeGameState == 2){
            gr.gameState = 2;
        }


    }

    public void drawPlayingUI(Graphics2D g2) throws IOException{
        if (playerActor.getHealth() > 0) {
            g2.setFont(statusFont);
            g2.setColor(Color.BLACK);
            // health
            g2.drawString("Health: " + playerActor.getHealth(), 10, 25);

            // timer
            time += (double) 1 / 60;
            g2.drawString("Time: " + decimalFormat.format(time), gr.getTileSize() * 19, 25);
        }
        else {
            _keyboard.changeGameState = 2;
            gr.gameState = gr.endState;
        }
    }

    public void drawEndPage(Graphics2D g2) throws IOException{

        int x, y, textLength;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gr.getScreenWidth(),gr.getScreenHeight());

        // GAME OVER
        String text = "GAME OVER";
        g2.setColor(Color.RED);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y = gr.getScreenHeight() / 2 - (gr.getTileSize() * 3);
        g2.drawString(text, x, y);

        // HOW YOU DIED
        if (playerActor.getHealth() <= 0){
            text = "TRAPS JUST KILLED YOU!";
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gr.getScreenWidth() / 2 - textLength / 2;
            y += gr.getTileSize()*2;
            g2.drawString(text, x, y);
        }
        else {
            text = "ZOMBIES JUST KILLED YOU!";
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gr.getScreenWidth() / 2 - textLength / 2;
            y += gr.getTileSize()*2;
            g2.drawString(text, x, y);
        }

        // Display TIME PLAYED
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        text = "TIME PLAYED: " + decimalFormat.format(time) + " SECONDS";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y += gr.getTileSize()*5;
        g2.drawString(text, x, y);

        // Display SCORE
        text = "HEART(REWARD) COLLECTED: " + playerActor.regularHeartCollected;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y += gr.getTileSize();
        g2.drawString(text, x, y);

        text = "BIG HEART(BONUS REWARD) COLLECTED: " + playerActor.bigHeartCollected;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y += gr.getTileSize();
        g2.drawString(text, x, y);

        // BUTTON: Quit
        text = "PRESS ENTER TO QUIT";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gr.getScreenWidth() / 2 - textLength / 2;
        y += gr.getTileSize()*3;
        g2.drawString(text, x, y);
    }

}
