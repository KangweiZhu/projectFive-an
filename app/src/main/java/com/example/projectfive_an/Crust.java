package com.example.projectfive_an;

/**
 * This enum class is for setting and getting the crust that used for making one pizza.
 * Each crust constants have a string type value can be retrieved.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public enum Crust {
    DEEPDISH("Deep Dish"),
    BROOKLYN("Brooklyn"),
    PAN("Pan"),
    THIN("Thin"),
    STUFFED("Stuffed"),
    HANDTOSSED("Hand Tossed");

    private final String crustText;

    /**
     * The constructor of Crust enum class. Automatically create and setting values to these enum constants above.
     *
     * @param crust The String type value that are going to be set.
     */
    Crust(String crust) {
        this.crustText = crust;
    }

    /**
     * Get the name of Crust used for one pizza.
     *
     * @return The String type name of pizza.
     */
    public String getCrustText() {
        return crustText;
    }

}
