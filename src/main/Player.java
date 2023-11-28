/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

import java.util.HashMap;

/**
 * This class represents the player/farmer who manages the farm and plays the
 * game.
 */
public class Player {
    private double experience; // Tracker for how much experience the player has
    private int bonus_earnings; // Dictates how much more profit player will get from harvesting a crop
    private int seed_cost_reduc; // Dictates how much the base price of a seed will be reduced
    private int current_level; // Tracker for the current level of the player
    private int waterBonusLimitIncrease; // Dictates how much more a player can water a crop
    private int fertilizerBonusLimitIncrease; // Dictates how much more a player can fertilize a crop
    private String farmer_type; // Dictates what "buffs" the player will get (bonus earnings, seed cost
                                // reduction, etc.)
    private Objectcoin wallet; // Holds the Objectcoin class for the player
    private HashMap<String, Integer> seedInventory; // Holds all the seeds that the player has bought and not used

    /**
     * Used to create a Player object
     * 
     * @param seeds An array of all the available seeds in the game
     */
    public Player(String[] seeds) {
        this.experience = 0;
        this.bonus_earnings = 0;
        this.seed_cost_reduc = 0;
        this.current_level = 0;
        this.waterBonusLimitIncrease = 0;
        this.fertilizerBonusLimitIncrease = 0;
        this.farmer_type = "Farmer";
        this.wallet = new Objectcoin();

        // Initialize Inventory
        this.seedInventory = new HashMap<>();
        for (String seed : seeds) {
            seedInventory.put(seed, 0);
        }
    }

    /**
     * Method that returns the value assigned to current_level
     * 
     * @return an integer that dictatest the player's current level
     */
    public int get_lvl() {
        return this.current_level;
    }

    /**
     * Method that returns the class that holds all of the Objectcoins the player
     * has
     * 
     * @return an object that points to the player's monetary finances
     */
    public Objectcoin get_wallet() {
        return this.wallet;
    }

    /**
     * Mathod that returns the value assigned to farmer_type
     * 
     * @return a String that dictates what " type of farmer" the player is
     */
    public String get_farmer_type() {
        return this.farmer_type;
    }

    /**
     * Mathod that returns the value assigned to experience
     * 
     * 
     * @return a double that dictates how much experience the player has
     */
    public double get_experience() {
        return this.experience;
    }

    /**
     * Mathod that returns the value assigned to seed_cost_reduc
     * 
     * @return an integer that dictates how much the base price of a seed will be
     *         reduced
     */
    public int get_seed_cost_reduc() {
        return this.seed_cost_reduc;
    }

    /**
     * Mathod that returns the value assigned to waterBonusLimitIncrease
     * 
     * @return an integer that dictates how much more a player can water a crop
     */
    public int get_waterBonusLimitIncrease() {
        return this.waterBonusLimitIncrease;
    }

    /**
     * Mathod that returns the value assigned to fertilizerBonusLimitIncrease
     * 
     * @return an integer that dictates how much more a player can fertilize a crop
     */
    public int get_fertilizerBonusLimitIncrease() {
        return this.fertilizerBonusLimitIncrease;
    }

    /**
     * Mathod that returns a HashMap assigned to seedInventory
     * 
     * @return hashmap that holds information about the seeds the player owns
     */
    public HashMap<String, Integer> get_seedInventory() {
        return seedInventory;
    }

    /**
     * A method to add to the current experience of the player
     * 
     * @param exp the amount of experience to be added
     */
    public void add_exp(double exp) {
        this.experience += exp;
    }

    /**
     * A method that adds 1 to the current level of the player
     */
    public void increment_lvl() {
        this.current_level += 1;
    }

    /**
     * A method that allows the player to plant a crop/seed they own
     * 
     * @param seed the name of the seed to be planted
     * @param plot the plot/tile at which they have chosen to plant the seed on
     */
    public void plant_seed(String seed, Plot plot) {
        plot.place_seed(seed, this.waterBonusLimitIncrease, this.fertilizerBonusLimitIncrease, this.bonus_earnings);
        plot.set_isOccupied(true);
        this.seedInventory.replace(seed, this.seedInventory.get(seed) - 1);
    }

    /**
     * A method that allows the changeing of farmer type
     * 
     * @param i dictates what type of farmer the player will become according to its
     *          index in the array
     */
    public void change_farmerType(int i) {
        String[] farmerTypes = { "Registered Farmer", "Distinguished Farmer", "Legendary Farmer" };
        if (farmerTypes[i].equals("Registered Farmer")) {
            this.bonus_earnings += 1;
            this.seed_cost_reduc += 1;
        }
        if (farmerTypes[i].equals("Distinguished Farmer")) {
            this.bonus_earnings += 1;
            this.seed_cost_reduc += 1;
            this.waterBonusLimitIncrease += 1;
        }
        if (farmerTypes[i].equals("Legendary Farmer")) {
            this.bonus_earnings += 2;
            this.seed_cost_reduc += 1;
            this.waterBonusLimitIncrease += 1;
            this.fertilizerBonusLimitIncrease += 1;
        }
        this.farmer_type = farmerTypes[i];
    }
}
