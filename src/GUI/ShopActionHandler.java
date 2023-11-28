/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Player;
import main.UserMethods;

/**
 * Class responsible for handling events related to the shopFrame
 */
public class ShopActionHandler implements ActionListener {
    private Player farmer; // The mian player object
    private int cost; // Cost of the seeds
    private MainUI gameUI; // Main UI for the game
    private JFrame shopFrame; // UI for the shop
    private SeedsUI seedsUI; // UI for the seeds

    /**
     * Construct the ShopActionHandler by passing farmer, cost, gameUI, shopFrame,
     * and seedsUI
     * 
     * @param farmer    // Main player object
     * @param cost      // Cost of the seeds
     * @param gameUI    // Main UI for the game
     * @param shopFrame // UI for the shop
     * @param seedsUI   // UI for the seeds
     */
    ShopActionHandler(Player farmer, int cost, MainUI gameUI, JFrame shopFrame, SeedsUI seedsUI) {
        this.farmer = farmer;
        this.cost = cost;
        this.gameUI = gameUI;
        this.shopFrame = shopFrame;
        this.seedsUI = seedsUI;
    }

    /*
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String seed = e.getActionCommand(); // The seed clicked by the player
        String message;
        message = UserMethods.buy_seeds(this.farmer, this.cost, seed); // String message indicating what happened with
                                                                       // the purchase

        JDialog popup = new JDialog(shopFrame);
        popup.setBounds(600, 350, 350, 100);
        popup.setLocationRelativeTo(shopFrame);
        popup.setUndecorated(true);

        // Shows the message on the JDialog
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setSize(250, 50);
        popup.add(label);

        popup.setVisible(true);
        this.gameUI.redrawMainGameUI();
        shopFrame.repaint();
        this.seedsUI.updateTooltips();

        // Use a timer to make JDialog automatically disappear
        Timer timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popup.dispose();
                shopFrame.repaint();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}
