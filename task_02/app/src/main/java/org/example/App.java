/**
 * Main application class for managing teams of employees.
 * This class provides a command-line interface to create and manage teams
 * consisting of both normal employees and super employees with special powers.
 * 
 * The application allows users to:
 * - Create new teams
 * - Add normal employees with name, work role, and salary
 * - Add super employees with special powers
 * - View team composition
 * - Generate work and salary reports
 * 
 * @author Timur Ramazanov
 */
package org.example;
import se.company.resource.NormalEmployee;
import se.company.resource.SuperEmployee;
import se.company.resource.SuperPower;
import se.company.resource.Team;

public class App {
    private static Team team = new Team();

    public static void main(String[] args) {
        Menu menu = new Menu();
        
        menu.addOption("1", "Create a new empty team", () -> {
            team = new Team();
            System.out.println(team.toString());
        });

        menu.addOption("2", "Add normal employee to team", () -> {
            System.out.println("# Create new NormalEmployee");
            String name = menu.prompt(" Enter name: ");
            String work = menu.prompt(" Enter work: ");
            int salary = Integer.parseInt(menu.prompt(" Enter salary: "));
            NormalEmployee emp = new NormalEmployee(name, work, salary);
            team.add(emp);
            System.out.println(team.toString());
        });

        menu.addOption("3", "Add John, Jane and little Jr", () -> {
            NormalEmployee john = new NormalEmployee("John", "IT", 1000);
            NormalEmployee jane = new NormalEmployee("Jane", "Design", 950);
            NormalEmployee littleJr = new NormalEmployee("Little Jr", "IDK", 100);
            team.add(john);
            team.add(jane);
            team.add(littleJr);
            System.out.println(team.toString());
        });

        menu.addOption("4", "Print out work being done", () -> System.out.println(team.work()));

        menu.addOption("5", "Add super employee to team", () -> {
            System.out.println("# Create new SuperEmployee");
            String name = menu.prompt(" Enter name: ");
            String work = menu.prompt(" Enter work: ");
            SuperEmployee emp = new SuperEmployee(name, work, 0);
            team.add(emp);
            System.out.println(team.toString());
        });

        menu.addOption("6", "Add three super employee, with powers, to team", () -> {
            SuperPower flight = new SuperPower("Flight", "Fly at supersonic speeds");
            SuperPower invisibility = new SuperPower("Invisibility", "Become invisible to the naked eye");
            SuperPower speed = new SuperPower("Speed", "Run with speed of sound");

            SuperEmployee superman = new SuperEmployee("Superman", "Security", 0);
            superman.addPower(flight);
            superman.addPower(speed);

            SuperEmployee hulk = new SuperEmployee("Hulk", "Avengers", 0);
            hulk.addPower(speed);

            SuperEmployee doctorStrange = new SuperEmployee("Doctor Strange", "Magic", 0);
            doctorStrange.addPower(flight);
            doctorStrange.addPower(invisibility);

            team.add(superman);
            team.add(hulk);
            team.add(doctorStrange);
            System.out.println(team.toString());
        });

        menu.addOption("7", "Salary report", () -> System.out.println(team.salaryReport()));

        menu.run();
    }
}