/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package main;

/**
 * A class that represents a "wallet" that the player owns.
 */
public class Objectcoin {
    private double amount; // A value indicating how much the player has

    /**
     * Used to create an Objectcoin object
     */
    Objectcoin() {
        this.amount = 100;
    }

    /**
     * A method that returns the value assigned to amount
     * 
     * @return a double dictating how much the player has in their "wallet"
     */
    public double get_amt() {
        return this.amount;
    }

    /**
     * A method that allows adding to the current amount given another amount
     * 
     * @param amt number of objectcoins to be added to the "wallet"
     */
    public void add_amt(double amt) {
        this.amount += amt;
        return;
    }

    /**
     * A method that allows subtracting to the current amount given another amount
     * 
     * @param amt number of objectcoins to be subtracted to the "wallet"
     */
    public void subtract_amt(double amt) {
        this.amount -= amt;
        return;
    }
}
