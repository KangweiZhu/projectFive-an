package com.example.projectfive_an;

import java.util.ArrayList;

/**
 * This class extends from the Pizza class. It represents the BBQChicken flavor pizza in real life.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class BBQChicken extends Pizza {
    /**
     * Default constructor of BBQChicken Class.
     */
    public BBQChicken() {
    }

    /**
     * Parameterized constructor of BBQChicken Class.
     * Setting up basic attributes of BBQChicken flavor pizza.
     *
     * @param selectedtoppings The ArrayList of toppings that stores the selected toppings.
     * @param crust            The Crust that is used for making this pizza.
     * @param size             The size of this pizza.
     */
    public BBQChicken(ArrayList<Topping> selectedtoppings, Crust crust, Size size) {
        super(selectedtoppings, crust, size);
    }

    /**
     * This method overrides from the Customizable interface.
     * It is used for add one Topping to this pizza.
     *
     * @param obj The Topping that are going to be added.
     * @return True if successfully added, otherwise false.
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
     * This method is override from Customizable interface.
     * It is used when removing a topping from one pizza.
     *
     * @param obj The Topping that are going to be removed.
     * @return true if successfully removed, false otherwise.
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
     * Get the price of BBQChicken Flavor pizza. Different size has different prices
     *
     * @return The double type value of price of BBQ chicken Pizza.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 13.99;
        } else if (this.getSize() == Size.MEDIUM) {
            return 15.99;
        } else if (this.getSize() == Size.LARGE) {
            return 17.99;
        }
        return 0;
    }
}
