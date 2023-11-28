/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.FontUIResource;

/**
 * Class that handles the tool buttons in the mainUI
 */
public class ToolsUI {
    private BackgroundImagePanel mainGamePanel; // Main JPanel for the main game
    private String toolBtnPressed;// Dictates the current tool button clicked
    private JButton[][] farmTiles; // A 2D JButton array that holds all the clickable farm tiles in the game
    private JToggleButton plowButton; // Button for plow tool
    private JToggleButton wateringCanButton; // Button for watering can tool
    private JToggleButton fertilizerButton; // Button for fertilizer tool
    private JToggleButton pickaxeButton; // Button for pickaxe tool
    private JToggleButton shovelButton; // Button for shovel tool
    private SeedsUI seedsUI; // UI for the seeds

    ToolsUI(BackgroundImagePanel mainGamePanel, JButton[][] farmTiles) {
        this.mainGamePanel = mainGamePanel;
        this.toolBtnPressed = "";
        this.farmTiles = farmTiles;
    }

    /**
     * Method that setups the UI for the tools
     *
     * @param seedsUI // UI for the seeds
     */
    public void setup(SeedsUI seedsUI) {
        this.seedsUI = seedsUI;
        Icon plow = new ImageIcon("resources/Tools/tool_plow.png");
        Icon plow_pressed = new ImageIcon("resources/Tools/tool_plow_pressed.png");
        Icon wateringCan = new ImageIcon("resources/Tools/tool_watering-can.png");
        Icon wateringCan_pressed = new ImageIcon("resources/Tools/tool_watering-can_pressed.png");
        Icon pickaxe = new ImageIcon("resources/Tools/tool_pickaxe.png");
        Icon pickaxe_pressed = new ImageIcon("resources/Tools/tool_pickaxe_pressed.png");
        Icon fertilizer = new ImageIcon("resources/Tools/tool_fertilizer.png");
        Icon fertilizer_pressed = new ImageIcon("resources/Tools/tool_fertilizer_pressed.png");
        Icon shovel = new ImageIcon("resources/Tools/tool_shovel.png");
        Icon shovel_pressed = new ImageIcon("resources/Tools/tool_shovel_pressed.png");

        this.plowButton = new JToggleButton(plow);
        plowButton.setBackground(Color.BLACK);
        plowButton.setFont(new FontUIResource("Arial", 0, 10));
        plowButton.setBounds(40, 285, 50, 50);
        plowButton.setPressedIcon(plow_pressed);
        plowButton.setSelectedIcon(plow_pressed);
        plowButton.setToolTipText("Plow");
        plowButton.addActionListener(new ToolsActionHandler(this));
        plowButton.setActionCommand("Plow");

        this.wateringCanButton = new JToggleButton(wateringCan);
        wateringCanButton.setBackground(Color.BLACK);
        wateringCanButton.setFont(new FontUIResource("Arial", 0, 10));
        wateringCanButton.setBounds(100, 285, 50, 50);
        wateringCanButton.setToolTipText("Watering Can");
        wateringCanButton.setPressedIcon(wateringCan_pressed);
        wateringCanButton.setSelectedIcon(wateringCan_pressed);
        wateringCanButton.addActionListener(new ToolsActionHandler(this));
        wateringCanButton.setActionCommand("Watering Can");

        this.fertilizerButton = new JToggleButton(fertilizer);
        fertilizerButton.setBackground(Color.BLACK);
        fertilizerButton.setFont(new FontUIResource("Arial", 0, 10));
        fertilizerButton.setBounds(40, 340, 50, 50);
        fertilizerButton.setToolTipText("Fertilizer");
        fertilizerButton.setPressedIcon(fertilizer_pressed);
        fertilizerButton.setSelectedIcon(fertilizer_pressed);
        fertilizerButton.addActionListener(new ToolsActionHandler(this));
        fertilizerButton.setActionCommand("Fertilizer");

        this.pickaxeButton = new JToggleButton(pickaxe);
        pickaxeButton.setBackground(Color.BLACK);
        pickaxeButton.setFont(new FontUIResource("Arial", 0, 10));
        pickaxeButton.setBounds(100, 340, 50, 50);
        pickaxeButton.setToolTipText("Pickaxe");
        pickaxeButton.setPressedIcon(pickaxe_pressed);
        pickaxeButton.setSelectedIcon(pickaxe_pressed);
        pickaxeButton.addActionListener(new ToolsActionHandler(this));
        pickaxeButton.setActionCommand("Pickaxe");

        this.shovelButton = new JToggleButton(shovel);
        shovelButton.setBackground(Color.BLACK);
        shovelButton.setFont(new FontUIResource("Arial", 0, 10));
        shovelButton.setBounds(70, 395, 50, 50);
        shovelButton.setToolTipText("Shovel");
        shovelButton.setPressedIcon(shovel_pressed);
        shovelButton.setSelectedIcon(shovel_pressed);
        shovelButton.addActionListener(new ToolsActionHandler(this));
        shovelButton.setActionCommand("Shovel");

        this.mainGamePanel.add(plowButton);
        this.mainGamePanel.add(wateringCanButton);
        this.mainGamePanel.add(fertilizerButton);
        this.mainGamePanel.add(pickaxeButton);
        this.mainGamePanel.add(shovelButton);
    }

