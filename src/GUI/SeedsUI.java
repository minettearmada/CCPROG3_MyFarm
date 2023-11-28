/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import main.Player;

/**
 * Class that handles the seed buttons in the mainUI
 */
public class SeedsUI {
    private BackgroundImagePanel mainGamePanel; // Main JPanel for the main game
    private String seedBtnPressed; // Dictates the current seed button clicked
    private JButton[][] farmTiles; // A 2D JButton array that holds all the clickable farm tiles in the game
    private JToggleButton turnipButton; // Button for the turnip seed
    private JToggleButton carrotButton; // Button for the carrot seed
    private JToggleButton potatoButton; // Button for the potato seed
    private JToggleButton roseButton; // Button for the rose seed
    private JToggleButton tulipsButton; // Button for the tulips seed
    private JToggleButton sunflowerButton; // Button for the sunflower seed
    private JToggleButton mangoButton; // Button for the mango seed
    private JToggleButton appleButton;// Button for the apple seed
    private ToolsUI toolsUI; // UI for the tool buttons
    private Player farmer; // The main player object

    SeedsUI(BackgroundImagePanel mainGamePanel, JButton[][] farmTiles) {
        this.mainGamePanel = mainGamePanel;
        this.seedBtnPressed = "";
        this.farmTiles = farmTiles;
    }

