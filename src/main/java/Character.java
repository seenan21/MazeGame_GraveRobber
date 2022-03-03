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

    public void setHealth(int health) {
        if (health >= 0 && health <= 100) {
            this.health = health;
        }
        else {
            System.out.println("ERROR: Health must be between 0 and 100");
            System.exit(-1);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setStartState(int[] startState) {
        this.startState = startState;
    }

    public int[] getStartState() {
        return startState;
    }

    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }

    public Direction getDirectionFacing() {
        return directionFacing;
    }
}
