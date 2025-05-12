package am.aua.StrategyGame.ui;

import am.aua.StrategyGame.core.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Swing panel that displays and allows interaction with a single country's stats, economy, and army.
 */
public class CountryPanel extends JPanel {

    /** The country this panel represents */
    private final Country country;

    /** Label showing the country's HP */
    private final JLabel hpLabel;

    /** Label showing the country's gold and upgrade level */
    private final JLabel goldLabel;

    /** Area displaying a list of troops in the army */
    private final JTextArea armyArea;

    /**
     * Creates a panel for the given country.
     * Adds buttons for buying troops and upgrading economy, and displays current status.
     *
     * @param country the country to manage in this panel
     */
    public CountryPanel(Country country) {
        this.country = country;

        // Layout and border
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(country.getName()));

        // Initialize UI components
        hpLabel = new JLabel();
        goldLabel = new JLabel();
        armyArea = new JTextArea(5, 20);
        armyArea.setEditable(false);

        // Buy Knight button
        JButton knightButton = new JButton("Buy Knight (10)");
        knightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyTroop(new Knight());
            }
        });

        // Buy Archer button
        JButton archerButton = new JButton("Buy Archer (12)");
        archerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyTroop(new Archer());
            }
        });

        // Buy Horseman button
        JButton horsemanButton = new JButton("Buy Horseman (15)");
        horsemanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyTroop(new Horseman());
            }
        });

        // Upgrade Economy button
        JButton upgradeButton = new JButton("Upgrade Economy");
        upgradeButton.addActionListener(this::upgrade);

        // Add components to the panel
        add(hpLabel);
        add(goldLabel);
        add(knightButton);
        add(archerButton);
        add(horsemanButton);
        add(upgradeButton);
        add(new JScrollPane(armyArea));

        // Display initial state
        refresh();
    }

    /**
     * Attempts to buy the given troop and updates the display.
     * If not enough gold, shows an error message.
     *
     * @param troop the troop to buy
     */
    private void buyTroop(Troop troop) {
        try {
            country.buyTroop(troop);
        } catch (InvalidActionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        refresh();
    }

    /**
     * Attempts to upgrade the country's economy.
     * If not enough gold, shows an error message.
     *
     * @param e the action event (button click)
     */
    private void upgrade(ActionEvent e) {
        try {
            country.upgradeEconomy();
        } catch (InvalidActionException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        refresh();
    }

    /**
     * Updates the display with the latest country stats and army list.
     */
    public void refresh() {
        hpLabel.setText("HP: " + country.getHp());
        goldLabel.setText("Gold: " + country.getGold() + " | Upgrade Level: " + country.getUpgradeLevel());
        armyArea.setText("");
        for (Troop t : country.getArmy()) {
            armyArea.append(t.toString() + "\n");
        }
    }
}