    /**
     * Method that setups the UI for the the seeds
     * 
     * @param toolsUI // UI for the tools
     * @param farmer  // The main player object
     */
    public void setup(ToolsUI toolsUI, Player farmer) {
        this.toolsUI = toolsUI;
        this.farmer = farmer;
        Icon turnip = new ImageIcon("resources/SeedIcon/TurnipIcon.png");
        Icon turnip_pressed = new ImageIcon("resources/SeedIcon/TurnipIconPressed.png");
        Icon carrot = new ImageIcon("resources/SeedIcon/CarrotIcon.png");
        Icon carrot_pressed = new ImageIcon("resources/SeedIcon/CarrotIconPressed.png");
        Icon potato = new ImageIcon("resources/SeedIcon/PotatoIcon.png");
        Icon potato_pressed = new ImageIcon("resources/SeedIcon/PotatoIconPressed.png");
        Icon rose = new ImageIcon("resources/SeedIcon/RoseIcon.png");
        Icon rose_pressed = new ImageIcon("resources/SeedIcon/RoseIconPressed.png");
        Icon tulips = new ImageIcon("resources/SeedIcon/TulipsIcon.png");
        Icon tulips_pressed = new ImageIcon("resources/SeedIcon/TulipsIconPressed.png");
        Icon sunflower = new ImageIcon("resources/SeedIcon/SunflowerIcon.png");
        Icon sunflower_pressed = new ImageIcon("resources/SeedIcon/SunflowerIconPressed.png");
        Icon mango = new ImageIcon("resources/SeedIcon/MangoIcon.png");
        Icon mango_pressed = new ImageIcon("resources/SeedIcon/MangoIconPressed.png");
        Icon apple = new ImageIcon("resources/SeedIcon/AppleIcon.png");
        Icon apple_pressed = new ImageIcon("resources/SeedIcon/AppleIconPressed.png");

        this.turnipButton = new JToggleButton(turnip);
        turnipButton.setOpaque(false);
        turnipButton.setContentAreaFilled(false);
        turnipButton.setBorderPainted(false);
        turnipButton.setFont(new FontUIResource("Arial", 0, 10));
        turnipButton.setBounds(250, 655, 80, 80);
        turnipButton.setPressedIcon(turnip_pressed);
        turnipButton.setSelectedIcon(turnip_pressed);
        turnipButton
                .setToolTipText("<html>Turnip Seed<br>Owned: " + farmer.get_seedInventory().get("Turnip") + "</html>");
        turnipButton.addActionListener(new SeedsActionHandler(this));
        turnipButton.setActionCommand("Turnip");

        this.carrotButton = new JToggleButton(carrot);
        carrotButton.setOpaque(false);
        carrotButton.setContentAreaFilled(false);
        carrotButton.setBorderPainted(false);
        carrotButton.setFont(new FontUIResource("Arial", 0, 10));
        carrotButton.setBounds(350, 655, 80, 80);
        carrotButton.setPressedIcon(carrot_pressed);
        carrotButton.setSelectedIcon(carrot_pressed);
        carrotButton
                .setToolTipText("<html>Carrot Seed<br>Owned: " + farmer.get_seedInventory().get("Carrot") + "</html>");
        carrotButton.addActionListener(new SeedsActionHandler(this));
        carrotButton.setActionCommand("Carrot");

        this.potatoButton = new JToggleButton(potato);
        potatoButton.setOpaque(false);
        potatoButton.setContentAreaFilled(false);
        potatoButton.setBorderPainted(false);
        potatoButton.setFont(new FontUIResource("Arial", 0, 10));
        potatoButton.setBounds(450, 655, 80, 80);
        potatoButton.setPressedIcon(potato_pressed);
        potatoButton.setSelectedIcon(potato_pressed);
        potatoButton
                .setToolTipText("<html>Potato Seed<br>Owned: " + farmer.get_seedInventory().get("Potato") + "</html>");
        potatoButton.addActionListener(new SeedsActionHandler(this));
        potatoButton.setActionCommand("Potato");

        this.roseButton = new JToggleButton(rose);
        roseButton.setOpaque(false);
        roseButton.setContentAreaFilled(false);
        roseButton.setBorderPainted(false);
        roseButton.setFont(new FontUIResource("Arial", 0, 10));
        roseButton.setBounds(550, 655, 80, 80);
        roseButton.setPressedIcon(rose_pressed);
        roseButton.setSelectedIcon(rose_pressed);
        roseButton.setToolTipText("<html>Rose Seed<br>Owned: " + farmer.get_seedInventory().get("Rose") + "</html>");
        roseButton.addActionListener(new SeedsActionHandler(this));
        roseButton.setActionCommand("Rose");

        this.tulipsButton = new JToggleButton(tulips);
        tulipsButton.setOpaque(false);
        tulipsButton.setContentAreaFilled(false);
        tulipsButton.setBorderPainted(false);
        tulipsButton.setFont(new FontUIResource("Arial", 0, 10));
        tulipsButton.setBounds(650, 655, 80, 80);
        tulipsButton.setPressedIcon(tulips_pressed);
        tulipsButton.setSelectedIcon(tulips_pressed);
        tulipsButton
                .setToolTipText("<html>Tulips Seed<br>Owned: " + farmer.get_seedInventory().get("Tulips") + "</html>");
        tulipsButton.addActionListener(new SeedsActionHandler(this));
        tulipsButton.setActionCommand("Tulips");

        this.sunflowerButton = new JToggleButton(sunflower);
        sunflowerButton.setOpaque(false);
        sunflowerButton.setContentAreaFilled(false);
        sunflowerButton.setBorderPainted(false);
        sunflowerButton.setFont(new FontUIResource("Arial", 0, 10));
        sunflowerButton.setBounds(750, 655, 80, 80);
        sunflowerButton.setPressedIcon(sunflower_pressed);
        sunflowerButton.setSelectedIcon(sunflower_pressed);
        sunflowerButton
                .setToolTipText(
                        "<html>Sunflower Seed<br>Owned: " + farmer.get_seedInventory().get("Sunflower") + "</html>");
        sunflowerButton.addActionListener(new SeedsActionHandler(this));
        sunflowerButton.setActionCommand("Sunflower");

        this.mangoButton = new JToggleButton(mango);
        mangoButton.setOpaque(false);
        mangoButton.setContentAreaFilled(false);
        mangoButton.setBorderPainted(false);
        mangoButton.setFont(new FontUIResource("Arial", 0, 10));
        mangoButton.setBounds(850, 655, 80, 80);
        mangoButton.setPressedIcon(mango_pressed);
        mangoButton.setSelectedIcon(mango_pressed);
        mangoButton.setToolTipText("<html>Mango Seed<br>Owned: " + farmer.get_seedInventory().get("Mango") + "</html>");
        mangoButton.addActionListener(new SeedsActionHandler(this));
        mangoButton.setActionCommand("Mango");

        this.appleButton = new JToggleButton(apple);
        appleButton.setOpaque(false);
        appleButton.setContentAreaFilled(false);
        appleButton.setBorderPainted(false);
        appleButton.setFont(new FontUIResource("Arial", 0, 10));
        appleButton.setBounds(950, 655, 80, 80);
        appleButton.setPressedIcon(apple_pressed);
        appleButton.setSelectedIcon(apple_pressed);
        appleButton.setToolTipText("<html>Apple Seed<br>Owned: " + farmer.get_seedInventory().get("Apple") + "</html>");
        appleButton.addActionListener(new SeedsActionHandler(this));
        appleButton.setActionCommand("Apple");

        this.mainGamePanel.add(turnipButton);
        this.mainGamePanel.add(carrotButton);
        this.mainGamePanel.add(potatoButton);
        this.mainGamePanel.add(roseButton);
        this.mainGamePanel.add(tulipsButton);
        this.mainGamePanel.add(sunflowerButton);
        this.mainGamePanel.add(mangoButton);
        this.mainGamePanel.add(appleButton);
    }

