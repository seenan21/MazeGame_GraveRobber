package Clock;

import Characters.Character;
import Characters.Direction;
import Map.Grid;
import Map.Level;

public class CharacterClock implements Runnable{

    int steps = 0;
    private Grid _grid;
    private Character _character;
    private Direction _direction;
    private Level _level;

    public CharacterClock(Grid grid, Level level, Character character, Direction direction) {
        this._grid = grid;
        this._character = character;
        this._direction = direction;
        this._level = level;
    }

    @Override
    public void run() {

        while(steps <= _grid.getTileSize()/2) {

            boolean isFacingWall = true;
            if (_direction == Direction.NORTH && _level.wallCheck(_character.getPosition()[0], _character.getPosition()[1] - _character.getSpeed()) == false) {
                _character.moveNorth();
                isFacingWall = false;
            } else if (_direction == Direction.SOUTH && _level.wallCheck(_character.getPosition()[0], _character.getPosition()[1] + _character.getSpeed()) == false) {
                _character.moveSouth();
                isFacingWall = false;
            } else if (_direction == Direction.EAST && _level.wallCheck(_character.getPosition()[0] + _character.getSpeed(), _character.getPosition()[1]) == false) {
                _character.moveEast();
                isFacingWall = false;
            } else if (_direction == Direction.WEST && _level.wallCheck(_character.getPosition()[0] - _character.getSpeed(), _character.getPosition()[1]) == false) {
                _character.moveWest();
                isFacingWall = false;
            }

            if (_character.getPreviousDirectionFacing() != _character.getDirectionFacing() || isFacingWall == true) {
                _character.spriteCounter = 0;
            } else if (_character.spriteCounter >= 2) {
                _character.spriteCounter = 0;
            } else {
                _character.spriteCounter++;
            }


            _character.setDirectionFacing(_direction);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            steps = steps + _character.getSpeed();
        }
        _character.setNextMovement(Direction.NONE);
        _character.setWalking(false);
    }
}
