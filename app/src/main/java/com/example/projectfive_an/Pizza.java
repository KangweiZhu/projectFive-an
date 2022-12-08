package com.example.projectfive_an;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is an abstract class that implements the Customizable interface.
 * It has four child class: Meatzza, Deluxe, BBQChicken and BuildYourOwn.
 * All of these class represent the different flavor of pizza in real life. They might have different topping used,
 * different crust used and so on. That why inheritance is used for Pizza class.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Default constructor of Pizza abstract class.
     */
    public Pizza() {
    }

    /**
     * Parameterized constructor of Pizza abstract class.
     *
     * @param toppings The ArrayList that stores every topping.
     * @param crust    The crust of one specific style/flavor Pizza.
     * @param size     The size of pizza.
     */
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * This abstract method presents the price of one pizza.
     *
     * @return The double type value of price of one pizza.
     */
    public abstract double price();

    /**
     * Get all the preset toppings in Topping enum class.
     *
     * @return The ArrayList that stores Topping enum values.
     */
    public ArrayList<Topping> getAllToppings() {
        Topping[] enumToppings = Topping.values();
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(enumToppings));
        return allToppings;
    }

    /**
     * Get the topping that are selected for this pizza.
     *
     * @return the ArrayList which stores topping that are selected for this pizza.
     */
    public ArrayList<Topping> getSelectedToppings() {
        return this.toppings;
    }

    /**
     * Get the Toppings that we can choose to for this pizza.
     *
     * @return The ArrayList that stores the topping that are available to choose.
     */
    public ArrayList<Topping> getAvailableToppings() {
        ArrayList<Topping> availableToppings = new ArrayList<Topping>();
        for (Topping topping : this.getAllToppings()) {
            if (!this.getSelectedToppings().contains(topping)) {
                availableToppings.add(topping);
            }
        }
        return availableToppings;
    }

    /**
     * Get the crust of this Pizza.
     *
     * @return The Crust Object.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Set the crust that this Pizza uses.
     *
     * @param crust The Crust object that this Pizza is going to use.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Get the size of current Pizza
     *
     * @return The size of current Pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of current Pizza
     *
     * @param size The size of current pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }
}
