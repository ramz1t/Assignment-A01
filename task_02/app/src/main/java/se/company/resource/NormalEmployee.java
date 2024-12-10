package se.company.resource;

public class NormalEmployee extends Employee {
    public NormalEmployee(String name, String work, int salary) {
        super(name, work, salary);
        this.prefix = "Emp(" + id + "):";
    }

    @Override
    public String work() {
        return String.format("%s %s is working on a report in %s", prefix, name, work);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", prefix, name, work);
    }
}
