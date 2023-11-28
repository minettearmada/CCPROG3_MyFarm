/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import main.*;

/**
 * Class responsible for the game's GUI
 */
public class MainUI {
    private JFrame mainWindow; // UI's main JFrame
    private JPanel mainPanel; // UI's main JPanel
    private JLabel levelLabel; // UI's label for the farmer type
    private JLabel objectcoinLabel; // UI's lavel for the objectocin
    private JLabel xpLabel; // UI's label for the farmer experience
    private JLabel calendarLabel; // UI's label for the current day
    private JLabel sunLabel; // UI's label for the sun icon
    private JLabel rainLabel; // UI's label for the rain icon
    private Player farmer; // The main player object
    private Farm farm; // The main farm object
    private Tools tools; // The main tools object
    private JButton[][] farmTiles; // A 2D JButton array that holds all the clickable farm tiles in the game
    private SeedsUI seedsUI; // UI for the seeds
    private ToolsUI toolsUI; // UI for the tools
    private int nxtLvl_threshold; // Dictates the threshold for farmer to level up
    private boolean game_over; // Dictates whether the player has lost or not

    public MainUI() {
        this.mainWindow = new JFrame("MyFarm"); // Main game window
        this.mainPanel = new JPanel();
        this.nxtLvl_threshold = 100;
        this.game_over = false;
    }

    /**
     * Method that returns the value assigned to game_over
     * 
     * @return a boolean that dictates whether the player has lost or not
     */
    public boolean getGame_over() {
        return this.game_over;
    }

    /**
     * Method that sets the value for game_over
     * 
     * @param a true if game is over and false otherwise
     */
    public void setGame_over(boolean a) {
        this.game_over = a;
    }

    /**
     * Method that setups all necessary information to related to the GUI
     */
    public void setup() {
        Image bgImage = null;
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1300, 800);
        mainWindow.setResizable(false);
        // Main Panel uses CardLayout Panel in order to switch scenes on button click
        mainPanel.setLayout(new CardLayout());

        // Start Music
        SoundHandler.playMusic("resources/Sound/BgMusic.wav");

