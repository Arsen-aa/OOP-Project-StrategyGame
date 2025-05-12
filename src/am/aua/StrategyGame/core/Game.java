package am.aua.StrategyGame.core;

import java.io.IOException;

/**
 * Core Game controller that manages two countries and handles game logic such as saving, battles, and gold cycles.
 */
public class Game {

    /** The first country in the game */
    private Country country1;

    /** The second country in the game */
    private Country country2;

    /**
     * Creates a new game with two countries using the given player names.
     *
     * @param name1 the name of the first country
     * @param name2 the name of the second country
     */
    public Game(String name1, String name2) {
        this.country1 = new Country(name1);
        this.country2 = new Country(name2);
    }

    /**
     * Loads a saved game from a file.
     *
     * @param saveFile the file to load the game from
     * @throws IOException if loading the file fails
     */
    public Game(String saveFile) throws IOException {
        Country[] loaded = GameSaver.load(saveFile);
        this.country1 = loaded[0];
        this.country2 = loaded[1];
    }

    /**
     * Returns the first country.
     *
     * @return country1
     */
    public Country getCountry1() {
        return country1;
    }

    /**
     * Returns the second country.
     *
     * @return country2
     */
    public Country getCountry2() {
        return country2;
    }

    /**
     * Checks if the game is over (if one country's HP is 0 or less).
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return country1.getHp() <= 0 || country2.getHp() <= 0;
    }

    /**
     * Returns the name of the winning country, or null if the game is not over yet.
     *
     * @return winner's name or null
     */
    public String getWinner() {
        if (country1.getHp() <= 0) return country2.getName();
        if (country2.getHp() <= 0) return country1.getName();
        return null;
    }

    /**
     * Runs the next game cycle where both countries earn gold.
     */
    public void nextCycle() {
        country1.earnGold();
        country2.earnGold();
    }

    /**
     * Resolves a battle between the two countries.
     * The attacker and defender are handled inside the battle logic.
     */
    public void resolveBattle() {
        BattleResult.resolveBattle(country1, country2);
    }

    /**
     * Saves the current game state to a file.
     *
     * @param filename the name of the file to save to
     * @throws IOException if saving fails
     */
    public void saveGame(String filename) throws IOException {
        GameSaver.save(country1, country2, filename);
    }
}
