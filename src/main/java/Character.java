/**
 * Contains methods that are applicable to all npc and human players.
 */
public abstract class Character {

    private int health = 0;
    // Items object here
    private int[] position = new int[2];
    private int[] startState = new int[2];
    private Direction directionFacing;

    public Character(int[] startState, int health) {
        this.setStartState(startState);
        this.setPosition(startState);
        this.setHealth(health);
    }

    /**
     * Changes the health of the player.
     */
    public void setHealth(int health) {
        if (health >= 0 && health <= 100) {
            this.health = health;
        }
        else {
            System.out.println("ERROR: Health must be between 0 and 100");
            System.exit(-1);
        }
    }

    /**
     * Returns player's current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Changes the position of the player.
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * Returns player's current position.
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Changes the initial position of the player.
     */
    public void setStartState(int[] startState) {
        this.startState = startState;
    }

    /**
     * Returns player's original position.
     */
    public int[] getStartState() {
        return startState;
    }

    /**
     * Changes the direction the player is facing on the map.
     */
    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }

    /**
     * Returns player's direction they are facing on the map.
     */
    public Direction getDirectionFacing() {
        return directionFacing;
    }
}
