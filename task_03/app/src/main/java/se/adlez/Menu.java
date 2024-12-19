package se.adlez;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * A command-line menu system that handles user input and executes corresponding actions.
 * The menu supports adding options with descriptions and associated actions, displaying
 * the menu to users, and running an input loop to process user choices.
 */
public class Menu {
    /** Stores menu option descriptions mapped to their keys */
    final private LinkedHashMap<String, String> descriptions = new LinkedHashMap<>();
    /** Stores menu actions mapped to their keys */
    final private LinkedHashMap<String, Runnable> actions = new LinkedHashMap<>();
    /** Scanner for reading user input */
    final private Scanner scanner = new Scanner(System.in);

    /**
     * Adds a new option to the menu.
     * @param key The key/number used to select this option
     * @param description A description of what this option does
     * @param action The Runnable to execute when this option is selected
     */
    public void addOption(String key, String description, Runnable action) {
        descriptions.put(key.toLowerCase(), description);
        actions.put(key.toLowerCase(), action);
    }

    /**
     * Displays the menu options to the user, including all added options
     * plus the standard menu and quit commands.
     */
    public void printMenu() {
        System.out.println("----------------");
        descriptions.forEach((key, value) -> System.out.println("| " + key + ") " + value));
        System.out.println("| m) Print menu");
        System.out.println("| q) Quit");
        System.out.println("----------------");
    }

    /**
     * Prompts the user with a message and returns their input.
     * @param message The prompt message to display
     * @return The user's input as a String
     */
    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Starts the menu system, displaying options and handling user input.
     * Runs in an infinite loop until the user selects quit.
     * Executes the corresponding action when a valid option is selected.
     */
    public void run() {
        printMenu();
        while (true) {
            String choice = prompt("Enter your choice: ").toLowerCase();

            if (choice.equals("q")) {
                System.exit(0);
            } else if (choice.equals("m")) {
                printMenu();
            } else if (actions.containsKey(choice)) {
                actions.get(choice).run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}