        // Set background image
        try {
            File imageFile = new File("resources/GameStartBg.png");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bgImage = bgImage.getScaledInstance(1300, 800, java.awt.Image.SCALE_SMOOTH);
        BackgroundImagePanel mainMenuPanel = new BackgroundImagePanel(bgImage);

        // Create game title
        JLabel gameTitle = new JLabel();
        gameTitle.setBounds(250, 125, 800, 200);

        ImageIcon MyFarmTitle = new ImageIcon("resources/GameTitle.png");
        gameTitle.setIcon(MyFarmTitle);

        mainMenuPanel.add(gameTitle);

        // Create main menu panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(430, 350, 450, 250);
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));

        JButton startGame = new JButton("START GAME");
        startGame.setBackground(Color.WHITE);
        startGame.setFont(new FontUIResource("Tahoma", 0, 40));
        startGame.addActionListener(new StartMenuActionHandler(mainPanel));
        startGame.setActionCommand("START");

        JButton htpButton = new JButton("HOW TO PLAY");
        htpButton.setBackground(Color.WHITE);
        htpButton.setFont(new FontUIResource("Tahoma", 0, 40));
        htpButton.addActionListener(new StartMenuActionHandler(mainPanel));
        htpButton.setActionCommand("HTP");

        JButton quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.WHITE);
        quitButton.setFont(new FontUIResource("Tahoma", 0, 40));
        quitButton.addActionListener(new StartMenuActionHandler(mainPanel));
        quitButton.setActionCommand("QUIT");

        buttonPanel.add(startGame);
        buttonPanel.add(htpButton);
        buttonPanel.add(quitButton);

        mainMenuPanel.add(buttonPanel);

        // Create How To Play panel
        ImageIcon backIcon = new ImageIcon("resources/BackButton.png");
        ImageIcon backIconPressed = new ImageIcon("resources/BackPressed.png");
        JButton backButton = new JButton();
        backButton.setIcon(backIcon);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setPressedIcon(backIconPressed);
        backButton.setSelectedIcon(backIconPressed);
        backButton.setBounds(15, 15, 110, 50);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                CardLayout scenes = (CardLayout) mainPanel.getLayout();
                scenes.show(mainPanel, "main_menu");
            }
        });

        BackgroundImagePanel htpPanel = new BackgroundImagePanel(bgImage);

        // How to Play Panel
        JPanel aboutGamePanel = new JPanel();
        aboutGamePanel.setBounds(100, 100, 1100, 600);
        aboutGamePanel.setBackground(Color.WHITE);

        JLabel htpLabel = new JLabel("ABOUT THE GAME");
        htpLabel.setBounds(500, 200, 500, 50);
        htpLabel.setFont(new Font("Tahoma", Font.BOLD, 50));

        JTextArea tf1 = new JTextArea();
        String text = "\nMyFarm is a farming simulation game, where the player acts as the sole farmer (and manager) of their own farm.";
        text += "\nThe gameplay typically involves the following tasks:\n";
        text += "\tBuying seeds\n \tPreparing land\n \tPlanting seeds\n \tAdvancing days (err… watching crops grow)\n \tHarvesting crops";
        tf1.setEditable(false);
        tf1.setBounds(100, 500, 500, 500);
        tf1.setOpaque(false);
        tf1.setText(text);
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        JTextArea tf2 = new JTextArea();
        String text2 = "\nThere's obviously a lot more to the game, but that's the general idea. There's also no real end goal to this game - \nas in many simulation games.";
        text2 += " However, to make things clear, the game can theoretically go on forever and \nthe player can continue to play for as long as they want except in the case when they\n \t(1) run out of seeds\n \t(2) don't have any active/growing crops\n \t(3) don't have enough money to buy new seeds\n";
        tf2.setEditable(false);
        tf2.setBounds(100, 800, 500, 500);
        tf2.setOpaque(false);
        tf2.setText(text2);
        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        JTextArea tf3 = new JTextArea();
        String text3 = "The player also cannot continue if all the tiles in their farm lot contain withered crops (i.e. crops that died due to \nlack of care).";
        text3 += "When either situation happens, the game ends and the player is asked if they'd want to start a new \ngame or simply exit the program.\n";
        tf3.setEditable(false);
        tf3.setBounds(100, 800, 500, 500);
        tf3.setOpaque(false);
        tf3.setText(text3);
        tf3.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        aboutGamePanel.add(htpLabel);
        aboutGamePanel.add(tf1);
        aboutGamePanel.add(tf2);
        aboutGamePanel.add(tf3);
        htpPanel.add(aboutGamePanel);
        htpPanel.add(backButton);

        // Create FileChooser Panel when Start is clicked
        BackgroundImagePanel fileChooserPanel = new BackgroundImagePanel(bgImage);
        JButton backButton2 = new JButton();
        backButton2.setIcon(backIcon);
        backButton2.setOpaque(false);
        backButton2.setContentAreaFilled(false);
        backButton2.setBorderPainted(false);
        backButton2.setPressedIcon(backIconPressed);
        backButton2.setSelectedIcon(backIconPressed);
        backButton2.setBounds(15, 15, 110, 50);
        backButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                CardLayout scenes = (CardLayout) mainPanel.getLayout();
                scenes.show(mainPanel, "main_menu");
            }
        });

        JTextArea tArea = new JTextArea("Select a file for farm rock distribution.");
        tArea.setEditable(false);
        tArea.setBounds(520, 220, 340, 30);
        tArea.setOpaque(false);
        tArea.setFont(new Font("Sans Serif", Font.BOLD, 18));

        JButton loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setFont(new FontUIResource("Tahoma", 0, 15));
        loadGameButton.setBounds(600, 350, 150, 20);
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout scenes = (CardLayout) mainPanel.getLayout();
                scenes.show(mainPanel, "main_game");
            }
        });

        JButton selectButton = new JButton("SELECT FILE");
        selectButton.setFont(new FontUIResource("Tahoma", 0, 15));
        selectButton.setBounds(600, 250, 150, 20);
        MainUI gameUI = this;
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("C:");
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    UserMethods.game_start(file, gameUI);

                    tArea.setText(file.getName() + " file is chosen.");
                    fileChooserPanel.add(loadGameButton);

                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        // Set tooltips to disapeear only after 10 seconds
        ToolTipManager.sharedInstance().setDismissDelay(10000);

        fileChooserPanel.add(backButton2);
        fileChooserPanel.add(tArea);
        fileChooserPanel.add(selectButton);

        // Add all start menu components to mainPanel
        mainPanel.add(mainMenuPanel, "main_menu");
        mainPanel.add(htpPanel, "htp_panel");
        mainPanel.add(fileChooserPanel, "fileChooser_panel");

        mainWindow.add(mainPanel);
        mainWindow.setVisible(true);
    }

    /**
     * Method that setsup and displays the UI for the main game
     * 
     * @param farmer The main player object
     * @param farm   The main farm object
     * @param tools  The main tool object
     */
    public void drawMainGameUI(Player farmer, Farm farm, Tools tools) {
        Image bgImage = null;
        this.farmer = farmer;
        this.farm = farm;
        this.tools = tools;

        // Set background image
        try {
            File imageFile = new File("resources/MainGameBg.png");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bgImage = bgImage.getScaledInstance(1300, 800, java.awt.Image.SCALE_SMOOTH);
        BackgroundImagePanel mainGamePanel = new BackgroundImagePanel(bgImage);

        ImageIcon levelIcon = new ImageIcon("resources/level.png");
        ImageIcon objectcoinIcon = new ImageIcon("resources/objectcoin.png");
        ImageIcon xpIcon = new ImageIcon("resources/xp.png");
        ImageIcon calendarIcon = new ImageIcon("resources/calendar.png");
        ImageIcon rainIcon = new ImageIcon("resources/rain.png");
        ImageIcon sunIcon = new ImageIcon("resources/sun.png");

        JLabel level = new JLabel();
        level.setText(farmer.get_farmer_type());
        level.setIcon(levelIcon);
        level.setHorizontalTextPosition(JLabel.CENTER);
        level.setBounds(20, 20, 300, 60);
        if (farmer.get_farmer_type() == "Farmer") {
            level.setFont(new Font("Tahoma", Font.BOLD, 20));
        } else
            level.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.levelLabel = level;

        JLabel objectcoin = new JLabel();
        DecimalFormat df = new DecimalFormat("0.00");
        objectcoin.setText(df.format(farmer.get_wallet().get_amt()));
        objectcoin.setIcon(objectcoinIcon);
        objectcoin.setHorizontalTextPosition(JLabel.CENTER);
        objectcoin.setBounds(325, 20, 225, 60);
        objectcoin.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.objectcoinLabel = objectcoin;

        JLabel xp = new JLabel();
        xp.setText("LEVEL " + Integer.toString(farmer.get_lvl()));
        xp.setIcon(xpIcon);
        xp.setHorizontalTextPosition(JLabel.CENTER);
        xp.setBounds(540, 20, 225, 60);
        xp.setFont(new Font("Tahoma", Font.BOLD, 20));
        xp.setToolTipText(df.format(farmer.get_experience()) + "/" + this.nxtLvl_threshold);
        this.xpLabel = xp;

        JLabel sun = new JLabel();
        sun.setIcon(sunIcon);
        sun.setHorizontalTextPosition(JLabel.CENTER);
        sun.setBounds(1070, 15, 80, 80);
        this.sunLabel = sun;

        JLabel rain = new JLabel();
        rain.setIcon(rainIcon);
        rain.setHorizontalTextPosition(JLabel.CENTER);
        rain.setBounds(1070, 15, 80, 80);
        this.rainLabel = rain;
        this.rainLabel.setVisible(false);

        JLabel calendar = new JLabel();
        calendar.setText(Integer.toString(farm.getDay_ctr()));
        calendar.setIcon(calendarIcon);
        calendar.setHorizontalTextPosition(JLabel.CENTER);
        calendar.setVerticalTextPosition(JLabel.CENTER);
        calendar.setBounds(1150, 15, 120, 160);
        calendar.setFont(new Font("Tahoma", Font.BOLD, 40));
        this.calendarLabel = calendar;

        mainGamePanel.add(sun);
        mainGamePanel.add(rain);
        mainGamePanel.add(level);
        mainGamePanel.add(objectcoin);
        mainGamePanel.add(xp);
        mainGamePanel.add(calendar);

        // Creating buttons in main game
        JButton shopButton = new JButton("SHOP");
        shopButton.setBackground(Color.WHITE);
        shopButton.setFont(new FontUIResource("Tahoma", 0, 20));
        shopButton.setBounds(20, 165, 150, 40);

        shopButton.setToolTipText("You can buy seeds here.");
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                drawShopFrame();
            }
        });

        JButton helpButton = new JButton("HELP");
        helpButton.setBackground(Color.WHITE);
        helpButton.setFont(new FontUIResource("Tahoma", 0, 20));
        helpButton.setBounds(20, 215, 150, 40);
        helpButton.setToolTipText("Help");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                drawHelpFrame();
            }
        });

        // Set up harvest button
        JButton harvestButton = new JButton("HARVEST");
        harvestButton.setBackground(Color.WHITE);
        harvestButton.setFont(new FontUIResource("Tahoma", 0, 20));
        harvestButton.setBounds(20, 590, 150, 50);
        harvestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                // Check if there are any harvestable crops
                boolean harvestableCrop = false;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (farm.get_plots()[i][j].get_crop() != null) {
                            if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                harvestableCrop = true;
                            }
                        }
                    }
                }

                if (!harvestableCrop) {
                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 350, 100);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "There are no harvestable crops!";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setSize(250, 50);
                    label.setFont(new FontUIResource("Tahoma", 0, 15));

                    popup.add(label);
                    popup.setVisible(true);

                    // Use a timer to make JDialog automatically disappear
                    Timer timer = new Timer(1500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    toolsUI.setToolBtnPressed("");
                    seedsUI.setSeedBtnPressed("");
                    toolsUI.untoggleButtons();
                    seedsUI.untoggleButtons();

                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            farmTiles[i][j].setEnabled(true);
                        }
                    }
                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 350, 100);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "Select the crop you want to harvest!";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setSize(250, 50);
                    label.setFont(new FontUIResource("Tahoma", 0, 15));

                    popup.add(label);
                    popup.setVisible(true);

                    // Use a timer to make JDialog automatically disappear
                    Timer timer = new Timer(1500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            SoundHandler.playSFX("resources/Sound/earn_sfx.wav");
                            popup.dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });

        // Set up register button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setBackground(Color.WHITE);
        registerButton.setFont(new FontUIResource("Tahoma", 0, 20));
        registerButton.setBounds(20, 650, 150, 50);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((farmer.get_lvl() >= 5 && farmer.get_lvl() < 10)
                        && !farmer.get_farmer_type().equals("Registered Farmer")
                        && farmer.get_wallet().get_amt() > 200) {
                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 550, 300);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "<html>You have reached the requirements to become a Registered Farmer. <br>Would you like to register for 200 Objectcoins?";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setBounds(180, 50, 250, 80);
                    label.setFont(new FontUIResource("Tahoma", 0, 16));

                    JButton yesBtn = new JButton();
                    yesBtn.setBounds(130, 200, 80, 40);
                    yesBtn.setOpaque(false);
                    yesBtn.setContentAreaFilled(false);
                    yesBtn.setBorderPainted(false);
                    ImageIcon yesBtnIcon = new ImageIcon("resources/YesButton.png");
                    Image img = yesBtnIcon.getImage();
                    img = img.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIcon = new ImageIcon(img);
                    yesBtn.setIcon(yesBtnIcon);
                    ImageIcon yesBtnIconPressed = new ImageIcon("resources/YesPressed.png");
                    Image img3 = yesBtnIconPressed.getImage();
                    img3 = img3.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIconPressed = new ImageIcon(img3);
                    yesBtn.setPressedIcon(yesBtnIconPressed);
                    yesBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (!farmer.get_farmer_type().equals("Registered Farmer")) {
                                farmer.get_wallet().subtract_amt(200);
                                farmer.change_farmerType(0);
                            }
                            String message = "<html>Used 200 Objectcoins.<br>Successfully registered as a Registered Farmer!</html>";
                            label.setText(message);
                            popup.repaint();
                            redrawMainGameUI();
                            // Use a timer to make JDialog automatically disappear
                            Timer timer = new Timer(2000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    popup.dispose();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    });

                    JButton noBtn = new JButton();
                    noBtn.setBounds(340, 200, 80, 40);
                    noBtn.setOpaque(false);
                    noBtn.setContentAreaFilled(false);
                    noBtn.setBorderPainted(false);
                    ImageIcon noBtnIcon = new ImageIcon("resources/NoButton.png");
                    Image img2 = noBtnIcon.getImage();
                    img2 = img2.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIcon = new ImageIcon(img2);
                    noBtn.setIcon(noBtnIcon);
                    ImageIcon noBtnIconPressed = new ImageIcon("resources/NoPressed.png");
                    Image img4 = noBtnIconPressed.getImage();
                    img4 = img4.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIconPressed = new ImageIcon(img4);
                    noBtn.setPressedIcon(noBtnIconPressed);
                    noBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                    popup.add(yesBtn);
                    popup.add(noBtn);
                    popup.add(label);
                    popup.setVisible(true);

                } else if ((farmer.get_lvl() >= 10 && farmer.get_lvl() < 15)
                        && !farmer.get_farmer_type().equals("Distinguished Farmer")
                        && farmer.get_wallet().get_amt() > 300) {

                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 550, 300);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "<html>You have reached the requirements to become a Distinguished Farmer. <br>Would you like to register for 300 Objectcoins?</html>";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setBounds(180, 50, 250, 80);
                    label.setFont(new FontUIResource("Tahoma", 0, 16));

                    JButton yesBtn = new JButton();
                    yesBtn.setBounds(130, 200, 80, 40);
                    yesBtn.setOpaque(false);
                    yesBtn.setContentAreaFilled(false);
                    yesBtn.setBorderPainted(false);
                    ImageIcon yesBtnIcon = new ImageIcon("resources/YesButton.png");
                    Image img = yesBtnIcon.getImage();
                    img = img.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIcon = new ImageIcon(img);
                    yesBtn.setIcon(yesBtnIcon);
                    ImageIcon yesBtnIconPressed = new ImageIcon("resources/YesPressed.png");
                    Image img3 = yesBtnIconPressed.getImage();
                    img3 = img3.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIconPressed = new ImageIcon(img3);
                    yesBtn.setPressedIcon(yesBtnIconPressed);
                    yesBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (!farmer.get_farmer_type().equals("Distinguished Farmer")) {
                                farmer.get_wallet().subtract_amt(300);
                                farmer.change_farmerType(1);
                            }
                            String message = "<html>Used 300 Objectcoins.<br>Successfully registered as a Distinguished Farmer!</html>";
                            label.setText(message);
                            popup.repaint();
                            redrawMainGameUI();
                            // Use a timer to make JDialog automatically disappear
                            Timer timer = new Timer(2000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    popup.dispose();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    });

                    JButton noBtn = new JButton();
                    noBtn.setBounds(340, 200, 80, 40);
                    noBtn.setOpaque(false);
                    noBtn.setContentAreaFilled(false);
                    noBtn.setBorderPainted(false);
                    ImageIcon noBtnIcon = new ImageIcon("resources/NoButton.png");
                    Image img2 = noBtnIcon.getImage();
                    img2 = img2.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIcon = new ImageIcon(img2);
                    noBtn.setIcon(noBtnIcon);
                    ImageIcon noBtnIconPressed = new ImageIcon("resources/NoPressed.png");
                    Image img4 = noBtnIconPressed.getImage();
                    img4 = img4.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIconPressed = new ImageIcon(img4);
                    noBtn.setPressedIcon(noBtnIconPressed);
                    noBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                    popup.add(yesBtn);
                    popup.add(noBtn);
                    popup.add(label);
                    popup.setVisible(true);
                } else if (farmer.get_lvl() >= 15 && !farmer.get_farmer_type().equals("Legendary Farmer")
                        && farmer.get_wallet().get_amt() > 400) {

                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 550, 300);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "<html>You have reached the requirements to become a Legendary Farmer. <br>Would you like to register for 400 Objectcoins?</html>";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setBounds(180, 50, 250, 80);
                    label.setFont(new FontUIResource("Tahoma", 0, 16));

                    JButton yesBtn = new JButton();
                    yesBtn.setBounds(130, 200, 80, 40);
                    yesBtn.setOpaque(false);
                    yesBtn.setContentAreaFilled(false);
                    yesBtn.setBorderPainted(false);
                    ImageIcon yesBtnIcon = new ImageIcon("resources/YesButton.png");
                    Image img = yesBtnIcon.getImage();
                    img = img.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIcon = new ImageIcon(img);
                    yesBtn.setIcon(yesBtnIcon);
                    ImageIcon yesBtnIconPressed = new ImageIcon("resources/YesPressed.png");
                    Image img3 = yesBtnIconPressed.getImage();
                    img3 = img3.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    yesBtnIconPressed = new ImageIcon(img3);
                    yesBtn.setPressedIcon(yesBtnIconPressed);
                    yesBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (!farmer.get_farmer_type().equals("Legendary Farmer")) {
                                farmer.get_wallet().subtract_amt(400);
                                farmer.change_farmerType(2);
                            }
                            String message = "<html>Used 400 Objectcoins.<br>Successfully registered as a Legendary Farmer!</html>";
                            label.setText(message);
                            popup.repaint();
                            redrawMainGameUI();
                            // Use a timer to make JDialog automatically disappear
                            Timer timer = new Timer(2000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    popup.dispose();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    });

                    JButton noBtn = new JButton();
                    noBtn.setBounds(340, 200, 80, 40);
                    noBtn.setOpaque(false);
                    noBtn.setContentAreaFilled(false);
                    noBtn.setBorderPainted(false);
                    ImageIcon noBtnIcon = new ImageIcon("resources/NoButton.png");
                    Image img2 = noBtnIcon.getImage();
                    img2 = img2.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIcon = new ImageIcon(img2);
                    noBtn.setIcon(noBtnIcon);
                    ImageIcon noBtnIconPressed = new ImageIcon("resources/NoPressed.png");
                    Image img4 = noBtnIconPressed.getImage();
                    img4 = img4.getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH);
                    noBtnIconPressed = new ImageIcon(img4);
                    noBtn.setPressedIcon(noBtnIconPressed);
                    noBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                    popup.add(yesBtn);
                    popup.add(noBtn);
                    popup.add(label);
                    popup.setVisible(true);
                } else {
                    String message;
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 350, 100);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    message = "You have not met the requirements to register!";
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setSize(250, 50);
                    label.setFont(new FontUIResource("Tahoma", 0, 15));

                    popup.add(label);
                    popup.setVisible(true);

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
        });

        ImageIcon quitIcon = new ImageIcon("resources/QuitButton.png");
        ImageIcon quitIconPressed = new ImageIcon("resources/QuitButtonPressed.png");
        JButton quitButton = new JButton();
        quitButton.setIcon(quitIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setPressedIcon(quitIconPressed);
        quitButton.setSelectedIcon(quitIconPressed);
        quitButton.setBounds(1150, 700, 125, 50);
        quitButton.setToolTipText("End Game");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");
                System.exit(0);
            }
        });

        Icon nextDayIcon = new ImageIcon("resources/next_day.png");
        Icon nextDayIcon_pressed = new ImageIcon("resources/next_day_pressed.png");
        JButton nextDayButton = new JButton(nextDayIcon);
        nextDayButton.setOpaque(false);
        nextDayButton.setContentAreaFilled(false);
        nextDayButton.setBorderPainted(false);
        nextDayButton.setPressedIcon(nextDayIcon_pressed);
        nextDayButton.setBounds(1170, 350, 100, 100);
        nextDayButton.setToolTipText("Next Day");
        MainUI gameUI = this;
        nextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundHandler.playSFX("resources/Sound/click_sfx.wav");

                String message = UserMethods.end_day(farm, farmer, gameUI, farmTiles);
                if (gameUI.getGame_over()) {
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 500, 300);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);

                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setSize(250, 50);
                    label.setFont(new FontUIResource("Tahoma", 0, 15));

                    JButton quitButton = new JButton("QUIT");
                    quitButton.setBackground(Color.ORANGE);
                    quitButton.setFont(new FontUIResource("Tahoma", 0, 20));
                    quitButton.setBounds(180, 200, 125, 50);
                    quitButton.setToolTipText("Quit");
                    quitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
                    popup.add(quitButton);
                    popup.add(label, SwingConstants.CENTER);
                    popup.setVisible(true);
                    mainWindow.repaint();
                } else {
                    JDialog popup = new JDialog(mainWindow);
                    popup.setBounds(600, 350, 300, 100);
                    popup.setLocationRelativeTo(mainWindow);
                    popup.setUndecorated(true);
                    JLabel label = new JLabel(message, SwingConstants.CENTER);
                    label.setSize(250, 50);
                    label.setFont(new FontUIResource("Tahoma", 0, 15));

                    popup.add(label, SwingConstants.CENTER);
                    popup.setVisible(true);
                    redrawMainGameUI();
                    // Implement rain, chance of 5%
                    int num = ThreadLocalRandom.current().nextInt(1, 101);
                    // Generate a number from 1 to 100, if the number is 1 to 5 set isRaining to
                    // true
                    if (num <= 5) {
                        farm.set_isRaining(true);
                    } else {
                        farm.set_isRaining(false);
                    }
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
        });

        // Draw farm area
        JPanel farmAreaPanel = new JPanel();
        farmAreaPanel.setBounds(200, 160, 940, 475);
        farmAreaPanel.setLayout(new GridLayout(5, 10, 10, 10));
        farmAreaPanel.setOpaque(false);

        JButton[][] farmTiles = new JButton[5][10];
        ImageIcon tileIcon;
        // Create button for each tile
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (farm.get_plots()[i][j].get_hasRock()) {
                    tileIcon = new ImageIcon("resources/FarmTiles/RockTile.png");
                } else {
                    tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
                }
                // Rescale ImageIcon
                Image img = tileIcon.getImage();
                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                tileIcon = new ImageIcon(img);
                farmTiles[i][j] = new JButton(tileIcon);

                // Set tooltip text
                String info;
                info = "<html>" + "Times Watered: " + farm.get_plots()[i][j].get_timesWatered();
                info += "<br>" + "Times Fertilized: " + farm.get_plots()[i][j].get_timesFertilized() + "</html>";
                farmTiles[i][j].setToolTipText(info);

                farmTiles[i][j].setDisabledIcon(tileIcon);
                farmTiles[i][j].setEnabled(false);
                farmTiles[i][j].setOpaque(false);
                farmTiles[i][j].setContentAreaFilled(false);
                farmTiles[i][j].setBorderPainted(false);
                farmAreaPanel.add(farmTiles[i][j]);
            }
        }

        // Setup Tools and Seeds Buttons
        ToolsUI toolsUI = new ToolsUI(mainGamePanel, farmTiles);
        SeedsUI seedsUI = new SeedsUI(mainGamePanel, farmTiles);
        toolsUI.setup(seedsUI);
        seedsUI.setup(toolsUI, this.farmer);

        this.toolsUI = toolsUI;
        this.seedsUI = seedsUI;

        // Add action listeners to farmtile buttons
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                farmTiles[i][j].addActionListener(
                        new FarmTilesActionHandler(i, j, toolsUI, seedsUI, farm, farmer, tools, mainGamePanel, this));
            }
        }

        this.farmTiles = farmTiles;

        mainGamePanel.add(shopButton);
        mainGamePanel.add(helpButton);
        mainGamePanel.add(harvestButton);
        mainGamePanel.add(registerButton);
        mainGamePanel.add(nextDayButton);
        mainGamePanel.add(quitButton);
        mainGamePanel.add(farmAreaPanel);

        mainPanel.add(mainGamePanel, "main_game");
    }

    /**
     * Method that handles the setup and displaying of the shop frame
     */
    private void drawShopFrame() {
        JFrame shopFrame = new JFrame();
        Image bgImage = null;

        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setSize(980, 600);
        shopFrame.setResizable(false);
        shopFrame.setUndecorated(true);
        shopFrame.setLocationRelativeTo(this.mainWindow);

        // Set background image
        try {
            File imageFile = new File("resources/ShopBg.png");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bgImage = bgImage.getScaledInstance(980, 600, java.awt.Image.SCALE_SMOOTH);
        BackgroundImagePanel shopPanel = new BackgroundImagePanel(bgImage);
        shopPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        // Create label and buttons for seeds available in shop
        JPanel seedBtnsPanel = new JPanel(new GridLayout(2, 4, 4, 4));
        seedBtnsPanel.setBounds(110, 130, 730, 200);
        seedBtnsPanel.setOpaque(false);

        JLabel shopLabel = new JLabel("CLICK THE BUTTON TO BUY THE SEED!");
        shopLabel.setBounds(240, 490, 500, 50);
        shopLabel.setFont(new Font("Tahoma", Font.BOLD, 25));

        ImageIcon appleIcon = new ImageIcon("resources/SeedIcon/AppleIcon.png");
        ImageIcon carrotIcon = new ImageIcon("resources/SeedIcon/CarrotIcon.png");
        ImageIcon mangoIcon = new ImageIcon("resources/SeedIcon/MangoIcon.png");
        ImageIcon potatoIcon = new ImageIcon("resources/SeedIcon/PotatoIcon.png");
        ImageIcon roseIcon = new ImageIcon("resources/SeedIcon/RoseIcon.png");
        ImageIcon sunflowerIcon = new ImageIcon("resources/SeedIcon/SunflowerIcon.png");
        ImageIcon tulipsIcon = new ImageIcon("resources/SeedIcon/TulipsIcon.png");
        ImageIcon turnipIcon = new ImageIcon("resources/SeedIcon/TurnipIcon.png");

        ImageIcon appleIconPressed = new ImageIcon("resources/SeedIcon/AppleIconPressed.png");
        ImageIcon carrotIconPressed = new ImageIcon("resources/SeedIcon/CarrotIconPressed.png");
        ImageIcon mangoIconPressed = new ImageIcon("resources/SeedIcon/MangoIconPressed.png");
        ImageIcon potatoIconPressed = new ImageIcon("resources/SeedIcon/PotatoIconPressed.png");
        ImageIcon roseIconPressed = new ImageIcon("resources/SeedIcon/RoseIconPressed.png");
        ImageIcon sunflowerIconPressed = new ImageIcon("resources/SeedIcon/SunflowerIconPressed.png");
        ImageIcon tulipsIconPressed = new ImageIcon("resources/SeedIcon/TulipsIconPressed.png");
        ImageIcon turnipIconPressed = new ImageIcon("resources/SeedIcon/TurnipIconPressed.png");

        JButton appleButton = new JButton(appleIcon);
        JButton carrotButton = new JButton(carrotIcon);
        JButton mangoButton = new JButton(mangoIcon);
        JButton potatoButton = new JButton(potatoIcon);
        JButton roseButton = new JButton(roseIcon);
        JButton sunflowerButton = new JButton(sunflowerIcon);
        JButton tulipsButton = new JButton(tulipsIcon);
        JButton turnipButton = new JButton(turnipIcon);

        appleButton.setOpaque(false);
        appleButton.setContentAreaFilled(false);
        appleButton.setBorder(null);
        appleButton.setFocusPainted(false);
        appleButton.setPressedIcon(appleIconPressed);
        appleButton.setToolTipText(
                "<html>Apple Seed<br>Price: " + (200 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        appleButton.addActionListener(
                new ShopActionHandler(this.farmer, 200 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        appleButton.setActionCommand("Apple");

        carrotButton.setOpaque(false);
        carrotButton.setContentAreaFilled(false);
        carrotButton.setBorder(null);
        carrotButton.setFocusPainted(false);
        carrotButton.setPressedIcon(carrotIconPressed);
        carrotButton.setToolTipText(
                "<html>Carrot Seed<br>Price: " + (10 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        carrotButton.addActionListener(
                new ShopActionHandler(this.farmer, 10 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        carrotButton.setActionCommand("Carrot");

        mangoButton.setOpaque(false);
        mangoButton.setContentAreaFilled(false);
        mangoButton.setBorder(null);
        mangoButton.setFocusPainted(false);
        mangoButton.setPressedIcon(mangoIconPressed);
        mangoButton.setToolTipText(
                "<html>Mango Seed<br>Price: " + (100 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        mangoButton.addActionListener(
                new ShopActionHandler(this.farmer, 100 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        mangoButton.setActionCommand("Mango");

        potatoButton.setOpaque(false);
        potatoButton.setContentAreaFilled(false);
        potatoButton.setBorder(null);
        potatoButton.setFocusPainted(false);
        potatoButton.setPressedIcon(potatoIconPressed);
        potatoButton.setToolTipText(
                "<html>Potato Seed<br>Price: " + (20 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        potatoButton.addActionListener(
                new ShopActionHandler(this.farmer, 20 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        potatoButton.setActionCommand("Potato");

        roseButton.setOpaque(false);
        roseButton.setContentAreaFilled(false);
        roseButton.setBorder(null);
        roseButton.setFocusPainted(false);
        roseButton.setPressedIcon(roseIconPressed);
        roseButton.setToolTipText(
                "<html>Rose Seed<br>Price: " + (5 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        roseButton.addActionListener(
                new ShopActionHandler(this.farmer, 5 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        roseButton.setActionCommand("Rose");

        sunflowerButton.setOpaque(false);
        sunflowerButton.setContentAreaFilled(false);
        sunflowerButton.setBorder(null);
        sunflowerButton.setFocusPainted(false);
        sunflowerButton.setPressedIcon(sunflowerIconPressed);
        sunflowerButton
                .setToolTipText("<html>Sunflower Seed<br>Price: " + (20 - farmer.get_seed_cost_reduc())
                        + " Objectcoins</html>");
        sunflowerButton.addActionListener(
                new ShopActionHandler(this.farmer, 20 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        sunflowerButton.setActionCommand("Sunflower");

        tulipsButton.setOpaque(false);
        tulipsButton.setContentAreaFilled(false);
        tulipsButton.setBorder(null);
        tulipsButton.setFocusPainted(false);
        tulipsButton.setPressedIcon(tulipsIconPressed);
        tulipsButton.setToolTipText(
                "<html>Tulips Seed<br>Price: " + (10 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        tulipsButton.addActionListener(
                new ShopActionHandler(this.farmer, 10 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        tulipsButton.setActionCommand("Tulips");

        turnipButton.setOpaque(false);
        turnipButton.setContentAreaFilled(false);
        turnipButton.setBorder(null);
        turnipButton.setFocusPainted(false);
        turnipButton.setPressedIcon(turnipIconPressed);
        turnipButton.setToolTipText(
                "<html>Turnip Seed<br>Price: " + (5 - farmer.get_seed_cost_reduc()) + " Objectcoins</html>");
        turnipButton.addActionListener(
                new ShopActionHandler(this.farmer, 5 - farmer.get_seed_cost_reduc(), this, shopFrame, this.seedsUI));
        turnipButton.setActionCommand("Turnip");

        // Closes shopFrame if user clicks/brings mouse outside
        mainWindow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shopFrame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                shopFrame.dispose();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        seedBtnsPanel.add(turnipButton);
        seedBtnsPanel.add(carrotButton);
        seedBtnsPanel.add(potatoButton);
        seedBtnsPanel.add(roseButton);
        seedBtnsPanel.add(tulipsButton);
        seedBtnsPanel.add(sunflowerButton);
        seedBtnsPanel.add(mangoButton);
        seedBtnsPanel.add(appleButton);

        shopPanel.add(seedBtnsPanel);
        shopPanel.add(shopLabel);

        shopFrame.add(shopPanel);
        shopFrame.setVisible(true);
    }

    private void drawHelpFrame() {
        JFrame helpFrame = new JFrame();
        JPanel toolsPanel = new JPanel();
        JPanel seedsPanel = new JPanel();
        Image bgImage = null;

        helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helpFrame.setSize(950, 600);
        helpFrame.setResizable(false);
        helpFrame.setUndecorated(true);
        helpFrame.setLocationRelativeTo(this.mainWindow);

        // Set background image
        try {
            File imageFile = new File("resources/HelpBg.png");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bgImage = bgImage.getScaledInstance(950, 600, java.awt.Image.SCALE_SMOOTH);
        BackgroundImagePanel helpPanel = new BackgroundImagePanel(bgImage);
        helpPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        JLabel help = new JLabel("HELP");
        help.setFont(new Font("Verdana", 1, 30));
        help.setHorizontalTextPosition(JLabel.CENTER);
        help.setBounds(425, 25, 100, 50);
        helpPanel.add(help);

        ImageIcon seedsPanelIcon = new ImageIcon("resources/SeedsPanel.png");
        JLabel seedsLabel = new JLabel(seedsPanelIcon);
        seedsPanel.add(seedsLabel);
        seedsPanel.setBounds(375, 75, 550, 500);
        seedsPanel.setBackground(Color.decode("#97C251"));

        ImageIcon toolsPanelIcon = new ImageIcon("resources/ToolsPanel.png");
        JLabel toolsLabel = new JLabel(toolsPanelIcon);
        toolsPanel.add(toolsLabel);
        toolsPanel.setBounds(25, 75, 325, 500);
        toolsPanel.setBackground(Color.decode("#97C251"));

        // Closes helpFrame if user clicks/brings mouse outside
        mainWindow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                helpFrame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                helpFrame.dispose();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        helpPanel.add(help);
        helpPanel.add(toolsPanel);
        helpPanel.add(seedsPanel);

        helpFrame.add(helpPanel);
        helpFrame.setVisible(true);
    }

    /**
     * Method that handles the updating of the elements/components in the
     * mainGamePanel. This includes the tooltips.
     */
    public void redrawMainGameUI() {
        DecimalFormat df = new DecimalFormat("0.00");
        // Update Game Icons
        // Check if farmer has leveled up and update if they have
        while (farmer.get_experience() >= nxtLvl_threshold) {
            this.nxtLvl_threshold += 100;
            this.farmer.increment_lvl();
        }
        this.levelLabel.setText(farmer.get_farmer_type());
        this.objectcoinLabel.setText(df.format(farmer.get_wallet().get_amt()));
        this.xpLabel.setText("LEVEL " + Integer.toString(farmer.get_lvl()));
        this.calendarLabel.setText(Integer.toString(farm.getDay_ctr()));

        // Set weather
        if (farm.get_isRaining()) {
            this.sunLabel.setVisible(false);
            this.rainLabel.setVisible(true);
            // Water all plants
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    if (farm.get_plots()[i][j].get_crop() != null) {
                        tools.use_tool(farmTiles[i][j], "Watering Can", farmer, farm, i, j);
                    }
                }
            }
        } else {
            this.rainLabel.setVisible(false);
            this.sunLabel.setVisible(true);
        }

        // Update Tooltips
        this.xpLabel.setToolTipText(df.format(farmer.get_experience()) + "/" + this.nxtLvl_threshold);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                String info;
                if (this.farm.get_plots()[i][j].get_isOccupied()) {
                    info = "<html>" + "Times Watered: " + farm.get_plots()[i][j].get_timesWatered();
                    info += "<br>" + "Times Fertilized: " + farm.get_plots()[i][j].get_timesFertilized() + "<br>";
                    info += this.farm.get_plots()[i][j].get_crop().toString() + "</html>";
                    farmTiles[i][j].setToolTipText(info);
                } else {
                    info = "<html>" + "Times Watered: " + farm.get_plots()[i][j].get_timesWatered();
                    info += "<br>" + "Times Fertilized: " + farm.get_plots()[i][j].get_timesFertilized() + "</html>";
                    farmTiles[i][j].setToolTipText(info);
                }

            }
        }
        this.mainPanel.repaint();
    }

    /**
     * Method that returns the value assigned to farmTiles
     * 
     * @return A 2D JButton array that holds all the clickable farm
     *         tiles in the game
     */
    public JButton[][] getFarmTiles() {
        return farmTiles;
    }

    /**
     * Method that returns the value assigned to tools
     * 
     * @return The main tools object
     */
    public Tools getTools() {
        return tools;
    }
}

/**
 * Utility class that allows setting an image background to a JPanel
 */
class BackgroundImagePanel extends JComponent {
    private Image image;

    BackgroundImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }
}