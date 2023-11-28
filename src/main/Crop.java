/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

/**
 * This class represents the seed/crop that will be planted in the farm.
 */
public class Crop {
    private String seedName; // String that holds the name of the seed
    private String cropType; // Dictates the type of crop
    private int harvestTime; // Dictates length of time before a crop is harvestable
    private int daysPlanted; // Counter for the number of days a crop has been planted
    private int waterNeed; // Dictates how many times a crop should be watered for it to grow healthy
    private int fertilizerNeed; // Dictates how many times a crop should be fertilized for it to grow healthy
    private int maxProduce; // Dictates the maximum number of produce a crop can return on harvest
    private int minProduce; // Dictates the minimum number of produce a crop can return on harvest
    private int producePrice; // Dictates the price of the crop's produce per unit; it also takes into account
                              // player's bonus earnings (according to farmer type)
    private int maxWaterBonusNeed; // Dictates the maximum times a crop is waterable; takes into account player's
                                   // water bonus limit increase (according to farmer type)
    private int maxFertilizerBonusNeed;// Dictates the maximum times a crop is fertilizable; takes into account
                                       // player's fertilizer bonus limit increase (according to farmer type)
    private double expYield; // Dictates how much experience the player will earn once the crop is harvested
    private boolean isWithered; // Dictates whether or not the crop has withered
    private boolean isHarvestable; // Dictates whether or not the crop is harvestable

    /**
     * Used to create a crop object
     * 
     * @param seed                         The name of the crop which will also
     *                                     dictate the crop's attributes
     * @param waterBonusLimitIncrease      Player's "buff" to waterBonusLimit
     *                                     according to farmer type
     * @param fertilizerBonusLimitIncrease Player's "buff" to
     *                                     fertilizerBonusLimit according to farmer
     *                                     type
     * @param bonus_earnings               Player's added income per unit produce
     *                                     according to farmer type
     */
    Crop(String seed, int waterBonusLimitIncrease, int fertilizerBonusLimitIncrease, int bonus_earnings) {

        switch (seed) {
            case "Turnip":
                this.seedName = seed;
                this.cropType = "Root";
                this.harvestTime = 2;
                this.daysPlanted = 0;
                this.waterNeed = 1;
                this.fertilizerNeed = 0;
                this.maxProduce = 2;
                this.minProduce = 1;
                this.producePrice = 6 + bonus_earnings;
                this.maxWaterBonusNeed = 2 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 1 + fertilizerBonusLimitIncrease;
                this.expYield = 5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Carrot":
                this.seedName = seed;
                this.cropType = "Root";
                this.harvestTime = 3;
                this.daysPlanted = 0;
                this.waterNeed = 1;
                this.fertilizerNeed = 0;
                this.maxProduce = 2;
                this.minProduce = 1;
                this.producePrice = 9 + bonus_earnings;
                this.maxWaterBonusNeed = 2 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 1 + fertilizerBonusLimitIncrease;
                this.expYield = 7.5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Potato":
                this.seedName = seed;
                this.cropType = "Root";
                this.harvestTime = 5;
                this.daysPlanted = 0;
                this.waterNeed = 3;
                this.fertilizerNeed = 1;
                this.maxProduce = 10;
                this.minProduce = 1;
                this.producePrice = 3 + bonus_earnings;
                this.maxWaterBonusNeed = 4 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 2 + fertilizerBonusLimitIncrease;
                this.expYield = 12.5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Rose":
                this.seedName = seed;
                this.cropType = "Flower";
                this.harvestTime = 1;
                this.daysPlanted = 0;
                this.waterNeed = 1;
                this.fertilizerNeed = 0;
                this.maxProduce = 1;
                this.minProduce = 1;
                this.producePrice = 5 + bonus_earnings;
                this.maxWaterBonusNeed = 2 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 1 + fertilizerBonusLimitIncrease;
                this.expYield = 2.5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Tulips":
                this.seedName = seed;
                this.cropType = "Flower";
                this.harvestTime = 2;
                this.daysPlanted = 0;
                this.waterNeed = 2;
                this.fertilizerNeed = 0;
                this.maxProduce = 1;
                this.minProduce = 1;
                this.producePrice = 9 + bonus_earnings;
                this.maxWaterBonusNeed = 3 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 1 + fertilizerBonusLimitIncrease;
                this.expYield = 5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Sunflower":
                this.seedName = seed;
                this.cropType = "Flower";
                this.harvestTime = 3;
                this.daysPlanted = 0;
                this.waterNeed = 2;
                this.fertilizerNeed = 1;
                this.maxProduce = 1;
                this.minProduce = 1;
                this.producePrice = 19 + bonus_earnings;
                this.maxWaterBonusNeed = 3 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 2 + fertilizerBonusLimitIncrease;
                this.expYield = 7.5;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Mango":
                this.seedName = seed;
                this.cropType = "Fruit tree";
                this.harvestTime = 10;
                this.daysPlanted = 0;
                this.waterNeed = 7;
                this.fertilizerNeed = 4;
                this.maxProduce = 15;
                this.minProduce = 5;
                this.producePrice = 8 + bonus_earnings;
                this.maxWaterBonusNeed = 7 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 4 + fertilizerBonusLimitIncrease;
                this.expYield = 25;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
            case "Apple":
                this.seedName = seed;
                this.cropType = "Fruit tree";
                this.harvestTime = 10;
                this.daysPlanted = 0;
                this.waterNeed = 7;
                this.fertilizerNeed = 5;
                this.maxProduce = 15;
                this.minProduce = 10;
                this.producePrice = 5 + bonus_earnings;
                this.maxWaterBonusNeed = 7 + waterBonusLimitIncrease;
                this.maxFertilizerBonusNeed = 5 + fertilizerBonusLimitIncrease;
                this.expYield = 25;
                this.isWithered = false;
                this.isHarvestable = false;
                break;
        }
    }

