package am.aua.StrategyGame.core;

import java.io.*;

/**
 * Utility class for saving and loading the game state to and from a file.
 * Uses "%%" as a delimiter to separate fields in the save file.
 */
public class GameSaver {

    /**
     * Saves the current state of both countries to a file.
     *
     * @param c1       the first country
     * @param c2       the second country
     * @param filename the name of the file to save to
     * @throws IOException if writing to the file fails
     */
    public static void save(Country c1, Country c2, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(serialize(c1));  // Save country 1 data
            writer.println(serialize(c2));  // Save country 2 data
        }
    }

    /**
     * Converts a country object into a string for saving.
     * Format: name%%hp%%gold%%upgradeLevel%%troop1,troop2,...
     *
     * @param c the country to serialize
     * @return a string representation of the country
     */
    private static String serialize(Country c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c.getName()).append("%%")
          .append(c.getHp()).append("%%")
          .append(c.getGold()).append("%%")
          .append(c.getUpgradeLevel()).append("%%");
        for (Troop t : c.getArmy()) {
            sb.append(t.getType()).append(",");
        }
        return sb.toString();
    }

    /**
     * Loads two countries from the specified save file.
     *
     * @param filename the name of the save file
     * @return an array containing the two loaded countries
     * @throws IOException if reading the file fails
     */
    public static Country[] load(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Country c1 = deserialize(reader.readLine());  // Load first country
            Country c2 = deserialize(reader.readLine());  // Load second country
            return new Country[]{c1, c2};
        }
    }

    /**
     * Reconstructs a country from its saved string format.
     *
     * @param line the line from the save file
     * @return the reconstructed Country object
     */
    private static Country deserialize(String line) {
        String[] parts = line.split("%%");
        Country c = new Country(parts[0]);

        // Restore HP
        c.takeDamage(100 - Integer.parseInt(parts[1]));

        try {
            // Restore gold by simulating gold earnings
            int gold = Integer.parseInt(parts[2]);
            while (c.getGold() < gold) c.earnGold();

            // Restore upgrade level
            int upgrades = Integer.parseInt(parts[3]) - 1;
            for (int i = 0; i < upgrades; i++) {
                c.upgradeEconomy();
            }
        } catch (InvalidActionException ignored) {
            // Ignore any upgrade exceptions (like not enough gold)
        }

        // Restore troops
        if (parts.length > 4) {
            String[] troops = parts[4].split(",");
            for (String t : troops) {
                if (t.equals("Knight")) c.getArmy().add(new Knight());
                else if (t.equals("Archer")) c.getArmy().add(new Archer());
                else if (t.equals("Horseman")) c.getArmy().add(new Horseman());
            }
        }

        return c;
    }
}
