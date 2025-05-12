package am.aua.StrategyGame.core;

/**
 * Helper class for resolving battles between two countries.
 */
public class BattleResult {

    /**
     * Resolves a battle between an attacking and a defending country.
     * Compares total attack and defense values to determine the winner.
     * The loser takes damage, and the attacker's army is cleared after battle.
     *
     * @param attacker the country that initiates the battle
     * @param defender the country that defends
     * @return a message indicating the result of the battle and HP lost
     */
    public static String resolveBattle(Country attacker, Country defender) {
        int atk = attacker.getTotalAttack();   // Total attack power of the attacking country's army
        int def = defender.getTotalDefense();  // Total defense power of the defending country's army

        if (atk > def) {
            int damage = atk - def;
            defender.takeDamage(damage);       // Defender loses HP
            attacker.clearArmy();              // Attacker's army is used up
            return attacker.getName() + " wins the battle! " + defender.getName() + " loses " + damage + " HP.";
        } else {
            int damage = def - atk;
            attacker.takeDamage(damage);       // Attacker loses HP if defense is stronger
            attacker.clearArmy();              // Attacker's army is still used up
            return defender.getName() + " defends successfully! " + attacker.getName() + " loses " + damage + " HP.";
        }
    }
}
