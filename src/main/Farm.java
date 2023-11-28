/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

/**
 * A class representing the whole farm area that the Player owns/play on
 */
public class Farm {
    private Plot[][] plots; // The field that the player can access
    private int day_ctr; // The current day count
    private boolean isRaining; // Dictates whether or not it is raining

    public Farm() {
        this.plots = new Plot[5][10];
        this.day_ctr = 0;
        this.isRaining = false;
    }

    /**
     * A method that fills each plots array index with a plot object
     * 
     * @param array a 2D boolean array that dictates whether or not will have a
     *              rock.
     *              Array is created from file input chosen at the start of the
     *              game.
     */
    public void setup(boolean[][] array) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Plot new_plot = new Plot(array[i][j]);
                this.plots[i][j] = new_plot;
            }
        }
    }

    public void increment_dayCtr() {
        this.day_ctr += 1;
    }

    /**
     * A method that returns the array Plot
     * 
     * @return the whole play area the player can access
     */
    public Plot[][] get_plots() {
        return this.plots;
    }

    /**
     * A method that returns the value assigned to day_ctr
     * 
     * @return the whole play area the player can access
     */
    public int getDay_ctr() {
        return day_ctr;
    }

    /**
     * A method that returns the value assigned to isRaining
     * 
     * @return a boolean that dictates whether or not it is raining
     */
    public boolean get_isRaining() {
        return isRaining;
    }

    /**
     * A method to set a value to the attribute isRaining
     * 
     * @param a true if it is raining and false otherwise
     */
    public void set_isRaining(boolean a) {
        this.isRaining = a;
    }
}
