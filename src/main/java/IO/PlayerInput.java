package IO;

import Characters.Direction;

public class PlayerInput implements Runnable {
    private Keyboard _keyboard;
    private Direction _direction = Direction.NONE;

    public PlayerInput(Keyboard _keyboard) {
        this._keyboard = _keyboard;
    }

    @Override
    public void run() {

        while(true) {
            System.out.println("T");
            if (_keyboard.upKeyPressed) {
                _direction = Direction.NORTH;
            } else if (_keyboard.downKeyPressed) {
                _direction = Direction.SOUTH;
            } else if (_keyboard.leftKeyPressed) {
                _direction = Direction.WEST;
            } else if (_keyboard.rightKeyPressed) {
                _direction = Direction.EAST;
            }
        }
    }

    public void clearDirection() {
        _direction = Direction.NONE;
    }

    public Direction getDirection() {
        return _direction;
    }
}
