package com.example.projectfive_an;

import java.util.ArrayList;

/**
 * This class extends from Pizza class. Deluxe represents the deluxe flavor pizza.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class Deluxe extends Pizza {
    /**
     * Default constructor of Deluxe class
     */
    public Deluxe() {
    }

    /**
     * Parameterized constructor of Deluxe class.
     * Setting the basic attribute of Deluxe pizza.
     *
     * @param selectedtoppings The ArrayList that stores toppings that are selected.
     * @param crust            The Crust that are used for this pizza.
     * @param size             The Size of this pizza.
     */
    public Deluxe(ArrayList<Topping> selectedtoppings, Crust crust, Size size) {
        super(selectedtoppings, crust, size);
    }

    /**
     * This method is override from the Customizable Interface.
     * It is used for add one topping to one pizza.
     *
     * @param obj The object that are going to be added to the pizza.
     * @return true if it is successfully added, otherwise false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Topping) {
            if (!getAvailableToppings().contains(obj)) {
                return false;
            } else {
                this.getSelectedToppings().add((Topping) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is override from the Customizable Interface.
     * It is used for remove one topping from one pizza.
     *
     * @param obj The Topping that are going to be removed.
     * @return true if the topping is successfully removed. False Otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            if (getAvailableToppings().contains(obj)) {
                return false;
            } else {
                this.getSelectedToppings().remove((Topping) obj);
                return false;
            }
        }
        return false;
    }

    /**
     * This method is override from the Pizza class. It will return different prices for different size of deluxe flavor
     * pizza.
     *
     * @return The double typed value price of this flavor of pizza.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 14.99;
        } else if (this.getSize() == Size.MEDIUM) {
            return 16.99;
        } else if (this.getSize() == Size.LARGE) {
            return 18.99;
        }
        return 0;
    }
}