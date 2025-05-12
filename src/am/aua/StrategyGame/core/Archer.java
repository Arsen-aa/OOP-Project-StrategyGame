package am.aua.StrategyGame.core;

/**
 * Represents an Archer unit in the game.
 * Archers are good at defense but have lower attack power.
 */
public class Archer extends Troop {

    /**
     * Constructs an Archer with preset attack, defense, and cost values.
     */
    public Archer() {
        this.attackPower = 3;   // Low attack power
        this.defensePower = 7;  // High defense power
        this.cost = 12;         // Costs 12 gold to buy
    }

    /**
     * Returns the type of the troop.
     *
     * @return "Archer" as the type of this troop.
     */
    @Override
    public String getType() {
        return "Archer";
    }
}
