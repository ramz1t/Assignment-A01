package se.company.resource;

/**
 * Abstract base class representing an employee in the company.
 * Provides common functionality and properties for all employee types.
 */
public abstract class Employee {
    /** Counter for generating unique employee IDs */
    private static int GLOBAL_ID = 0;

    /** Prefix used when displaying employee information */
    protected String prefix;
    /** Name of the employee */
    protected String name;
    /** Work role/department of the employee */
    protected String work;
    /** Salary of the employee */
    protected int salary;
    /** Unique identifier for the employee */
    public int id;

    /**
     * Creates a new employee with the specified details.
     * @param name The name of the employee
     * @param work The work role/department of the employee
     * @param salary The salary of the employee
     */
    public Employee(String name, String work, int salary) {
        this.name = name;
        this.work = work;
        this.salary = salary;
        this.id = getNewEmployeeId();
    }

    /**
     * Returns a string representation of the employee.
     * @return String representation of employee details
     */
    @Override
    public String toString() { return ""; }

    /**
     * Describes the work being performed by the employee.
     * @return Description of employee's work activity
     */
    public String work() { return ""; }

    /**
     * Generates and returns a new unique employee ID.
     * @return New unique employee ID
     */
    public static int getNewEmployeeId() {
        GLOBAL_ID++;
        return GLOBAL_ID;
    }

    /**
     * Gets the name of the employee.
     * @return Employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the salary of the employee.
     * @return Employee's salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Gets the unique ID of the employee.
     * @return Employee's ID
     */
    public int getId() {
        return id;
    }
}
