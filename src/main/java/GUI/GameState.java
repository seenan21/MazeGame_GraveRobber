package GUI;

/**
 * The games state class keeps track of which screen the GUI should be drawing
 */
public class GameState {
    private GameStateType _gameState;
    private boolean titleMenuChosen = false;
    private boolean win = false;

    public GameState(GameStateType _gameState) {
        this._gameState = _gameState;
    }

    /**
     *
     * @return current state of game
     */
    public GameStateType getGameState() {
        return _gameState;
    }

    /**
     *
     * @param _gameState - which state the game should be in
     */
    public void setGameState(GameStateType _gameState) {
        this._gameState = _gameState;
    }

    /**
     *
     * @param titleMenuChosen - title menu option has been chosen
     */
    public void setTitleMenuChosen(boolean titleMenuChosen) {
        this.titleMenuChosen = titleMenuChosen;
    }

    /**
     *
     * @return has title menu been chosen
     */
    public boolean isTitleMenuChosen() {
        return titleMenuChosen;
    }

    /**
     *
     * @param win - the player has won the game
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     *
     * @return if the player won the game
     */
    public boolean isWin() {
        return win;
    }
}
