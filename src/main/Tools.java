/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class is in charge of all interactions related to the using of
 * a tool. It is responsible for making the changes to the player's experiece
 * after using a tool as well as what happens to an object once a tool is
 * used on it.
 */
public class Tools {
    private String[] toolList; // List of all the tools available in the game

    /**
     * Initialize toolList with all the usable tools in the game
     */
    public Tools() {
        this.toolList = new String[] { "Plow", "Watering Can", "Fertilizer", "Pickaxe", "Shovel" };
    }

    /**
     * Method that gets the list of tools.
     * 
     * @return the array toolList
     */
    public String[] get_toolList() {
        return this.toolList;
    }

    /**
     * Method that makes changes to all other objects when a tool is used.
     * 
     * @param farmTileClicked A JButton object that indicates which farmtile was
     *                        clicked
     * @param tool            dicatates which tool will be used according to each
     *                        tool's
     *                        position in the toolList array
     * @param farmer          The main player object
     * @param farm            The main farm object
     * @param x               An integer dictating the row of the farmTile
     * @param y               An integer dictating the column of the farmTile
     */
    public String use_tool(JButton farmTileClicked, String tool, Player farmer, Farm farm, int x, int y) {
        int row = x, col = y;
        String message = "";
        switch (tool) {
            case "Plow":
                // Check if plot chosen can be plowed
                if (farm.get_plots()[row][col].get_hasRock()) {
                    message = "Unable to plow chosen plot because it has a rock!";
                } else if (farm.get_plots()[row][col].get_isOccupied()) {
                    message = "Unable to plow chosen plot because it is occupied!";
                } else if (farm.get_plots()[row][col].get_isPlowed()) {
                    message = "Unable to plow chosen plot because it is already plowed!";
                } else {
                    // If all checks are passed, turn isPlowed to true and add experience to player
                    farm.get_plots()[row][col].set_isPlowed(true);
                    farmer.add_exp(0.5);
                    // Change button image
                    ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/PlowedTile.png");
                    Image img = tileIcon.getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    tileIcon = new ImageIcon(img);
                    farmTileClicked.setIcon(tileIcon);
                    farmTileClicked.setDisabledIcon(tileIcon);
                    message = "<html>Successfully plowed plot!";
                    message += "<br>Gained 0.5 experience.</html>";
                }
                break;
            case "Watering Can":
                if (!farm.get_plots()[row][col].get_isOccupied()) {
                    message = "Chosen plot has no crop planted to water!";
                } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                    message = "Unable to water a withered crop!";
                } else if (farm.get_plots()[row][col].get_wateredToday()) {
                    message = "This crop has already been watered today!";
                } else {
                    farm.get_plots()[row][col]
                            .increment_timesWatered(farm.get_plots()[row][col].get_crop().get_maxWaterBonusNeed());
                    farm.get_plots()[row][col].set_wateredToday(true);
                    ImageIcon tileIcon;
                    Image img;
                    switch (farm.get_plots()[row][col].get_crop().get_seedName()) {
                        case "Turnip":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/TurnipSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/TurnipPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/TurnipWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Carrot":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/CarrotSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/CarrotPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/CarrotWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Potato":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/PotatoSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/PotatoPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/PotatoWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Rose":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/RoseSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/RosePlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/RoseWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Tulips":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/TulipsSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/TulipsPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/TulipsWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Sunflower":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/SunflowerSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/SunflowerPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/SunflowerWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Mango":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/MangoSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/MangoPlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/MangoWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                        case "Apple":
                            if (farm.get_plots()[row][col].get_crop()
                                    .get_daysPlanted() <= (farm.get_plots()[row][col].get_crop().get_harvestTime()
                                            / 2)) {
                                tileIcon = new ImageIcon(
                                        "resources/FarmTiles/WateredTiles/AppleSeedWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isHarvestable()) {
                                // Fully Grown/Harvestable
                                tileIcon = new ImageIcon("resources/FarmTiles/PlantTiles/ApplePlant.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else if (farm.get_plots()[row][col].get_crop().get_isWithered()) {
                                // Withered
                                tileIcon = new ImageIcon("resources/FarmTiles/WitheredTiles/AppleWithered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            } else {
                                tileIcon = new ImageIcon("resources/FarmTiles/WateredTiles/PlantGrowthWatered.png");
                                img = tileIcon.getImage();
                                img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                                tileIcon = new ImageIcon(img);
                                farmTileClicked.setIcon(tileIcon);
                                farmTileClicked.setDisabledIcon(tileIcon);
                            }
                            break;
                    }
                    message = "<html>Successfully watered crop!";
                    farmer.add_exp(0.5);
                    message += "<br>Gained 0.5 experience.</html>";
                }
                break;
            case "Fertilizer":
                if (farmer.get_wallet().get_amt() < 10) {
                    message = "You don't have enough Objectcoins to use the Fertilizer!";
                    return message;
                }
                farmer.get_wallet().subtract_amt(10);
                message = "<html>Used 10 Objectcoins.";

                if (!farm.get_plots()[row][col].get_isOccupied()) {
                    message += "<br>Chosen plot has no crop planted to fertilize!</html>";
                } else {
                    farm.get_plots()[row][col].increment_timesFertilized(
                            farm.get_plots()[row][col].get_crop().get_maxFertilizerBonusNeed());
                    message += "<br>Successfully fertilized crop!";
                    farmer.add_exp(4);
                    message += "<br>Gained 4 experience.</html>";
                }
                break;
            case "Pickaxe":
                if (farm.get_plots()[row][col].get_hasRock()) {
                    if (farmer.get_wallet().get_amt() < 50) {
                        message = "You don't have enough Objectcoins to use the Pickaxe!";
                        return message;
                    }
                    farmer.get_wallet().subtract_amt(50);
                    message = "<html>Used 50 Objectcoins.";
                    farm.get_plots()[row][col].set_hasRock(false);
                    message += "<br>Successfully removed rock!";
                    ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
                    Image img = tileIcon.getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    tileIcon = new ImageIcon(img);
                    farmTileClicked.setIcon(tileIcon);
                    farmTileClicked.setDisabledIcon(tileIcon);
                    farmer.add_exp(15);
                    message += "<br>Gained 15 experience.</html>";
                } else {
                    message = "Chosen plot has no rock to remove!";
                }
                break;
            case "Shovel":
                if (farmer.get_wallet().get_amt() < 7) {
                    message = "You don't have enough Objectcoins to use the shovel!";
                    return message;
                }
                farmer.get_wallet().subtract_amt(7);
                message = "<html>Used 7 Objectcoins.";
                if (farm.get_plots()[row][col].get_hasRock()) {
                    message += "<br>Unable to shovel chosen plot because it has a rock!</html>";
                } else if (!farm.get_plots()[row][col].get_isPlowed()) {
                    message += "<br>Shoveled an unplowed tile. Nothing happened.</html>";
                } else if (farm.get_plots()[row][col].get_isPlowed() && !farm.get_plots()[row][col].get_isOccupied()) {
                    message += "<br>Shoveled an empty plowed tile. Tile is now unplowed.</html>";
                    farm.get_plots()[row][col].set_isPlowed(false);
                    ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
                    Image img = tileIcon.getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    tileIcon = new ImageIcon(img);
                    farmTileClicked.setIcon(tileIcon);
                    farmTileClicked.setDisabledIcon(tileIcon);
                } else if (farm.get_plots()[row][col].get_isOccupied()
                        && !farm.get_plots()[row][col].get_crop().get_isWithered()) {
                    farm.get_plots()[row][col].remove_crop();
                    ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
                    Image img = tileIcon.getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    tileIcon = new ImageIcon(img);
                    farmTileClicked.setIcon(tileIcon);
                    farmTileClicked.setDisabledIcon(tileIcon);
                    message += "<br>Shoveled an active/growing crop!";
                    farmer.add_exp(0.5);
                    message += "<br>Gained 0.5 experience.</html>";
                } else {
                    farm.get_plots()[row][col].remove_crop();
                    ImageIcon tileIcon = new ImageIcon("resources/FarmTiles/UnplowedTile.png");
                    Image img = tileIcon.getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    tileIcon = new ImageIcon(img);
                    farmTileClicked.setIcon(tileIcon);
                    farmTileClicked.setDisabledIcon(tileIcon);
                    message += "<br>Successfully shoveled a withered crop!";
                    farmer.add_exp(0.5);
                    message += "<br>Gained 0.5 experience.</html>";
                }
                break;

        }
        return message;
    }
}
