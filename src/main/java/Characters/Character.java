package Characters;

import Clock.CharacterMovementThread;
import Constants.Constants;
import IO.Keyboard;
import Map.Grid;
import Map.Level;
import items.Item;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;

/**
 * Contains methods that are applicable to all npc and human players.
 */
public abstract class Character{

    Grid _grid;
    Keyboard _keyboard;

    private int health;
    private ArrayList<Item> bag = new ArrayList<>();
    private int[] position = new int[2];
    private int _speed;
    private int _stepsAllowed;
    private int[] startState = new int[2];
    private Direction directionFacing;
    private Direction previousDirectionFacing;
    private Direction nextMovement = Direction.NONE;
    private BufferedImage _spriteNorth0, _spriteSouth0, _spriteEast0, _spriteWest0, _spriteNorth1, _spriteSouth1, _spriteEast1, _spriteWest1, _spriteNorth2, _spriteSouth2, _spriteEast2, _spriteWest2;
    private Rectangle spriteBody;
    public int spriteCounter = 0;
    protected Level level;
    private boolean _walking = false;
    private CharacterType _characterType = CharacterType.NONE;

    /**
     * Constructor for the character class.
     * <p>
     * //     * @param health - Character's total health
     * //     * @param directionFacing - Direction the character is facing
     * //     * @param map - Map the character is being added to
     */
    public Character(Grid grid, Keyboard keyboard, Level level) {
        this._keyboard = keyboard;
        this._grid = grid;
        this.level = level;
        setStepsAllowed(1);
        spriteBody = new Rectangle(0,0, _grid.getTileSize(),_grid.getTileSize());
    }

    /**
     * Finds the sprite image for the character.
     */
    public abstract void getImage();

    public void setCharacterType(CharacterType _characterType) {
        this._characterType = _characterType;
    }

    public CharacterType getCharacterType() {
        return _characterType;
    }

    /**
     * Changes the health of the player.
     *
     * @param health - Character's total health
     */
    public void setHealth(int health) {
        if (health >= 0 && health <= 100) {
            this.health = health;
        } else {
            System.out.println("ERROR: Health must be between 0 and 100");
            System.exit(-1);
        }
    }

    /**
     * @returns player's current health.
     *
     */
    public int getHealth() {
        return health;
    }

    /**
     * @returns character's speed
     */
    public int getSpeed() {
        return _speed;
    }

    /**
     * Sets character's speed
     * @param _speed
     */
    public void setSpeed(int _speed) {
        this._speed = _speed;
    }

    /**
     *
     * @return sprite's body dimensions
     */
    public Rectangle getSpriteBody() {
        return spriteBody;
    }

    /** Set if the character is currently walking.
     *
     * @param walking - is the character in the middle of walking?
     */
    public void setWalking(boolean walking) {
        this._walking = walking;
    }

    /**
     *
     * @return if the character is currently walking
     */
    public boolean isWalking() {
        return _walking;
    }

    /**
     *
     * @return The number of steps that a character can take.
     * One step == half a tile
     */
    public int getStepsAllowed() {
        return _stepsAllowed;
    }

    /**
     * The number of steps that a character can take.
     * One step == half a tile
     * @param _stepsAllowed - The number of steps that a character can take.
     *
     */
    public void setStepsAllowed(int _stepsAllowed) {
        this._stepsAllowed = (_grid.getTileSize()-1)*_stepsAllowed;
    }

    /**
     * Adds an item from the character's bag.
     *
     * @param item - item to be added to character's bag
     */
    public void addToBag(Item item) {
        this.bag.add(item);
    }

    /**
     * Removes an item from the character's bag.
     *
     * @param item - item to be removed to character's bag
     */
    public void removeFromBag(Item item) {
        this.bag.remove(item);
    }

    /**
     * Changes the position of the player.
     *
     * @param x - X coordinate of character's starting position
     * @param y - Y coordinate of character's starting position
     */
    public void setPosition(int x, int y) {
        this.position[Constants.X] = x;
        this.position[Constants.Y] = y;
    }

    /**
     * @returns player's current position.
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Changes the initial position of the player.
     *
     * @param x - X coordinate of character's position
     * @param y - X coordinate of character's position
     */
    public void setStartState(int x, int y) {
        this.startState[Constants.X] = x;
        this.startState[Constants.Y] = y;
    }

    /**
     * @returns player's original position.
     */
    public int[] getStartState() {
        return startState;
    }

