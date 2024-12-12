package se.company.resource;

/**
 * Represents a super power that can be possessed by super employees.
 * Each super power has a type and description that characterizes its abilities.
 */
public class SuperPower {
    /** The type/name of this super power */
    final private String type;
    /** A description of what this super power does */
    final private String description;

    /**
     * Creates a new super power with the specified type and description.
     * @param type The type/name of the super power
     * @param description A description of what the super power does
     */
    public SuperPower(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Returns a string describing the use of this super power.
     * @return A formatted string containing the type and description of the power being used
     */
    public String usePower() {
        return String.format("Using superpower: %s - %s.", type, description);
    }

    /**
     * Gets the type/name of this super power.
     * @return The type of the super power
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the description of this super power.
     * @return The description of what the super power does
     */
    public String getDescription() {
        return description;
    }
}
