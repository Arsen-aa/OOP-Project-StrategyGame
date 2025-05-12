package am.aua.StrategyGame.cil;

import am.aua.StrategyGame.core.*;
import java.util.Scanner;

/**
 * Console-based game runner for a 2-player strategy game.
 * Players take turns buying troops, upgrading economy, and fighting until one country's HP drops to 0.
 */
public class GameConsole {

    /** First country/player in the game */
    private final Country country1;

    /** Second country/player in the game */
    private final Country country2;

    /** Scanner used to read user input from the console */
    private final Scanner scanner;

    /**
     * Constructs a new console game with two countries and initializes the scanner.
     */
    public GameConsole() {
        this.country1 = new Country("Country 1");
        this.country2 = new Country("Country 2");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts and runs the main game loop until one country loses all HP.
     */
    public void run() {
        System.out.println("== Welcome to the Console Strategy Game ==");
        int round = 1;

        while (country1.getHp() > 0 && country2.getHp() > 0) {
            System.out.println("\n>>> ROUND " + round);
            country1.earnGold();
            country2.earnGold();

            playerTurn(country1);
            playerTurn(country2);

            String result = BattleResult.resolveBattle(country1, country2);
            System.out.println(result);

            displayStatus();

            savePrompt();
            round++;
        }

        System.out.println("\n== GAME OVER ==");
        System.out.println((country1.getHp() <= 0 ? country2.getName() : country1.getName()) + " wins!");
    }

    /**
     * Handles a single player's turn where they can buy troops, upgrade, or skip.
     *
     * @param country the country (player) whose turn it is
     */
    private void playerTurn(Country country) {
        System.out.println("\n" + country.getName() + "'s turn:");
        System.out.println("Gold: " + country.getGold());
        System.out.println("1. Buy Knight (10 gold)");
        System.out.println("2. Buy Archer (12 gold)");
        System.out.println("3. Buy Horseman (15 gold)");
        System.out.println("4. Upgrade Economy");
        System.out.println("5. Skip");

        System.out.print("Choose action: ");
        String input = scanner.nextLine();

        try {
            switch (input) {
                case "1":
                    country.buyTroop(new Knight());
                    break;
                case "2":
                    country.buyTroop(new Archer());
                    break;
                case "3":
                    country.buyTroop(new Horseman());
                    break;
                case "4":
                    country.upgradeEconomy();
                    break;
                default:
                    System.out.println("Skipping turn.");
            }
        } catch (InvalidActionException e) {
            System.out.println("Action failed: " + e.getMessage());
        }
    }

    /**
     * Displays the current status (HP, Gold, Army Size) of both countries.
     */
    private void displayStatus() {
        System.out.println("\n--- STATUS ---");
        System.out.println(country1.getName() + ": HP = " + country1.getHp() + ", Gold = " + country1.getGold() + ", Army = " + country1.getArmy().size());
        System.out.println(country2.getName() + ": HP = " + country2.getHp() + ", Gold = " + country2.getGold() + ", Army = " + country2.getArmy().size());
    }

    /**
     * Prompts the user to save the game to a file. If 'y', attempts to save using GameSaver.
     */
    private void savePrompt() {
        System.out.print("Do you want to save the game? (y/n): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            try {
                GameSaver.save(country1, country2, "game_save.txt");
                System.out.println("Game saved to game_save.txt");
            } catch (Exception e) {
                System.out.println("Failed to save: " + e.getMessage());
            }
        }
    }

    /**
     * Main method to launch the console game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new GameConsole().run();
    }
}
