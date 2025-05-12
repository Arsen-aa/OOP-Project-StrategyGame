package am.aua.StrategyGame.core;

/**
 * Represents a Horseman unit in the game.
 * Horsemen are strong in attack but weak in defense.
 */
public class Horseman extends Troop {

    /**
     * Creates a Horseman with set values for attack, defense, and cost.
     */
    public Horseman() {
        this.attackPower = 8;   // High attack power
        this.defensePower = 2;  // Low defense power
        this.cost = 15;         // Costs 15 gold to buy
    }

    /**
     * Returns the type of this troop.
     *
     * @return "Horseman" as the troop type
     */
    @Override
    public String getType() {
        return "Horseman";
    }
}
