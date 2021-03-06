package GUI;

import Characters.PlayerActor;
import Clock.Timer;
import Constants.Constants;
import Map.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Contains all UI functions for the project.
 */
public class UI{

    private Font statusFont;
    private PlayerActor playerActor;
    private double time = 0;
    private double timeFinal = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private Timer timer;
    private GameState _gameState;
    private Sound sound = new Sound();

    /**
     * Constructor for UI.
     */
    public UI(GameState gameState, PlayerActor playerActor){
        this._gameState = gameState;
        this.playerActor = playerActor;
        statusFont = new Font("Ariel", Font.PLAIN, 20);
        timer = new Timer();
        Thread timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * get the position where the string should start
     * the position will keep the message on middle of the screen
     */
    private int getCentreX(int textLength){
        return Constants.SCREEN_WIDTH / 2 - textLength / 2;
    }

    /**
     * Get the length of the text
     */
    private int getTextLength(String text, Graphics2D g2){
        return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    /**
     * Draw UI for the game. UI includes TIMER, SCORE, HEALTH.
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2) {

        // endState
        if (_gameState.getGameState() == GameStateType.END) {

            try {
                drawEndPage(g2);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        // titleState
        if (_gameState.getGameState() == GameStateType.TITLE) {

            try {
                drawTitlePage(g2);
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        // playState
        if (_gameState.getGameState() == GameStateType.PLAY) {

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
        int x, y;
        x = getCentreX(getTextLength(text, g2));
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
        if (_gameState.isTitleMenuChosen()){
            g2.drawString(">", x- Constants.TILE_SIZE*2, y);
        }
        if (_gameState.getGameState() == GameStateType.PLAY){
            sound.playSound(3);
        }

        text = "QUIT";
        x = Constants.SCREEN_WIDTH / 2 - Constants.TILE_SIZE*2;
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);
        if (!_gameState.isTitleMenuChosen()){
            g2.drawString(">", x- Constants.TILE_SIZE*2, y);
        }
    }


    /**
     * Drawing playing UI. Include time, health.
     * @param g2
     * @throws IOException
     */
    public void drawPlayingUI(Graphics2D g2) throws IOException{
        if (playerActor.getHealth() > 0) {
            g2.setFont(statusFont);
            g2.setColor(Color.BLACK);
            // health
            g2.drawString("Health: " + playerActor.getHealth(), 10, 25);

            // timer
            time = timer.getElapsedTime();
            g2.drawString("Time: " + decimalFormat.format(time), Constants.TILE_SIZE * 19, 25);
        }
        else {
            _gameState.setGameState(GameStateType.END);
        }
    }

    /**
     * Drawing End Page.
     * @param g2
     * @throws IOException
     */
    public void drawEndPage(Graphics2D g2) throws IOException{

        int x, y;
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
            x = getCentreX(getTextLength(text, g2));
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "TRAPS JUST KILLED YOU!";
            g2.setColor(Color.BLUE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            x = getCentreX(getTextLength(text, g2));
            y += Constants.TILE_SIZE*2;
            g2.drawString(text, x, y);
        }
        else if (_gameState.isWin()){
            text = "CONGRATULATIONS!";
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            x = getCentreX(getTextLength(text, g2));
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "PLAYER'S FINAL HEALTH: " + playerActor.getHealth();
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
            x = getCentreX(getTextLength(text, g2));
            y += Constants.TILE_SIZE;
            g2.drawString(text, x, y);

        }
        else {
            text = "GAME OVER";
            g2.setColor(Color.RED);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            x = getCentreX(getTextLength(text, g2));
            y = Constants.SCREEN_HEIGHT / 2 - (Constants.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "ZOMBIES JUST KILLED YOU!";
            g2.setColor(Color.BLUE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            x = getCentreX(getTextLength(text, g2));
            y += Constants.TILE_SIZE*2;
            g2.drawString(text, x, y);
        }

        // Display TIME PLAYED
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        text = "TIME PLAYED: " + decimalFormat.format(timeFinal) + " SECONDS";
        x = getCentreX(getTextLength(text, g2));
        y += Constants.TILE_SIZE*5;
        g2.drawString(text, x, y);

        // Display SCORE
        text = "HEART(REWARD) COLLECTED: " + playerActor.regularHeartCollected;
        x = getCentreX(getTextLength(text, g2));
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);

        text = "BIG HEART(BONUS REWARD) COLLECTED: " + playerActor.bigHeartCollected;
        x = getCentreX(getTextLength(text, g2));
        y += Constants.TILE_SIZE;
        g2.drawString(text, x, y);

        // BUTTON: Quit
        text = "PRESS ENTER TO QUIT";
        x = getCentreX(getTextLength(text, g2));
        y += Constants.TILE_SIZE*3;
        g2.drawString(text, x, y);
    }

}
