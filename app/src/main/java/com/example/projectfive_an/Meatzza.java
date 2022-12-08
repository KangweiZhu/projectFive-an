package com.example.projectfive_an;

import java.util.ArrayList;

/**
 * This class extends from the Pizza class. It represents the Meatzza flavor pizza in real life.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class Meatzza extends Pizza {
    /**
     * Default constructor of Meatzza class
     */
    public Meatzza() {
    }

    /**
     * Parameterized constructor of Meatzza class.
     * Setting up basic attribute of Meatzza flavor pizza.
     *
     * @param selectedtoppings The ArrayList that stores the selected toppings.
     * @param crust            The Crust used for creating this pizza.
     * @param size             The Size of this pizza.
     */
    public Meatzza(ArrayList<Topping> selectedtoppings, Crust crust, Size size) {
        super(selectedtoppings, crust, size);
    }

    /**
     * This method is override from the Customization Interface.
     * It is used for adding a topping to the current pizza.
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
     * This method is override from the Customizable interface.
     * It is used for removing a topping from current pizza.
     *
     * @param obj The Topping that are going to be removed.
     * @return True if successfully removed, otherwise false.
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
     * This price is override from the parent Pizza class. It returns the price of different sizes of Meatzza.
     *
     * @return The double type value of price.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 15.99;
        } else if (this.getSize() == Size.MEDIUM) {
            return 17.99;
        } else if (this.getSize() == Size.LARGE) {
            return 19.99;
        }
        return 0;
    }
}
