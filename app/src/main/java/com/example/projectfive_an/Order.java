package com.example.projectfive_an;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * This class represents the pizza order in real life. Each instance of Order class represents one order in real life.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class Order implements Customizable {
    private static int NEXTORDERNUMBER = 0;
    private ArrayList<Pizza> pizzaArrayList;
    private ArrayList<String> pizzaArrayListStringed;
    private int orderNumber = 0;

    /**
     * Default constructor for Order class.
     * It will initialize and set up all the instance variables in this class.
     */
    public Order() {
        this.pizzaArrayList = new ArrayList<>();
        this.pizzaArrayListStringed = new ArrayList<>();
        orderNumber = NEXTORDERNUMBER;
        NEXTORDERNUMBER++;
    }

    /**
     * Parameterized constructor for Order class.
     * It will initialize and set up all the instance variable in this class.
     *
     * @param pizzaArrayList         The pizzaArrayLit in this class will be set to this value.
     * @param pizzaArrayListStringed The pizzaArrayList will be set to this value.
     */
    public Order(ArrayList<Pizza> pizzaArrayList, ArrayList<String> pizzaArrayListStringed) {
        this.pizzaArrayList = pizzaArrayList;
        this.pizzaArrayListStringed = pizzaArrayListStringed;
        orderNumber = NEXTORDERNUMBER;
        NEXTORDERNUMBER++;
    }

    /**
     * This method is override from Customizable interface.
     * It will add the pizza object into the pizzaArrayList.
     *
     * @param obj The pizza that are going to be added.
     * @return True if successfully added, otherwise false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            return pizzaArrayList.add((Pizza) obj);
        } else if (obj instanceof String) {
            return pizzaArrayListStringed.add((String) obj);
        } else {
            return false;
        }
    }

    /**
     * This method is override from Customizable interface.
     * It will remove the pizza obejct from the pizzaArrayList.
     *
     * @param obj The pizza that are going to be removed.
     * @return True if successfully removed, otherwise false.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            return pizzaArrayList.remove((Pizza) obj);
        } else if (obj instanceof String) {
            return pizzaArrayListStringed.remove((String) obj);
        } else {
            return false;
        }
    }

    /**
     * Get the pizzaArrayList.
     *
     * @return The pizzaArrayList.
     */
    public ArrayList<Pizza> getPizzaArrayList() {
        return pizzaArrayList;
    }

    /**
     * Get the String type pizzaArrayList.
     *
     * @return String type pizzaArrayList.
     */
    public ArrayList<String> getPizzaArrayListStringed() {
        return pizzaArrayListStringed;
    }

    /**
     * Get the order number.
     *
     * @return The int type order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Get the subTotal.
     *
     * @return The double type subtotal.
     */
    public double getSubTotal() {
        double subTotal = 0;
        for (Pizza pizza : this.getPizzaArrayList()) {
            subTotal += pizza.price();
        }
        return subTotal;
    }

    /**
     * Get the order total.
     *
     * @return The double type order total.
     */
    public double getOrderTotal() {
        return getSubTotal() + (getSubTotal() * (getTaxRate()));
    }

    /**
     * Get the tax rate.
     *
     * @return The tax rate.
     */
    public double getTaxRate() {
        return (6.625 / 100);
    }
}
