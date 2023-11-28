/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

/**
 * This class represents each tile of workable area a player can access in
 * the game. This includes plowing, planting, watering, etc.
 */
public class Plot {
    private boolean hasRock; // Dictates if plot has a rock present or not
    private boolean isPlowed; // Dictates if plot is plowed or not
    private boolean isOccupied; // Dictates if plot is occupied by a crop (may be active or withered
    private boolean wateredToday; // Dictates if plot was watered today or not
    private int timesWatered; // Counter for how many times the plot has been watered
    private int timesFertilized; // Counter for how many times the plot has been fertilized
    private Crop crop; // Attribute to hold the information about a crop once planted

    /**
     * Create a plot object by indicating whether or not it has
     * a rock present
     * 
     * @param hasRock dictates if plot of land has a rock or not
     */
    Plot(boolean hasRock) {
        this.hasRock = hasRock;
        this.isPlowed = false;
        this.isOccupied = false;
        this.timesWatered = 0;
        this.timesFertilized = 0;
        this.wateredToday = false;
    }

    /**
     * Method that returns the value assigned to hasRock
     * 
     * @return true if there is a rock present and false otherwise
     */
    public boolean get_hasRock() {
        return this.hasRock;
    }

    /**
     * Method that returns the value assigned to isPlowed
     * 
     * @return true if it is plowed and false otherwise
     */
    public boolean get_isPlowed() {
        return this.isPlowed;
    }

    /**
     * Method that returns the value assigned to isOccupied
     * 
     * @return true if there is a plant present and false otherwise
     */
    public boolean get_isOccupied() {
        return this.isOccupied;
    }

    /**
     * Method that returns the value assigned to timesWatered
     * 
     * @return an integer value indicating the times the watering can was used
     *         on this plot. This is upper-bounded by a crop's maxWaterBonusNeed
     */
    public int get_timesWatered() {
        return this.timesWatered;
    }

    /**
     * Method that returns the value assigned to timesFertilized
     * 
     * @return an integer value indicating the times the fertilizer was used
     *         on this plot. This is upper-bounded by a crop's
     *         maxFertilizerBonusNeed
     */
    public int get_timesFertilized() {
        return this.timesFertilized;
    }

    /**
     * Method that returns the value assigned to wateredToday
     * 
     * @return a boolean indicating if the plot was watered
     *         today or not
     */
    public boolean get_wateredToday() {
        return this.wateredToday;
    }

    /**
     * Method that returns the value assigned to crop
     * 
     * @return crop object planted on the tile/plot
     */
    public Crop get_crop() {
        return this.crop;
    }

    /**
     * Method that sets the value for isPlowed
     * 
     * @param a true if the plot was successfully plowed and
     *          false if plot just had a plant harvested
     */
    public void set_isPlowed(boolean a) {
        this.isPlowed = a;
    }

    /**
     * Method that sets the value for isOccupied
     * 
     * @param a true if the plot was successfully planted on and
     *          false if plot just had a plant harvested
     */
    public void set_isOccupied(boolean a) {
        this.isOccupied = a;
    }

    /**
     * Method that sets the value for wateredToday
     * 
     * @param a true if the plot was succesfully watered false if
     *          the day was ended
     */
    public void set_wateredToday(boolean a) {
        this.wateredToday = a;
    }

    /**
     * Method that sets the value for hasRock
     * 
     * @param a true if the rock was succesfully removed false if
     *          otherwise
     */
    public void set_hasRock(boolean a) {
        this.hasRock = a;
    }

    /**
     * Method that adds 1 to the timesWatered attribute every time it is called
     * 
     * @param maxWaterBonusNeed dictates the max value assignable to timesWatered
     */
    public void increment_timesWatered(int maxWaterBonusNeed) {
        if (this.timesWatered < maxWaterBonusNeed) {
            this.timesWatered += 1;
        }

    }

    /**
     * Method that adds 1 to the timesFertilized attribute every time it is called
     * 
     * @param maxFertilizerBonusNeed dictates the max value assignable to
     *                               timesFertilized
     */
    public void increment_timesFertilized(int maxFertilizerBonusNeed) {
        if (this.timesFertilized < maxFertilizerBonusNeed) {
            this.timesFertilized += 1;
        }
    }

    /**
     * Method to revert a plot to its unplowed state (no crop, timesWatered and
     * timesFertilized is reset to 0, etc)
     */
    public void remove_crop() {
        this.isOccupied = false;
        this.isPlowed = false;
        this.wateredToday = false;
        this.timesWatered = 0;
        this.timesFertilized = 0;
        this.crop = null;
    }

    /**
     * Method that creates a crop object to be stored in this plots' crop attribute
     * according to the chosen seed of the player.
     * 
     * @param seed                         A string that dictates what type of
     *                                     crop/seed will be created
     * 
     * @param waterBonusLimitIncrease      An integer from the Player class that
     *                                     holds the special water bonus limit
     *                                     increase according to the player's farmer
     *                                     type
     * 
     * @param fertilizerBonusLimitIncrease An integer from the Player class that
     *                                     holds the special fertilizer bonus limit
     *                                     increase according to the player's farmer
     *                                     type
     * 
     * @param bonus_earnings               An integer from the Player class that
     *                                     dictates how much more profit player will
     *                                     get from harvesting a crop
     */
    public void place_seed(String seed, int waterBonusLimitIncrease, int fertilizerBonusLimitIncrease,
            int bonus_earnings) {
        this.crop = new Crop(seed, waterBonusLimitIncrease, fertilizerBonusLimitIncrease, bonus_earnings);
    }
}
