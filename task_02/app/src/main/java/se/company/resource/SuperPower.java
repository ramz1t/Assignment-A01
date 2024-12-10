package se.company.resource;

public class SuperPower {
    private String type, description;

    public SuperPower(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String usePower() {
        return String.format("Using superpower: %s - %s.", type, description);
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
