package am.aua.StrategyGame.core;

/**
 * Represents a Knight unit in the game.
 * Knights have balanced attack and defense, making them a well-rounded troop.
 */
public class Knight extends Troop {

    /**
     * Creates a Knight with medium attack, defense, and a low cost.
     */
    public Knight() {
        this.attackPower = 5;   // Medium attack power
        this.defensePower = 5;  // Medium defense power
        this.cost = 10;         // Costs 10 gold to buy
    }

    /**
     * Returns the type of this troop.
     *
     * @return "Knight" as the troop type
     */
    @Override
    public String getType() {
        return "Knight";
    }
}
