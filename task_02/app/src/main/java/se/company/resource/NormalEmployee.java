package se.company.resource;

/**
 * Represents a normal employee in the company.
 * Normal employees have standard work responsibilities and salary.
 * Extends the base Employee class.
 */
public class NormalEmployee extends Employee {
    /**
     * Creates a new normal employee with the specified details.
     * @param name The name of the employee
     * @param work The work role/department of the employee 
     * @param salary The salary of the employee
     */
    public NormalEmployee(String name, String work, int salary) {
        super(name, work, salary);
        this.prefix = "Emp(" + id + "):";
    }

    /**
     * Describes the work being performed by this normal employee.
     * @return A string describing the employee working on a report in their department
     */
    @Override
    public String work() {
        return String.format("%s %s is working on a report in %s", prefix, name, work);
    }

    /**
     * Returns a string representation of this normal employee.
     * @return A string containing the employee's ID prefix, name and work department
     */
    @Override
    public String toString() {
        return String.format("%s %s (%s)", prefix, name, work);
    }
}
