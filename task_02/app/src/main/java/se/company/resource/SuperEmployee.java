package se.company.resource;

import java.util.ArrayList;

public class SuperEmployee extends Employee {
    ArrayList<SuperPower> powers;
    
    public SuperEmployee(String name, String work, int salary) {
        super(name, work, salary);
        powers = new ArrayList<>();
        this.prefix = "Sup(" + id + "): ";
    }

    public void addPower(SuperPower power) {
        powers.add(power);
    }

    @Override
    public String work() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s is saving the day in %s", prefix, name, work));
        if (!powers.isEmpty()) {
            powers.forEach((power) -> sb.append(String.format("\n    %s", power.usePower())));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s %s (%s)", prefix, name, work));
        if (!powers.isEmpty()) {
            sb.append(" - ");
            for (int i = 0; i < powers.size(); i++) {
                sb.append(powers.get(i).getType());
                if (i != powers.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        
        return sb.toString();
    }
}
