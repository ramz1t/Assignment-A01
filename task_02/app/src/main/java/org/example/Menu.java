package org.example;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Menu {
    final private LinkedHashMap<String, String> descriptions = new LinkedHashMap<>();
    final private LinkedHashMap<String, Runnable> actions = new LinkedHashMap<>();
    final private Scanner scanner = new Scanner(System.in);

    public void addOption(String key, String description, Runnable action) {
        descriptions.put(key, description);
        actions.put(key, action);
    }

    public void printMenu() {
        System.out.println("----------------");
        descriptions.forEach((key, value) -> System.out.println("| " + key + ") " + value));
        System.out.println("| m) Print menu");
        System.out.println("| qQ) Quit");
        System.out.println("----------------");
    }

    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void run() {
        printMenu();
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("Q") || choice.equals("q")) {
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