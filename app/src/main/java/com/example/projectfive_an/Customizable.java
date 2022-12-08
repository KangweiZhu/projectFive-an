package com.example.projectfive_an;

/**
 * This is the interface that contains two method(add/remove toppings of one pizza) that need to be implemented.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public interface Customizable {
    /**
     * This method is for adding one thing into one ArrayList/array.
     *
     * @param obj The thing that are going to be added.
     * @return true if successfully added, otherwise false.
     */
    boolean add(Object obj);

    /**
     * This method is for removing one thing from one ArrayList/array.
     *
     * @param obj The things that are going to be removed.
     * @return true if successfully removed, otherwise false.
     */
    boolean remove(Object obj);
}