    /**
     * Method that gets the variable assigned to seedBtnPressed
     * 
     * @return dictates the current seed button clicked
     */
    public String getSeedBtnPressed() {
        return seedBtnPressed;
    }

    /**
     * Method that gets the turnip button
     * 
     * @return button for the turnip seed
     */
    public JToggleButton getTurnipButton() {
        return turnipButton;
    }

    /**
     * Method that gets the carrot button
     * 
     * @return button for the carrot seed
     */
    public JToggleButton getCarrotButton() {
        return carrotButton;
    }

    /**
     * Method that gets the potato button
     * 
     * @return button for the potato seed
     */
    public JToggleButton getPotatoButton() {
        return potatoButton;
    }

    /**
     * Method that gets the rose button
     * 
     * @return button for the rose seed
     */
    public JToggleButton getRoseButton() {
        return roseButton;
    }

    /**
     * Method that gets the tulips button
     * 
     * @return button for the tulips seed
     */
    public JToggleButton getTulipsButton() {
        return tulipsButton;
    }

    /**
     * Method that gets the sunflower button
     * 
     * @return button for the sunflower seed
     */
    public JToggleButton getSunflowerButton() {
        return sunflowerButton;
    }

    /**
     * Method that gets the mango button
     * 
     * @return button for the mango seed
     */
    public JToggleButton getMangoButton() {
        return mangoButton;
    }

    /**
     * Method that gets the apple button
     * 
     * @return button for the apple seed
     */
    public JToggleButton getAppleButton() {
        return appleButton;
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
     * Method that gets the toolsUI
     * 
     * @return UI for the tool buttons
     */
    public ToolsUI getToolsUI() {
        return toolsUI;
    }

    /**
     * Method that sets the value for seedBtnPressed
     * 
     * @param seedBtnPressed dictates the current seed button clicked
     */
    public void setSeedBtnPressed(String seedBtnPressed) {
        this.seedBtnPressed = seedBtnPressed;
    }

    /**
     * Method to switch to false the setSelected attribute of the seeds button
     * according to the seedBtnPressed attribute
     */
    public void untoggleButtons() {
        if (this.seedBtnPressed.equals("")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Turnip")) {
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Carrot")) {
            this.turnipButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Potato")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Rose")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Tulips")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Sunflower")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.mangoButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Mango")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.appleButton.setSelected(false);
        }
        if (this.seedBtnPressed.equals("Apple")) {
            this.turnipButton.setSelected(false);
            this.carrotButton.setSelected(false);
            this.potatoButton.setSelected(false);
            this.roseButton.setSelected(false);
            this.tulipsButton.setSelected(false);
            this.sunflowerButton.setSelected(false);
            this.mangoButton.setSelected(false);
        }
    }

    /**
     * Method that updates the tooltips to the seed buttons
     */
    public void updateTooltips() {
        this.turnipButton
                .setToolTipText("<html>Turnip Seed<br>Owned: " + farmer.get_seedInventory().get("Turnip") + "</html>");
        this.carrotButton
                .setToolTipText("<html>Carrot Seed<br>Owned: " + farmer.get_seedInventory().get("Carrot") + "</html>");
        this.potatoButton
                .setToolTipText("<html>Potato Seed<br>Owned: " + farmer.get_seedInventory().get("Potato") + "</html>");
        this.roseButton
                .setToolTipText("<html>Rose Seed<br>Owned: " + farmer.get_seedInventory().get("Rose") + "</html>");
        this.tulipsButton
                .setToolTipText("<html>Tulips Seed<br>Owned: " + farmer.get_seedInventory().get("Tulips") + "</html>");
        this.sunflowerButton
                .setToolTipText(
                        "<html>Sunflower Seed<br>Owned: " + farmer.get_seedInventory().get("Sunflower") + "</html>");
        this.mangoButton
                .setToolTipText("<html>Mango Seed<br>Owned: " + farmer.get_seedInventory().get("Mango") + "</html>");
        this.appleButton
                .setToolTipText("<html>Apple Seed<br>Owned: " + farmer.get_seedInventory().get("Apple") + "</html>");
    }
}