    /**
     * Method that returns value assigned to seedName
     * 
     * @return a String that dictates the crop's name
     */
    public String get_seedName() {
        return this.seedName;
    }

    /**
     * Method that returns value assigned to cropType
     * 
     * @return a String that dictates the type of crop
     */
    public String get_cropType() {
        return this.cropType;
    }

    /**
     * Method that returns value assigned to daysPlanted
     * 
     * @return an integer that dictates the length of time a crop has been
     *         planted for
     */
    public int get_daysPlanted() {
        return daysPlanted;
    }

    /**
     * Method that returns value assigned to isWithered
     * 
     * @return true if crop is withered, false otherwise
     */
    public boolean get_isWithered() {
        return this.isWithered;
    }

    /**
     * Method that returns value assigned to isHarvestable
     * 
     * @return true if the crop was grown successfully, false otherwise
     */
    public boolean get_isHarvestable() {
        return this.isHarvestable;
    }

    /**
     * Method that returns value assigned to maxProduce
     * 
     * @return an integer that dictates the maximum amount of produce the crop can
     *         return
     */
    public int get_maxProduce() {
        return this.maxProduce;
    }

    /**
     * Method that returns value assigned to minProduce
     * 
     * @return an integer that dictates the minimum amount of produce the crop can
     *         return
     */
    public int get_minProduce() {
        return this.minProduce;
    }

    /**
     * Method that returns value assigned to producePrice
     * 
     * @return an integer that dictates the price of the crop's produce per unit; it
     *         also takes into account player's bonus earnings (according to farmer
     *         type)
     */
    public int get_producePrice() {
        return this.producePrice;
    }

    /**
     * Method that returns value assigned to maxWaterBonusNeed
     * 
     * @return an integer that dictates the maximum times a crop is waterable; takes
     *         into account player's water bonus limit increase (according to farmer
     *         type)
     */
    public int get_maxWaterBonusNeed() {
        return this.maxWaterBonusNeed;
    }

    /**
     * Method that returns value assigned to maxFertilzierBonusNeed
     * 
     * @return an integer that dictates the maximum times a crop is fertilizable;
     *         takes
     *         into account player's fertilizer bonus limit increase (according to
     *         farmer
     *         type)
     */
    public int get_maxFertilizerBonusNeed() {
        return this.maxFertilizerBonusNeed;
    }

    /**
     * Method that returns value assigned to expYield
     * 
     * @return a double that dictates how much experience the player will earn once
     *         the crop is harvested
     */
    public double get_expYield() {
        return this.expYield;
    }

    /**
     * Method that returns value assigned to harvestTime
     * 
     * @return an integer that dictates the length of time before a crop is
     *         harvestable
     */
    public int get_harvestTime() {
        return harvestTime;
    }

    /**
     * Method that increments the crop's daysPlanted attribute by 1. Also checks if
     * the crop was grown properly (provided enough water and fertilizer) and is
     * therefore harvestable or withered otherwise. Also checks if the crop has
     * passed its harvest day and changes isWithered to true if it has.
     * 
     * @param plot the tile the crop is planted on
     */
    public void grow_up(Plot plot) {
        this.daysPlanted += 1;
        if (this.daysPlanted == harvestTime) {
            if (this.waterNeed <= plot.get_timesWatered() && this.fertilizerNeed <= plot.get_timesFertilized()) {
                this.isHarvestable = true;
            } else {
                this.isHarvestable = false;
                this.isWithered = true;
            }
        }
        if (this.daysPlanted > harvestTime) {
            this.isHarvestable = false;
            this.isWithered = true;
        }
    }

    @Override
    /**
     * Method that returns a String containing information about this object's
     * attributes.
     */
    public String toString() {
        String information = new String();
        information += "Seed: " + this.seedName + "<br>" + "Type: " + this.cropType + "<br>";
        information += "Harvest Time: " + this.harvestTime + "<br>" + "Days Planted: " + this.daysPlanted + "<br>";
        information += "Water Need: " + this.waterNeed + "<br>" + "Fertilizer Need: " + this.fertilizerNeed + "<br>";
        information += "Produce: " + this.minProduce + "-" + this.maxProduce + " units" + "<br>" + "Produce Price: "
                + this.producePrice + "<br>";
        information += "Water Bonus Need: " + this.maxWaterBonusNeed + "<br>" + "Fertilizer Bonus Need: "
                + this.maxFertilizerBonusNeed + "<br>";
        information += "Experience Yield: " + this.expYield + "<br>" + "Withered: " + this.isWithered + "<br>"
                + "Harvestable: " + this.isHarvestable + "<br>";

        return information;
    }
}
