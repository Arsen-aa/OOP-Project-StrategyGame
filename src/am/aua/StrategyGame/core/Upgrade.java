package am.aua.StrategyGame.core;

/**
 * Represents an economic upgrade system for a country.
 * Upgrades increase gold income per cycle and get more expensive with each level.
 */
public class Upgrade {

    /** Current upgrade level */
    private int level;

    /** Base cost for upgrading (multiplied by level) */
    private final int baseCost = 50;

    /**
     * Creates a new upgrade starting at level 1.
     */
    public Upgrade() {
        this.level = 1;
    }

    /**
     * Gets the current upgrade level.
     *
     * @return current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Calculates the amount of gold earned per cycle based on level.
     *
     * @return gold earned each cycle
     */
    public int getGoldPerCycle() {
        return level * 10;
    }

    /**
     * Calculates the cost required to upgrade to the next level.
     *
     * @return the gold cost to upgrade
     */
    public int getUpgradeCost() {
        return baseCost * level;
    }

    /**
     * Upgrades to the next level, increasing gold income and upgrade cost.
     */
    public void upgrade() {
        level++;
    }
}