    /**
     * Changes the direction the player is facing on the map.
     *
     * @param directionFacing - The direcetion the character is currently facing
     */
    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }

    /**
     * @returns player's direction they are facing on the map.
     */
    public Direction getDirectionFacing() {
        return directionFacing;
    }

    /**
     * Sets player's previous direction they were facing on the map.
     *
     * @param previousDirectionFacing previous direction facing
     */
    public void setPreviousDirectionFacing(Direction previousDirectionFacing) {
        this.previousDirectionFacing = previousDirectionFacing;
    }

    /**
     * @returns player's previous direction they were facing on the map.
     */
    public Direction getPreviousDirectionFacing() {
        return previousDirectionFacing;
    }

    /**
     * Stores the characters next movement direction
     * @param nextMovement - The next direction the character will walk in
     */
    public void setNextMovement(Direction nextMovement) {
        this.nextMovement = nextMovement;
    }

    /**
     *
     * @return the character's next movement direction
     */
    public Direction getNextMovement() {
        return nextMovement;
    }

    /**
     * @returns player's sprite based on direction.
     */
    public BufferedImage getSprite(Direction direction) {
        if (direction == Direction.NORTH) {
            if (spriteCounter == 0) {
                return _spriteNorth0;
            } else if (spriteCounter == 1) {
                return _spriteNorth1;
            } else if (spriteCounter == 2) {
                return _spriteNorth2;
            }
        } else if (direction == Direction.SOUTH) {
            if (spriteCounter == 0) {
                return _spriteSouth0;
            } else if (spriteCounter == 2) {
                return _spriteSouth1;
            } else if (spriteCounter == 1) {
                return _spriteSouth2;
            }
        } else if (direction == Direction.EAST) {
            if (spriteCounter == 0) {
                return _spriteEast0;
            } else if (spriteCounter == 1) {
                return _spriteEast1;
            } else if (spriteCounter == 2) {
                return _spriteEast2;
            }
        } else if (direction == Direction.WEST) {
            if (spriteCounter == 0) {
                return _spriteWest0;
            } else if (spriteCounter == 1) {
                return _spriteWest1;
            } else if (spriteCounter == 2) {
                return _spriteWest2;
            }
        }
        return null;
    }

    /**
     * Assigns the player's sprite image based on direction.
     * @param direction direction
     * @param spriteNum number of sprites
     * @param sprite image of sprite
     */
    public void setSprite(Direction direction, int spriteNum, BufferedImage sprite) {
        if (direction == Direction.NORTH) {
            switch (spriteNum) {
                case 0:
                    this._spriteNorth0 = sprite;
                    break;
                case 1:
                    this._spriteNorth1 = sprite;
                    break;
                case 2:
                    this._spriteNorth2 = sprite;
                    break;
                default:
                    this._spriteNorth0 = sprite;
            }
        }
        else if (direction == Direction.SOUTH) {
            switch (spriteNum) {
                case 0:
                    this._spriteSouth0 = sprite;
                    break;
                case 1:
                    this._spriteSouth1 = sprite;
                    break;
                case 2:
                    this._spriteSouth2 = sprite;
                    break;
                default:
                    this._spriteSouth0 = sprite;
            }
        }
        else if (direction == Direction.EAST) {
            switch (spriteNum) {
                case 0:
                    this._spriteEast0 = sprite;
                    break;
                case 1:
                    this._spriteEast1 = sprite;
                    break;
                case 2:
                    this._spriteEast2 = sprite;
                    break;
                default:
                    this._spriteEast0 = sprite;
            }
        }
        else if (direction == Direction.WEST) {
            switch (spriteNum) {
                case 0:
                    this._spriteWest0 = sprite;
                    break;
                case 1:
                    this._spriteWest1 = sprite;
                    break;
                case 2:
                    this._spriteWest2 = sprite;
                    break;
                default:
                    this._spriteWest0 = sprite;
            }
        }
    }

    /**
     * Attempts to move the character's position on the map.
     * Always changes the player's direction facing.
     *
     * @param direction - Player has achieved the final boss award
     */
    public void moveCharacter(Direction direction) {

        setPreviousDirectionFacing(getDirectionFacing());
        if(isWalking() == false) {
            setWalking(true);

            // Walking thread
            CharacterMovementThread characterMovementThread = new CharacterMovementThread(_grid, level, this, direction);
            Thread clockThread = new Thread(characterMovementThread);
            clockThread.start(); // Calls this.run()
        }
    }

    /**
     * Moves character's position one tile north
     */
    public void moveNorth() {
        this.setPosition(getPosition()[Constants.X],getPosition()[Constants.Y] - _speed);
    }

    /**
     * Moves character's position one tile south
     */
    public void moveSouth() {
        this.setPosition(getPosition()[Constants.X],getPosition()[Constants.Y] + _speed);
    }

    /**
     * Moves character's position one tile east
     */
    public void moveEast() {
        this.setPosition(getPosition()[Constants.X] + _speed, getPosition()[Constants.Y]);
    }

    /**
     * Moves character's position one tile west
     */
    public void moveWest() {
        this.setPosition(getPosition()[Constants.X] - _speed, getPosition()[Constants.Y]);
    }
}

