package com.example.projectfive_an;

/**
 * This interface follows the concept of abstract factory design pattern.
 * It will create different flavor pizza.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public interface PizzaFactory {
    /**
     * Abstract Factory Design Pattern, create a Deluxe flavor Pizza.
     *
     * @return A Deluxe type pizza.
     */
    Pizza createDeluxe();

    /**
     * Abstract Factory Design Pattern, create a Meatzza flavor Pizza
     *
     * @return A Meatzza type pizza.
     */
    Pizza createMeatzza();

    /**
     * Abstract Factory Design Pattern, create a BBQ Chicken flavor Pizza
     *
     * @return A BBQChicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Abstract Factory Design Pattern, create a Build Your Own flavor Pizza
     *
     * @return A Build Your Own pizza
     */
    Pizza createBuildYourOwn();
}

