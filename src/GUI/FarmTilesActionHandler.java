/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.plaf.FontUIResource;

import main.*;

/**
 * Class that handles events related to the farmTiles
 */
public class FarmTilesActionHandler implements ActionListener {
    private int x; // An integer dictating the row of the farmTile
    private int y; // An integer dictating the column of the farmTile
    private ToolsUI toolsUI; // UI for the tools
    private SeedsUI seedsUI; // UI for the seeds
    private Farm farm; // The main farm object
    private Player farmer; // The main player object
    private Tools tools; // The main tools object
    private BackgroundImagePanel mainGamePanel; // Main JPanel for the game
    private MainUI gameUI; // The main UI for the game

    FarmTilesActionHandler(int x, int y, ToolsUI toolsUI, SeedsUI seedsUI, Farm farm, Player farmer, Tools tools,
            BackgroundImagePanel mainGamePanel, MainUI gameUI) {
        this.x = x;
        this.y = y;
        this.toolsUI = toolsUI;
        this.seedsUI = seedsUI;
        this.farm = farm;
        this.farmer = farmer;
        this.tools = tools;
        this.mainGamePanel = mainGamePanel;
        this.gameUI = gameUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tool_used = toolsUI.getToolBtnPressed();
        String seed_used = seedsUI.getSeedBtnPressed();
        String message;
        JFrame mainWindow = (JFrame) SwingUtilities.getWindowAncestor(mainGamePanel);
        JButton farmTileClicked = (JButton) e.getSource();
        if (tool_used.equals("") && !seed_used.equals("")) {
            // A seed is selected
            message = UserMethods.plant_seed(farmTileClicked, farmer, farm, seed_used, x, y);
        } else if (seed_used.equals("") && !tool_used.equals("")) {
            // A tool is selected
            message = this.tools.use_tool(farmTileClicked, tool_used, farmer, farm, x, y);
        } else {
            // The harvest button is selected
            message = UserMethods.harvest_crop(farmTileClicked, farmer, farm, x, y, gameUI.getFarmTiles());
        }

        // Create popup window to inform user of what happened when tool is used
        JDialog popup = new JDialog(mainWindow);
        popup.setBounds(600, 350, 550, 100);
        popup.setLocationRelativeTo(mainWindow);
        popup.setUndecorated(true);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setSize(250, 50);
        label.setFont(new FontUIResource("Tahoma", 0, 15));
        popup.add(label);

        popup.setVisible(true);
        this.gameUI.redrawMainGameUI();
        mainWindow.repaint();
        this.seedsUI.updateTooltips();

        // Use a timer to make JDialog automatically disappear
        Timer timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popup.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

}
