package Characters;

public class PlayerActor extends Character{

    private boolean hasBossReward;

    /**
     * Constructor for the character class.
     *
     * @param positionX - X coordinate of character's position
     * @param positionY - Y coordinate of character's position
     * @param health    - Character's total health
     * @param hasBossReward - Player has achieved the final boss award
     */
    public PlayerActor(int positionX, int positionY, int health, Direction directionFacing, boolean hasBossReward) {
        super(positionX, positionY, health, directionFacing);
        this.setHasBossReward(hasBossReward);
    }

    /**
     * Changes the status of final boss award for the player
     *
     * @param hasBossReward - Player has achieved the final boss award
     */
    public void setHasBossReward(boolean hasBossReward) {
        this.hasBossReward = hasBossReward;
    }

    /**
     * Returns if the player has the final boss's reward
     */
    public boolean getHasBossReward() {
        return this.hasBossReward;
    }
}
