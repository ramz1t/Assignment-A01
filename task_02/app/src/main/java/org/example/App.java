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
    /** The current team being managed */
    private static Team team = new Team();
    /** Menu system for handling user interaction */
    private static Menu menu;

    /**
     * Main entry point of the application.
     * Initializes the menu system and starts the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        menu = new Menu();
        setupMenuOptions();
        menu.run();
    }

    /**
     * Sets up all menu options with their descriptions and associated actions.
     */
    private static void setupMenuOptions() {
        menu.addOption("1", "Create a new empty team", App::createNewTeam);
        menu.addOption("2", "Add normal employee to team", App::addNormalEmployee);
        menu.addOption("3", "Add John, Jane and little Jr", App::addDefaultEmployees);
        menu.addOption("4", "Print out work being done", App::printWork);
        menu.addOption("5", "Add super employee to team", App::addSuperEmployee);
        menu.addOption("6", "Add three super employee, with powers, to team", App::addDefaultSuperEmployees);
        menu.addOption("7", "Salary report", App::printSalaryReport);
    }

    /**
     * Creates a new empty team, replacing any existing team.
     */
    private static void createNewTeam() {
        team = new Team();
        System.out.println(team.toString());
    }

    /**
     * Prompts user for employee details and adds a new normal employee to the team.
     */
    private static void addNormalEmployee() {
        System.out.println("# Create new NormalEmployee");
        String name = menu.prompt(" Enter name: ");
        String work = menu.prompt(" Enter work: ");
        int salary = Integer.parseInt(menu.prompt(" Enter salary: "));
        NormalEmployee emp = new NormalEmployee(name, work, salary);
        team.add(emp);
        System.out.println(team.toString());
    }

    /**
     * Adds three predefined normal employees (John, Jane, and Little Jr) to the team.
     */
    private static void addDefaultEmployees() {
        NormalEmployee john = new NormalEmployee("John", "IT", 1000);
        NormalEmployee jane = new NormalEmployee("Jane", "Design", 950);
        NormalEmployee littleJr = new NormalEmployee("Little Jr", "IDK", 100);
        team.add(john);
        team.add(jane);
        team.add(littleJr);
        System.out.println(team.toString());
    }

    /**
     * Prints out the work being done by all team members.
     */
    private static void printWork() {
        System.out.println(team.work());
    }

    /**
     * Prompts user for employee details and adds a new super employee to the team.
     */
    private static void addSuperEmployee() {
        System.out.println("# Create new SuperEmployee");
        String name = menu.prompt(" Enter name: ");
        String work = menu.prompt(" Enter work: ");
        SuperEmployee emp = new SuperEmployee(name, work, 0);
        team.add(emp);
        System.out.println(team.toString());
    }

    /**
     * Adds three predefined super employees (Superman, Hulk, and Doctor Strange)
     * with their respective super powers to the team.
     */
    private static void addDefaultSuperEmployees() {
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
    }

    /**
     * Prints a salary report for all team members.
     */
    private static void printSalaryReport() {
        System.out.println(team.salaryReport());
    }
}
