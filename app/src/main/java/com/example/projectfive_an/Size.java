package com.example.projectfive_an;

/**
 * This enum class is for setting and getting one Pizza object's size value.
 * Each enum constants has a String type value which can be retrieved
 *
 * @author Michael Israel, Kangwei Zhu
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String sizeText;

    /**
     * The constructor method of Size enum class that only takes one parameter, which is String typed size. And will
     * automatically create the enum constants above with the String size given to them.
     *
     * @param size The size of pizza
     */
    Size(String size) {
        this.sizeText = size;
    }

    /**
     * Get the String type value of current Size enum value.
     *
     * @return The String type value of size.
     */
    public String getSizeText() {
        return sizeText;
    }
}
