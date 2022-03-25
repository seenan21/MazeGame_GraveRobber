package Clock;

import Characters.Character;
import Characters.Direction;
import Constants.Constants;
import Map.Grid;
import Map.Level;

/**
 * Allows the character to move 1 space at a time.
 */
public class CharacterMovementThread implements Runnable{

    int steps = 0;
    private Character _character;
    private Direction _direction;
    private Level _level;

    public CharacterMovementThread(Level level, Character character, Direction direction) {
        this._character = character;
        this._direction = direction;
        this._level = level;
    }

    /**
     * Attempts to move the character's position on the map.
     * Always changes the player's direction facing.
     */
    @Override
    public void run() {

        while(steps <= _character.getStepsAllowed()) {

            boolean isFacingWall = true;
            if (_direction == Direction.NORTH &&
                    _level.collisionCheck(_character, _character.getPosition()[0], _character.getPosition()[1] - _character.getSpeed()) == false) {
                _character.moveNorth();
                isFacingWall = false;
            } else if (_direction == Direction.SOUTH &&
                    _level.collisionCheck(_character, _character.getPosition()[0], _character.getPosition()[1] + _character.getSpeed()) == false) {
                _character.moveSouth();
                isFacingWall = false;
            } else if (_direction == Direction.EAST &&
                    _level.collisionCheck(_character,_character.getPosition()[0] + _character.getSpeed(), _character.getPosition()[1]) == false) {
                _character.moveEast();
                isFacingWall = false;
            } else if (_direction == Direction.WEST &&
                    _level.collisionCheck(_character,_character.getPosition()[0] - _character.getSpeed(),
                            _character.getPosition()[1]) == false) {
                _character.moveWest();
                isFacingWall = false;
            }

            if (isFacingWall == true) {
                _character.spriteCounter = 0;
            } else if (_character.spriteCounter >= 2) {
                _character.spriteCounter = 0;
            } else {
                _character.spriteCounter++;
            }

            _character.setDirectionFacing(_direction);

            try {
                Thread.sleep(Constants.WALK_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            steps = steps + _character.getSpeed();
        }
        _character.setNextMovement(Direction.NONE);
        _character.setWalking(false);
    }
}
