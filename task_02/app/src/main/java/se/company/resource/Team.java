package se.company.resource;

import java.time.LocalDate;
import java.util.ArrayList;

public class Team {
    ArrayList<Employee> members;

    public Team() {
        members = new ArrayList<>();
    }

    public void add(Employee emp) {
        members.add(emp);
    }

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
