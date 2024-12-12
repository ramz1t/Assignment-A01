package se.company.resource;

import java.util.ArrayList;

/**
 * Represents a super employee with special powers in the company.
 * Super employees extend normal employees with the ability to have and use super powers.
 * They have a special "Sup" prefix and can perform heroic work activities.
 */
public class SuperEmployee extends Employee {
    /** List of super powers this employee possesses */
    ArrayList<SuperPower> powers;
    
    /**
     * Creates a new super employee with the specified details.
     * @param name The name of the employee
     * @param work The work role/department of the employee
     * @param salary The salary of the employee
     */
    public SuperEmployee(String name, String work, int salary) {
        super(name, work, salary);
        this.powers = new ArrayList<>();
        this.prefix = "Sup(" + id + "): ";
    }

    /**
     * Adds a new super power to this employee's list of powers.
     * @param power The super power to add
     */
    public void addPower(SuperPower power) {
        powers.add(power);
    }

    /**
     * Describes the heroic work being performed by this super employee.
     * Includes descriptions of all super powers being used if any exist.
     * @return A string describing the employee saving the day and using their powers
     */
    @Override
    public String work() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s is saving the day in %s", prefix, name, work));
        if (!powers.isEmpty()) {
            powers.forEach((power) -> sb.append(String.format("\n    %s", power.usePower())));
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of this super employee.
     * Includes their prefix, name, work department and list of super power types if any exist.
     * @return A formatted string containing the employee's details and powers
     */
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
