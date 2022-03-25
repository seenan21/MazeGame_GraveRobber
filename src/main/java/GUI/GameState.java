package GUI;

public class GameState {
    private GameStateType _gameState;
    private boolean titleMenuChosen = false;
    private boolean win = false;

    public GameState(GameStateType _gameState) {
        this._gameState = _gameState;
    }

    public GameStateType getGameState() {
        return _gameState;
    }

    public void setGameState(GameStateType _gameState) {
        this._gameState = _gameState;
    }

    public void setTitleMenuChosen(boolean titleMenuChosen) {
        this.titleMenuChosen = titleMenuChosen;
    }

    public boolean isTitleMenuChosen() {
        return titleMenuChosen;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isWin() {
        return win;
    }
}
