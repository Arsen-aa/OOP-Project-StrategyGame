package am.aua.StrategyGame.core;

/**
 * Abstract class for all troop types in the game.
 * Each troop has attack power, defense power, and a cost.
 * Specific troop types (Knight, Archer, Horseman) extend this class.
 */
public abstract class Troop {

    /** Attack power of the troop */
    protected int attackPower;

    /** Defense power of the troop */
    protected int defensePower;

    /** Cost to buy the troop in gold */
    protected int cost;

    /**
     * Gets the troop's attack power.
     *
     * @return attack power value
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Gets the troop's defense power.
     *
     * @return defense power value
     */
    public int getDefensePower() {
        return defensePower;
    }

    /**
     * Gets the cost of the troop in gold.
     *
     * @return troop cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the type/name of the troop (e.g., "Knight", "Archer").
     *
     * @return the name of the troop type
     */
    public abstract String getType();

    /**
     * Returns a string with the troop's type, attack, defense, and cost.
     *
     * @return readable string showing troop stats
     */
    @Override
    public String toString() {
        return getType() + " [ATK: " + attackPower + ", DEF: " + defensePower + ", Cost: " + cost + "]";
    }
}
