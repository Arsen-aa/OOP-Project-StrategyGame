package am.aua.StrategyGame.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a country in the strategy game.
 * A country has a name, health points (HP), gold, an army of troops, and an upgradeable economy.
 */
public class Country {

    /** The name of the country */
    private final String name;

    /** The current health points (HP) of the country */
    private int hp;

    /** The amount of gold the country currently has */
    private int gold;

    /** The army of troops owned by the country */
    private final List<Troop> army;

    /** The economic upgrade system of the country */
    private final Upgrade upgrade;

    /**
     * Creates a new country with default HP, gold, and empty army.
     *
     * @param name the name of the country
     */
    public Country(String name) {
        this.name = name;
        this.hp = 100;
        this.gold = 100;
        this.army = new ArrayList<>();
        this.upgrade = new Upgrade();
    }

    /**
     * Increases the country's gold based on its upgrade level.
     */
    public void earnGold() {
        gold += upgrade.getGoldPerCycle();
    }

    /**
     * Buys a troop if enough gold is available.
     *
     * @param troop the troop to buy
     * @throws InvalidActionException if not enough gold
     */
    public void buyTroop(Troop troop) throws InvalidActionException {
        if (gold < troop.getCost()) {
            throw new InvalidActionException("Not enough gold to buy " + troop.getType());
        }
        army.add(troop);
        gold -= troop.getCost();
    }

    /**
     * Upgrades the economy if enough gold is available.
     *
     * @throws InvalidActionException if not enough gold
     */
    public void upgradeEconomy() throws InvalidActionException {
        int cost = upgrade.getUpgradeCost();
        if (gold < cost) {
            throw new InvalidActionException("Not enough gold to upgrade economy");
        }
        gold -= cost;
        upgrade.upgrade();
    }

    /**
     * Calculates and returns the total attack power of the army.
     *
     * @return total attack power
     */
    public int getTotalAttack() {
        return army.stream().mapToInt(Troop::getAttackPower).sum();
    }

    /**
     * Calculates and returns the total defense power of the army.
     *
     * @return total defense power
     */
    public int getTotalDefense() {
        return army.stream().mapToInt(Troop::getDefensePower).sum();
    }

    /**
     * Reduces the country's HP by the given amount.
     * HP cannot go below 0.
     *
     * @param amount the amount of damage to take
     */
    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    /**
     * Clears all troops from the army.
     */
    public void clearArmy() {
        army.clear();
    }

    /**
     * Gets the country's name.
     *
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current HP of the country.
     *
     * @return current health points
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the current gold of the country.
     *
     * @return current amount of gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Returns a copy of the army list to prevent external modification.
     *
     * @return a copy of the army list
     */
    public List<Troop> getArmy() {
        return new ArrayList<>(army); // Return copy to avoid privacy leaks
    }

    /**
     * Gets the current upgrade level of the country's economy.
     *
     * @return upgrade level
     */
    public int getUpgradeLevel() {
        return upgrade.getLevel();
    }
}
