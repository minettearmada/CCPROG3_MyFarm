/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import GUI.MainUI;
import GUI.SoundHandler;

/**
 * Supplementary utility file/class. Holds methods that are called according to
 * what happens in the game.
 */
public class UserMethods {
    /**
     * A method called when the game is loaded. Loads all necessary information to
     * start the game.
     * 
     * @param file   A file holding information about a Plot class's has_rock
     *               attribute
     * @param gameUI The main UI class
     */
    public static void game_start(File file, MainUI gameUI) {
        // Variable Initializations
        boolean array[][];
        String[] seeds = { "Turnip", "Carrot", "Potato", "Rose", "Tulips", "Sunflower", "Mango",
                "Apple" };
        // Initialize farm area
        Farm farm = new Farm();
        array = get_fileInput(file);
        farm.setup(array);
        // Initialize Player, Tools
        Player farmer = new Player(seeds);
        Tools tools = new Tools();

        gameUI.drawMainGameUI(farmer, farm, tools);
    }

    /**
     * A method called when the user buys a seed
     * 
     * @param farmer The main player class
     * @param cost   Integer dictating the cost of the crop. Takes into account
     *               Player class's seed_cost_reduc attribute
     * @param seed   A string dictating which seed was chosen to be bought
     * 
     * @return A string indicating what happened with the purchase
     */
    public static String buy_seeds(Player farmer, int cost, String seed) {
        int total_cost = cost;
        String message = "";
        // Make the purchase if player has enough Objectcoins
        if (farmer.get_wallet().get_amt() < total_cost) {
            message = "You don't have enough Objectcoins to make this purchase!";
            return message;
        }

        switch (seed) {
            case "Turnip":
                message = "Bought a Turnip Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Turnip",
                        farmer.get_seedInventory().get("Turnip") + 1);
                break;
            case "Carrot":
                message = "Bought a Carrot Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Carrot",
                        farmer.get_seedInventory().get("Carrot") + 1);
                break;
            case "Potato":
                message = "Bought a Potato Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Potato",
                        farmer.get_seedInventory().get("Potato") + 1);
                break;
            case "Rose":
                message = "Bought a Rose Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Rose",
                        farmer.get_seedInventory().get("Rose") + 1);
                break;
            case "Tulips":
                message = "Bought a Tulips Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Tulips",
                        farmer.get_seedInventory().get("Tulips") + 1);
                break;
            case "Sunflower":
                message = "Bought a Sunflower Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Sunflower",
                        farmer.get_seedInventory().get("Sunflower") + 1);
                break;
            case "Mango":
                message = "Bought a Mango Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Mango",
                        farmer.get_seedInventory().get("Mango") + 1);
                break;
            case "Apple":
                message = "Bought an Apple Seed for " + total_cost + " Objectcoins.";
                SoundHandler.playSFX("resources/Sound/buy_sfx.wav");
                farmer.get_wallet().subtract_amt(total_cost);
                farmer.get_seedInventory().replace("Apple",
                        farmer.get_seedInventory().get("Apple") + 1);
                break;
        }
        return message;
    }

    /**
     * A method called whenever the user plants a seed
     * 
     * @param farmTileClicked A JButton object that indicates which farmtile was
     *                        clicked
     * @param farmer          The main player object
     * @param farm            The main farm object
     * @param seed            A string dictating which seed was chosen to be planted
     * @param x               An integer dictating the row of the farmTile
     * @param y               An integer dictating the column of the farmTile
     * 
     * @return A string indicating what happened with the planting
     */
    public static String plant_seed(JButton farmTileClicked, Player farmer, Farm farm, String seed, int x, int y) {
        int row = x, col = y;
        boolean has_seed = false;
        String message = "";

        // Check if player has a seed of any type
        if (farmer.get_seedInventory().get(seed) > 0) {
            has_seed = true;
        }

        if (has_seed) {
            // Check if plot chosen can be planted on
            if (farm.get_plots()[row][col].get_hasRock()) {
                message = "Unable to plant on chosen plot because it has a rock!";
            } else if (farm.get_plots()[row][col].get_isOccupied()) {
                message = "Unable to plant on chosen plot because it is occupied!";

            } else if (!farm.get_plots()[row][col].get_isPlowed()) {
                message = "Unable to plant on chosen plot because it is not yet plowed!";

            } else {
                // Check if seed is a fruit tree
                if (seed.equals("Mango") || seed.equals("Apple")) {
                    if (!checkValidFruitTreePlacement(row, col, farm)) {
                        message = "Unable to plant on chosen plot because not enough space to grow a fruit tree!";
                        return message;
                    }
                }
                // If all checks are passed, plant seed
                farmer.plant_seed(seed, farm.get_plots()[row][col]);
                ImageIcon tileIcon;
                Image img;
                switch (seed) {
                    case "Turnip":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/TurnipSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Carrot":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/CarrotSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Potato":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/PotatoSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Rose":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/RoseSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Tulips":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/TulipsSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Sunflower":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/SunflowerSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Mango":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/MangoSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                    case "Apple":
                        tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/AppleSeed.png");
                        img = tileIcon.getImage();
                        img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                        tileIcon = new ImageIcon(img);
                        farmTileClicked.setIcon(tileIcon);
                        farmTileClicked.setDisabledIcon(tileIcon);
                        break;
                }
                message = "Successfully planted a " + seed +
                        " seed!\n";
            }
        } else {
            message += "You do not own any " + seed + " seeds!";
        }
        return message;
    }

    /**
     * A method called whenever the user harvests a crop
     * 
     * @param farmTileClicked A JButton object that indicates which farmtile was
     *                        clicked
     * @param farmer          The main player object
     * 
     * @param farm            The main farm object
     * @param x               An integer dictating the row of the farmTile
     * @param y               An integer dictating the column of the farmTile
     * @param farmTiles       A 2D JButton array that holds all the clickable farm
     *                        tiles in the game
     * @return A string indicating what happened with the harvesting
     */
    public static String harvest_crop(JButton farmTileClicked, Player farmer, Farm farm, int x, int y,
            JButton[][] farmTiles) {
        int row = x, col = y, products_count, harvestTotal;
        double waterBonus = 0, fertilizerBonus = 0, finalHarvestPrice = 0;
        String message = "";

        // Disable farmtiles since a farmtile has been clicked
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                farmTiles[i][j].setEnabled(false);
            }
        }

        if (!farm.get_plots()[row][col].get_isOccupied()) {
            message = "Chosen plot has no crop!";
            return message;
        } else if (farm.get_plots()[row][col].get_isOccupied()
                && farm.get_plots()[row][col].get_crop().get_isWithered()) {
            message = "Unable to harvest a withered crop!";
            return message;
        } else {
            // Calculate randomized produce count
            products_count = ThreadLocalRandom.current().nextInt(
                    farm.get_plots()[row][col].get_crop().get_minProduce(),
                    farm.get_plots()[row][col].get_crop().get_maxProduce() + 1);

            /*
             * Sell produce from harvest.
             * get_producePrice already takes into account player bonus
             * earnings.
             */
            harvestTotal = products_count * (farm.get_plots()[row][col].get_crop().get_producePrice());
            // Only apply bonus calculation if requirements are met
            if (farm.get_plots()[row][col]
                    .get_timesWatered() >= (farm.get_plots()[row][col].get_crop().get_maxWaterBonusNeed()
                            - farmer.get_waterBonusLimitIncrease())) {
                waterBonus = harvestTotal * 0.2 * (farm.get_plots()[row][col].get_timesWatered() - 1);
            }
            if (farm.get_plots()[row][col]
                    .get_timesFertilized() >= (farm.get_plots()[row][col].get_crop().get_maxFertilizerBonusNeed()
                            - farmer.get_fertilizerBonusLimitIncrease())) {
                fertilizerBonus = harvestTotal * 0.5 * (farm.get_plots()[row][col].get_timesFertilized());
            }
            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (farm.get_plots()[row][col].get_crop().get_cropType().equals("Flower")) {
                finalHarvestPrice *= 1.1;
            }

            DecimalFormat df = new DecimalFormat("0.00");
            farmer.get_wallet().add_amt(finalHarvestPrice);
            farmer.add_exp(farm.get_plots()[row][col].get_crop().get_expYield());
            message = "<html>" + products_count + " units of " + farm.get_plots()[row][col].get_crop().get_seedName()
                    + " was harvested.<br>";
            message += "Earned " + df.format(finalHarvestPrice) + " Objectcoins.<br>";
            message += "Gained " + farm.get_plots()[row][col].get_crop().get_expYield() + " experience.<br>";
            message += "Successfully harvested crop!</html>";
            // Remove crop from plot
            farm.get_plots()[row][col].remove_crop();
            // Change farmTiel button image
            ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
            Image img = tileIcon.getImage();
            img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
            tileIcon = new ImageIcon(img);
            farmTileClicked.setIcon(tileIcon);
            farmTileClicked.setDisabledIcon(tileIcon);
        }
        return message;
    }

    /**
     * A method that checks whether or not a selected plot is valid for a fruit tree
     * to be planted on
     * 
     * @param row  An integer dictating the row of the plot
     * @param col  An integer dictating the column of the plot
     * @param farm The main farm object
     * @return a boolean true if the plot has valid fruit tree placement and false
     *         otherwise
     */
    public static boolean checkValidFruitTreePlacement(int row, int col, Farm farm) {
        /*
         * Check if chosen index is at the edge of the farm area, if it is immediately
         * return false
         */
        if (row == 0 || row == 4 || col == 0 || col == 9) {
            return false;
        }

        // Check if surrounding tiles is occupied
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    if (farm.get_plots()[row + i][col + j].get_isOccupied()
                            || farm.get_plots()[row + i][col + j].get_hasRock()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * A method to read the rock file input
     * 
     * @param file The file to be read
     * 
     * @return a 2D boolean array that dictates whether or not will have a rock.
     * 
     */
    public static boolean[][] get_fileInput(File file) {
        boolean[][] array = new boolean[5][10];
        int i = 0, j = 0;

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                line = line.replaceAll("\\s+", "");
                for (String str : line.split(",")) {
                    if (str.equals("0")) {
                        array[i][j] = false;
                    } else {
                        array[i][j] = true;
                    }
                    j++;
                }
                j = 0;
                i++;
            }
            fileScanner.close();
        } catch (FileNotFoundException ex) {
            System.exit(0);
        }
        return array;
    }

    /**
     * A method called whenever user ends the day
     * 
     * @param farm      The main farm object
     * @param farmer    The main player object
     * @param gameUI    The main UI object
     * @param farmTiles A 2D JButton array that holds all the clickable farm
     *                  tiles in the game
     * 
     * @return A string indicating what happened when the day ended. Either the game
     *         is over or the player moves on to the next day.
     */
    public static String end_day(Farm farm, Player farmer, MainUI gameUI, JButton[][] farmTiles) {
        int activeCrops_ctr = 0, witheredCrops_ctr = 0;
        boolean has_seeds = false;
        String message = "";
        // Runs at the end of each day
        farm.increment_dayCtr();
        // If there are any active crops, increment attribute daysPlanted and set
        // wateredToday to false
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (farm.get_plots()[i][j].get_isOccupied()) {
                    farm.get_plots()[i][j].get_crop().grow_up(farm.get_plots()[i][j]);
                    farm.get_plots()[i][j].set_wateredToday(false);

                    // Set farm tile to unwatered
                    ImageIcon tileIcon;
                    Image img;
                    switch (farm.get_plots()[i][j].get_crop().get_seedName()) {
                        case "Turnip":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                // Still a seed
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/TurnipSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/TurnipPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/TurnipWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                // Growing
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Carrot":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/CarrotSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/CarrotPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/CarrotWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Potato":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/PotatoSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/PotatoPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/PotatoWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Rose":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/RoseSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/RosePlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/RoseWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Tulips":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/TulipsSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/TulipsPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/TulipsWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Sunflower":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/SunflowerSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/SunflowerPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/SunflowerWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Mango":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/MangoSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/MangoPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/MangoWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Apple":
                            if (farm.get_plots()[i][j].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[i][j].get_crop().get_harvestTime() / 2)) {
                                tileIcon = new ImageIcon("resources/FarmTiles/SeedTiles/AppleSeed.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                                break;
                            } else if (farm.get_plots()[i][j].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/ApplePlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[i][j].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/AppleWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantGrowth.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTiles[i][j].setIcon(tileIcon);
                                farmTiles[i][j].setDisabledIcon(tileIcon);
                            }
                    }
                }
            }
        }
        // Check if game_over conditions are met
        // Check if there are any active crops
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (farm.get_plots()[i][j].get_isOccupied()
                        && !farm.get_plots()[i][j].get_crop().get_isWithered()) {
                    activeCrops_ctr += 1;
                }
            }
        }
        // Check if player has seeds
        for (int count : farmer.get_seedInventory().values()) {
            if (count > 0) {
                has_seeds = true;
            }
        }
        // Count how may withered crops there are
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (farm.get_plots()[i][j].get_isOccupied()
                        && farm.get_plots()[i][j].get_crop().get_isWithered()) {
                    witheredCrops_ctr += 1;
                }
            }
        }
        if (activeCrops_ctr == 0 && farmer.get_wallet().get_amt() < 5 && !has_seeds) {
            message = "<html>GAME OVER<br>You have lost the game because you have no active crops, no more seeds, and don't have enough money to buy more seeds!</html>";
            gameUI.setGame_over(true);
            return message;
        }
        if (witheredCrops_ctr == 50) {
            message = "<html>GAME OVER<br>You have lost the game because all plots contain a withered crop!</html>";
            gameUI.setGame_over(true);
            return message;
        }
        return "Moving to the next day!";
    }
}
