package GUI;

import Characters.PlayerActor;
import Clock.Timer;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Contains all UI functions for the project.
 */
public class UI{

    private Grid _grid;
    private Font statusFont;
    private PlayerActor playerActor;
    private double time = 0;
    private double timeFinal = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private Keyboard _keyboard;
    private Timer timer;

    /**
     * Constructor for UI.
     * @param grid Grid
     * @param keyboard Keyboard we're using
     */
    public UI(Grid grid, Keyboard keyboard, PlayerActor playerActor){
        this._grid = grid;
        this._keyboard = keyboard;
        this.playerActor = playerActor;
        statusFont = new Font("Ariel", Font.PLAIN, 20);
        timer = new Timer();
        Thread timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * Draw UI for the game. UI includes TIMER, SCORE, HEALTH.
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2) {

        // endState
        if (_grid.gameState == _grid.endState) {

            try {
                drawEndPage(g2);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        // titleState
        if (_grid.gameState == _grid.titleState) {

            try {
                drawTitlePage(g2);
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        // playState
        if (_grid.gameState == _grid.playState) {

            try {
                drawPlayingUI(g2);
                timer.setStartTimer(true);
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
        g2.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Grave Robber";
        int x, y, textLength;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
        y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);

        // Shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+4, y+4);
        // Main color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // Image
        x = Constants.SCREEN_WIDTH/2 - Constants.TILE_SIZE*4;
        y += Constants.TILE_SIZE*2;
        g2.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite/grave_robber_hero/hero_down_1.png"))),x , y, Constants.TILE_SIZE*6, Constants.TILE_SIZE*3, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

        text = "START";
        x = Constants.SCREEN_WIDTH / 2 - Constants.TILE_SIZE*2;
        y += Constants.TILE_SIZE*5;
        g2.drawString(text, x, y);
        if (_keyboard.choosingTitleMenu){
            g2.drawString(">", x- Constants.TILE_SIZE*2, y);
        }
        if (_keyboard.changeGameState == 1){
            _grid.playSound(3);
            _grid.gameState = 1;
        }

        text = "QUIT";
        x = Constants.SCREEN_WIDTH / 2 - Constants.TILE_SIZE*2;
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);
        if (!_keyboard.choosingTitleMenu){
            g2.drawString(">", x- Constants.TILE_SIZE*2, y);
        }
        if (_keyboard.changeGameState == 2){
            _grid.gameState = 2;
        }


    }

    public void drawPlayingUI(Graphics2D g2) throws IOException{
        if (playerActor.getHealth() > 0) {
            g2.setFont(statusFont);
            g2.setColor(Color.BLACK);
            // health
            g2.drawString("Health: " + playerActor.getHealth(), 10, 25);

            // timer
//            time += (double) 1 / 60;
            time = timer.getElapsedTime();
            g2.drawString("Time: " + decimalFormat.format(time), Constants.TILE_SIZE * 19, 25);
        }
        else {
            _keyboard.changeGameState = 2;
            _grid.gameState = _grid.endState;
        }
    }

    public void drawEndPage(Graphics2D g2) throws IOException{

        int x, y, textLength;
        timeFinal = time;
        y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
        String text;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // HOW YOU DIED
        if (playerActor.getHealth() <= 0){
            text = "GAME OVER";
            g2.setColor(Color.RED);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "TRAPS JUST KILLED YOU!";
            g2.setColor(Color.BLUE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y += Constants.TILE_SIZE*2;
            g2.drawString(text, x, y);
        }
        else if (_grid.win){
            text = "CONGRATULATIONS!";
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "PLAYER'S FINAL HEALTH: " + playerActor.getHealth();
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y += Constants.TILE_SIZE;
            g2.drawString(text, x, y);

        }
        else {
            text = "GAME OVER";
            g2.setColor(Color.RED);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "ZOMBIES JUST KILLED YOU!";
            g2.setColor(Color.BLUE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
            y += Constants.TILE_SIZE*2;
            g2.drawString(text, x, y);
        }

        // Display TIME PLAYED
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        text = "TIME PLAYED: " + decimalFormat.format(timeFinal) + " SECONDS";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
        y += Constants.TILE_SIZE*5;
        g2.drawString(text, x, y);

        // Display SCORE
        text = "HEART(REWARD) COLLECTED: " + playerActor.regularHeartCollected;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);

        text = "BIG HEART(BONUS REWARD) COLLECTED: " + playerActor.bigHeartCollected;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);

        // BUTTON: Quit
        text = "PRESS ENTER TO QUIT";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = Constants.SCREEN_WIDTH / 2 - textLength / 2;
        y += Constants.TILE_SIZE*3;
        g2.drawString(text, x, y);
    }

}
