package com.example.projectfive_an;

import java.util.ArrayList;

/**
 * This class implements methods from PizzaFactory interface, and will create different flavor of pizza
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * Default Constructor of ChicagoPizza class.
     */
    public ChicagoPizza() {
    }

    /**
     * This method is override from the PizzaFactory interface. It creates a deluxe flavor Chicago Pizza.
     *
     * @return The Deluxe flavor chicago pizza create
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.DEEPDISH;
        Size size = Size.LARGE;
        selectedToppings.add(Topping.SAUSAGE);
        selectedToppings.add(Topping.PEPPERONI);
        selectedToppings.add(Topping.GREENPEPPERS);
        selectedToppings.add(Topping.ONIONS);
        selectedToppings.add(Topping.MUSHROOMS);
        Deluxe pizza = new Deluxe(selectedToppings, crust, size);
        return pizza;
    }

    /**
     * This method is override from the PizzaFactory interface. It creates a BBQ Chicken flavor Chicago Pizza.
     *
     * @return The BBQ Chicken flavor chicago pizza create
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.PAN;
        Size size = Size.LARGE;
        selectedToppings.add(Topping.BBQCHICKEN);
        selectedToppings.add(Topping.GREENPEPPERS);
        selectedToppings.add(Topping.PROVOLONE);
        selectedToppings.add(Topping.CHEDDAR);
        BBQChicken pizza = new BBQChicken(selectedToppings, crust, size);
        return pizza;
    }

    /**
     * This method is override from the PizzaFactory interface. It creates a Meatzza flavor Chicago Pizza.
     *
     * @return The Meatzza flavor chicago pizza create
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.STUFFED;
        Size size = Size.LARGE;
        selectedToppings.add(Topping.SAUSAGE);
        selectedToppings.add(Topping.PEPPERONI);
        selectedToppings.add(Topping.BEEF);
        selectedToppings.add(Topping.HAM);
        Meatzza pizza = new Meatzza(selectedToppings, crust, size);
        return pizza;
    }

    /**
     * This method is override from the PizzaFactory interface. It creates a build your own flavor Chicago Pizza.
     *
     * @return The build your own flavor chicago pizza create
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.PAN;
        Size size = Size.LARGE;
        BuildYourOwn pizza = new BuildYourOwn(selectedToppings, crust, size);
        return pizza;
    }
}