package se.company.resource;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a team of employees in the company.
 * Teams can contain both regular employees and super employees.
 * Teams can perform collective work activities and generate salary reports.
 */
public class Team {
    /** List of employees that are members of this team */
    private ArrayList<Employee> members;

    /**
     * Creates a new empty team.
     */
    public Team() {
        this.members = new ArrayList<>();
    }

    /**
     * Adds a new employee to the team.
     * @param emp The employee to add to the team
     */
    public void add(Employee emp) {
        members.add(emp);
    }

    /**
     * Returns a string representation of this team.
     * Lists all team members or indicates if the team is empty.
     * @return A formatted string containing the team's details and members
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("# TEAM:\n");

        if (!members.isEmpty()) {
            members.forEach((emp) -> {
                sb.append(" ");
                sb.append(emp.toString());
                sb.append("\n");
            });
        } else {
            sb.append(" (empty team)");
        }

        return sb.toString().trim();
    }

    /**
     * Makes all team members perform their work activities.
     * @return A string describing the work being performed by each team member
     */
    public String work() {
        StringBuilder sb = new StringBuilder();
        sb.append("# TEAM is working:\n");

        members.forEach((emp) -> {
            sb.append(" ");
            sb.append(emp.work());
            sb.append("\n");
        });

        return sb.toString().trim();
    }

    /**
     * Generates a salary report for all team members.
     * The report includes individual salaries and a total sum.
     * @return A formatted report showing salaries and generation date
     */
    public String salaryReport() {
        StringBuilder sb = new StringBuilder();
        LocalDate date = LocalDate.now();
        int total = 0;
        sb.append(String.format("# TEAM Salary %s %s\n", date.getYear(), date.getMonth().toString()));
        sb.append("--------------------------------\n");
        for (Employee emp : members) {
            total += emp.getSalary();
            sb.append(String.format(" (%d) %-21s %5d\n", emp.getId(), emp.getName(), emp.getSalary()));
        }
        sb.append("--------------------------------\n");
        sb.append(String.format("Total salary is %16d\n", total));
        sb.append(String.format("Report generated %s.", date.toString()));
        return sb.toString();
    }
}
