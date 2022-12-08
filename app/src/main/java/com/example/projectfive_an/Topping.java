package com.example.projectfive_an;

/**
 * This enum class is for setting and retrieving the toppings of one pizza.
 * Each enum has a string descriptive topping value that can be retrieved.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREENPEPPERS("GreenPeppers"),
    ONIONS("Onions"),
    MUSHROOMS("Mushrooms"),
    BBQCHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    BLACKOLIVES("Black Olives"),
    SPINACH("Spinach"),
    PINEAPPLES("Pineapples");

    private final String toppingText;

    /**
     * Constructor of Topping enum class. Serve the functions as setting the values to the above enum constants
     *
     * @param toppings The descriptive name of the topping of one pizza.
     */
    Topping(String toppings) {
        this.toppingText = toppings;
    }

    /**
     * Get the string value of one Topping enum constant.
     *
     * @return The String value of one Topping.
     */
    public String getToppingText() {
        return toppingText;
    }
}
