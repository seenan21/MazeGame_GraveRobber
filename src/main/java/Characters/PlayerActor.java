package Characters;

import Map.Grid;

/**
 * This PlayerActor refers to the main character controlled by the user. The player should be able to pickup items,
 * move north, south, east, and west, and have a set amount of health. The player can only be on one map at a time.
 *
 * The player will always spawn at the map's startCell.
 */
public class PlayerActor extends Character{

    private boolean hasBossReward;

    /**
     * Constructor for the character class.
     *
     * @param health - Character's total health
     * @param hasBossReward - Player has achieved the final boss award
     */
    public PlayerActor(int health, Direction directionFacing, Grid currentMap, boolean hasBossReward) {
        super(health, directionFacing, currentMap);
        this.setStartState(currentMap.getStartTile()[0], currentMap.getStartTile()[1]);
        this.setPosition(currentMap.getStartTile()[0], currentMap.getStartTile()[1]);
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
