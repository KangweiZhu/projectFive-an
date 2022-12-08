package com.example.projectfive_an;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represent the real life orders that a pizza restaurant database stores.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> ordersArrayList;

    /**
     * Default constructor.
     * It initializes the orderArrayList.
     */
    public StoreOrders() {
        this.ordersArrayList = new ArrayList<>();
    }

    /**
     * Parameterized constructor.
     * It will set the orderArrayList to the orderArrayList in the parameter.
     *
     * @param ordersArrayList The ordersArrayList that is going to be set.
     */
    public StoreOrders(ArrayList<Order> ordersArrayList) {
        this.ordersArrayList = ordersArrayList;
    }

    /**
     * This method is override from the Customizable interface.
     * It will add an order to the current ordersArrayList.
     *
     * @param obj The Thing that are going to be added.
     * @return True if successfully added, otherwise false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            return this.ordersArrayList.add((Order) obj);
        } else {
            return false;
        }
    }

    /**
     * This method is override from the Customizable interface.
     * It will remove an order from current ordersArrayList.
     *
     * @param obj The things that are going to be removed.
     * @return True if successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            return this.ordersArrayList.remove((Order) obj);
        } else {
            return false;
        }
    }

    /**
     * Get the current ordersArrayList.
     *
     * @return The current orderArrayList.
     */
    public ArrayList<Order> getOrdersArrayList() {
        return ordersArrayList;
    }

    /**
     * It will export the current orderArrayList into a text file name exportedStoreOrders.txt
     */
    public void export() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("exportedStoreOrders.txt"));
            writer.write("Store Orders\n\n");
            for (Order order : this.ordersArrayList) {
                writer.write("\nOrder Number : " + order.getOrderNumber() + "   Order Total: " +
                        order.getOrderTotal() + "   Order taxRate: " +order.getTaxRate() + "\n");
                for (String string : order.getPizzaArrayListStringed()) {
                    writer.write("    -"  + string + "\n");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}