package am.aua.StrategyGame.ui;

import am.aua.StrategyGame.core.*;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * Main graphical user interface (GUI) for the strategy game.
 * Displays both countries, handles attacks, and runs gold generation automatically.
 */
public class GameGUI extends JFrame {

    /** First country (player 1) */
    private final Country country1;

    /** Second country (player 2) */
    private final Country country2;

    /** Text area to show game events like battles and saves */
    private final JTextArea logArea;

    /** Panel showing the first country's status and controls */
    private final CountryPanel panel1;

    /** Panel showing the second country's status and controls */
    private final CountryPanel panel2;

    /**
     * Constructs the main game window.
     * Initializes GUI components, event listeners, and a gold generation timer.
     */
    public GameGUI() {
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        setTitle("Strategy Game");
        setSize(900, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create panels for each country
        panel1 = new CountryPanel(country1);
        panel2 = new CountryPanel(country2);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(panel1);
        centerPanel.add(panel2);

        // Text area for event logs
        logArea = new JTextArea(6, 80);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        // Button to trigger a battle
        JButton attackButton = new JButton("Fight!");
        attackButton.addActionListener(e -> handleAttack());

        // Button to save the game
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(e -> {
            try {
                GameSaver.save(country1, country2, "game_save.txt");
                log("Game saved.");
            } catch (IOException ex) {
                log("Error saving game.");
            }
        });

        // Add buttons to top panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(attackButton);
        bottomPanel.add(saveButton);

        // Add components to the main frame
        add(centerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.NORTH);

        // Timer that adds gold to both countries every 10 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                country1.earnGold();
                country2.earnGold();
                panel1.refresh();
                panel2.refresh();
            }
        }, 0, 10000); // every 10 seconds

        setVisible(true);
    }

    /**
     * Handles the logic when the "Fight!" button is clicked.
     * Resolves the battle, updates the display, and checks for a winner.
     */
    private void handleAttack() {
        String result = BattleResult.resolveBattle(country1, country2);
        log(result);
        panel1.refresh();
        panel2.refresh();

        if (country1.getHp() <= 0 || country2.getHp() <= 0) {
            log("Game Over! Winner: " + (country1.getHp() > 0 ? country1.getName() : country2.getName()));
        }
    }

    /**
     * Appends a line of text to the log area.
     *
     * @param text the message to log
     */
    private void log(String text) {
        logArea.append(text + "\n");
    }

    /**
     * Launches the game by opening the GUI window on the Swing thread.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI();
            }
        });
    }
}
