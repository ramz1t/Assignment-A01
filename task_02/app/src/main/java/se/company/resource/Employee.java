package se.company.resource;

public abstract class Employee {
    private static int GLOBAL_ID = 0;

    protected String prefix;
    protected String name;
    protected String work;
    protected int salary;
    public int id;

    public Employee(String name, String work, int salary) {
        this.name = name;
        this.work = work;
        this.salary = salary;
        this.id = getNewEmployeeId();
    }

    @Override
    public String toString() { return ""; }

    public String work() { return ""; }

    public static int getNewEmployeeId() {
        GLOBAL_ID++;
        return GLOBAL_ID;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
