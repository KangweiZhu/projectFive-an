package com.example.projectfive_an;

import java.util.ArrayList;

/**
 * This class follows the abstract factory design pattern. It implements all the method from PizzaFactory interface.
 * It will create different flavors pizza.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class NYPizza implements PizzaFactory {
    /**
     * Default constructor of NewYorkPizza class.
     */
    public NYPizza() {
    }

    /**
     * This method is override from the PizzaFactory interface. It will set up basic attributes of a Deluxe flavor pizza.
     *
     * @return The Pizza type deluxe flavor pizza.
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.BROOKLYN;
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
     * This method is override from the PizzaFactory interface. It will set up basic attributes of a BBQChicken
     * flavor pizza.
     *
     * @return The Pizza type BBQChicken flavor pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.THIN;
        Size size = Size.LARGE;
        selectedToppings.add(Topping.BBQCHICKEN);
        selectedToppings.add(Topping.GREENPEPPERS);
        selectedToppings.add(Topping.PROVOLONE);
        selectedToppings.add(Topping.CHEDDAR);
        BBQChicken pizza = new BBQChicken(selectedToppings, crust, size);
        return pizza;
    }

    /**
     * This method is override from the PizzaFactory interface. It will set up basic attributes of a Meatzza flavor
     * pizza.
     *
     * @return The Pizza type Meatzza flavor pizza.
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.HANDTOSSED;
        Size size = Size.LARGE;
        selectedToppings.add(Topping.SAUSAGE);
        selectedToppings.add(Topping.PEPPERONI);
        selectedToppings.add(Topping.BEEF);
        selectedToppings.add(Topping.HAM);
        Meatzza pizza = new Meatzza(selectedToppings, crust, size);
        return pizza;
    }

    /**
     * This method is override from the PizzaFactory interface. It will set up basic attributes of a BuildYourOwn
     * flavor pizza.
     *
     * @return The Pizza type BuildYourOwn flavor pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> selectedToppings = new ArrayList<Topping>();
        Crust crust = Crust.HANDTOSSED;
        Size size = Size.LARGE;
        BuildYourOwn pizza = new BuildYourOwn(selectedToppings, crust, size);
        return pizza;
    }
}