    /**
     * Method that gets the variable assigned to toolBtnPressed
     * 
     * @return dictates the current tool button clicked
     */
    public String getToolBtnPressed() {
        return toolBtnPressed;
    }

    /**
     * Method that gets the plow button
     * 
     * @return button for the plow button
     */
    public JToggleButton getPlowButton() {
        return plowButton;
    }

    /**
     * Method that gets the pickaxe button
     * 
     * @return button for the pickaxe tools
     */
    public JToggleButton getPickaxeButton() {
        return pickaxeButton;
    }

    /**
     * Method that gets the shovel button
     * 
     * @return button for the shovel tool
     */
    public JToggleButton getShovelButton() {
        return shovelButton;
    }

    /**
     * Method that gets the watering can button
     * 
     * @return button for the watering can tool
     */
    public JToggleButton getWateringCanButton() {
        return wateringCanButton;
    }

    /**
     * Method that gets the fertillizer button
     * 
     * @return button for the fertillizer tool
     */
    public JToggleButton getFertilizerButton() {
        return fertilizerButton;
    }

    /**
     * Method that gets the farmTiles
     * 
     * @return A 2D JButton array that holds all the clickable farm tiles in the
     *         game
     */
    public JButton[][] getFarmTiles() {
        return farmTiles;
    }

    /**
     * Method that gets the seedsUI
     * 
     * @return UI for the seeds buttons
     */
    public SeedsUI getSeedsUI() {
        return seedsUI;
    }

    /**
     * Method that sets the value for toolBtnPressed
     * 
     * @param seedBtnPressed dictates the current tool button clicked
     */
    public void setToolBtnPressed(String toolBtnPressed) {
        this.toolBtnPressed = toolBtnPressed;
    }

    public void untoggleButtons() {
        if (this.toolBtnPressed.equals("")) {
            this.plowButton.setSelected(false);
            this.wateringCanButton.setSelected(false);
            this.pickaxeButton.setSelected(false);
            this.shovelButton.setSelected(false);
            this.fertilizerButton.setSelected(false);
        }
        if (this.toolBtnPressed.equals("Plow")) {
            this.wateringCanButton.setSelected(false);
            this.pickaxeButton.setSelected(false);
            this.shovelButton.setSelected(false);
            this.fertilizerButton.setSelected(false);
        }
        if (this.toolBtnPressed.equals("Watering Can")) {
            this.plowButton.setSelected(false);
            this.pickaxeButton.setSelected(false);
            this.shovelButton.setSelected(false);
            this.fertilizerButton.setSelected(false);
        }
        if (this.toolBtnPressed.equals("Pickaxe")) {
            this.wateringCanButton.setSelected(false);
            this.plowButton.setSelected(false);
            this.shovelButton.setSelected(false);
            this.fertilizerButton.setSelected(false);
        }
        if (this.toolBtnPressed.equals("Shovel")) {
            this.wateringCanButton.setSelected(false);
            this.pickaxeButton.setSelected(false);
            this.plowButton.setSelected(false);
            this.fertilizerButton.setSelected(false);
        }
        if (this.toolBtnPressed.equals("Fertilizer")) {
            this.wateringCanButton.setSelected(false);
            this.pickaxeButton.setSelected(false);
            this.shovelButton.setSelected(false);
            this.plowButton.setSelected(false);
        }
    }
}
