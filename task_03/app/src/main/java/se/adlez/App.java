package se.adlez;
import java.io.IOException;
import java.util.Random;

import se.adlez.game.*;

/**
 * Main application class that manages the forest game.
 * Provides menu-driven interface for interacting with the game,
 * including creating forests, adding items, saving/loading game state,
 * and playing the game.
 */
public class App {
    /** The menu system for user interaction */
    private static Menu menu;
    /** The forest game environment */
    private static Forest forest;

    /**
     * Private constructor to prevent instantiation
     */
    private App() {
        // Private constructor to prevent instantiation
    }

    /**
     * Main entry point of the application.
     * Initializes the forest and menu system, then starts the game loop.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        forest = new Forest();
        forest.init();
        menu = new Menu();
        setupMenuOptions();
        menu.run();
    }

    /**
     * Sets up all available menu options for the game.
     * Configures actions for creating forests, managing items,
     * playing the game, and saving/loading game state.
     */
    public static void setupMenuOptions() {
        menu.addOption("1", "Create an empty forest (Or play again)", App::createEmptyForest);
        menu.addOption("2", "Print the forest", App::printForest);
        menu.addOption("3", "Add items (tree, rock) to the forest", App::addItems);
        menu.addOption("4", "List all items in the forest", App::listItems);
        menu.addOption("5", "Add 5 trees and 5 stones to the forest", App::add5TreesAnd5Rocks);
        menu.addOption("6", "Add player, hunter and the home", App::addPlayerHunterAndHome);
        menu.addOption("7", "Play game", App::playGame);
        menu.addOption("8", "Save game to file", App::saveToFile);
        menu.addOption("9", "Load game from file", App::loadFromFile);
        menu.addOption("p", "Print as JSON", App::printAsJson);
        menu.addOption("s", "Save as JSON", App::saveAsJson);
    }

    /**
     * Creates a new empty forest and initializes it.
     */
    public static void createEmptyForest() {
        forest = new Forest();
        forest.init();
        System.out.println("Created empty forest");
    }

    /**
     * Displays the current state of the forest.
     */
    public static void printForest() {
        System.out.println(forest.getGamePlan());
    }

    /**
     * Handles adding individual items (trees or rocks) to the forest.
     * Prompts user for item type and coordinates.
     */
    public static void addItems() {
        String input = menu.prompt("Add FirTree 🌲 (1) or Rock 🪨  (2): Enter your choice: ");
        
        if (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid input");
            return;
        }

        String[] coordinates = menu.prompt("Enter coordinates (x, y): ").split("\\s+");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        Position position = new Position(x, y);

        Item item = input.equals("1") ? new FirTree() : new Rock();
        forest.addItem(item, position);

        System.out.println("Added item to the forest: " + item.toString() + " " + position.toString());
    }

    /**
     * Displays a list of all items currently in the forest.
     */
    public static void listItems() {
        System.out.println(forest.listItems());
    }

    /**
     * Adds 5 trees and 5 rocks to random positions in the forest.
     */
    public static void add5TreesAnd5Rocks() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            AbstractItem firTree = new FirTree();
            Position treePos = new Position(random.nextInt(10), random.nextInt(10));
            forest.tryAddItem(firTree, treePos);
            
            AbstractItem rock = new Rock();
            Position rockPos = new Position(random.nextInt(10), random.nextInt(10));
            forest.tryAddItem(rock, rockPos);
        }
        System.out.println("Added 5 trees and 5 rocks to the forest");
    }

    /**
     * Adds the player (robot), hunter (wolf), and home (castle) to random empty positions in the forest.
     */
    public static void addPlayerHunterAndHome() {
        Random random = new Random();
        Position playerPos, hunterPos, homePos;
        
        // Find empty position for player
        do {
            playerPos = new Position(random.nextInt(Forest.WIDTH-1) + 1, 
                                   random.nextInt(Forest.HEIGHT-1) + 1);
        } while (!forest.isEmptyPosition(playerPos));
        
        // Find empty position for hunter
        do {
            hunterPos = new Position(random.nextInt(Forest.WIDTH-1) + 1,
                                   random.nextInt(Forest.HEIGHT-1) + 1);
        } while (!forest.isEmptyPosition(hunterPos));
        
        // Find empty position for home
        do {
            homePos = new Position(random.nextInt(Forest.WIDTH-1) + 1,
                                 random.nextInt(Forest.HEIGHT-1) + 1);
        } while (!forest.isEmptyPosition(homePos));

        AbstractMoveableItem player = new Robot(playerPos);
        AbstractHunter hunter = new Wolf(hunterPos); 
        AbstractMoveableItem home = new Castle(homePos);

        forest.addPlayerItem(player);
        forest.addHunterItem(hunter);
        forest.addHomeItem(home);

        System.out.println("Added player, hunter and home to the forest");
    }

    /**
     * Starts the main game loop where the player can move through the forest.
     * The game continues until the player reaches home, is caught by the hunter, or quits.
     */
    public static void playGame() {
        System.out.println("\nWelcome to the game!\n\nThe rules are simple:\nMove the player to the home and avoid the hunter.\nBut be careful, the hunter is fast and can jump in end to catch you!!!");
        String choice;
        do {
            System.out.println(forest.getGamePlan());
            choice = menu.prompt("Move player left=a, right=d, up=w, down=s, quit=q.");
            switch (choice) {
                case "a" -> forest.movePlayer(new Position(-1, 0));
                case "d" -> forest.movePlayer(new Position(1, 0));
                case "w" -> forest.movePlayer(new Position(0, -1));
                case "s" -> forest.movePlayer(new Position(0, 1));
                case "q" -> System.out.println("Bye bye!");
            }
            if (forest.isGameOver()) {
                System.out.println(forest.getStatus());
                break;
            }
        } while (!choice.equals("q"));
    }

    /**
     * Saves the current forest state to a file.
     * Prompts user for filename and handles potential IO errors.
     */
    public static void saveToFile() {
        String filename = menu.prompt("Enter filename to save to: ");
        try {
            ForestToFile.save(forest, filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving forest to file '" + filename + "'");
        }
    }

    /**
     * Loads a forest state from a file.
     * Prompts user for filename and handles potential IO and class not found errors.
     */
    public static void loadFromFile() {
        String filename = menu.prompt("Enter filename to load from: ");
        try {
            Forest forest = ForestToFile.load(filename);
            if (forest != null) {
                App.forest = forest;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading forest from file '" + filename + "'");
        }
    }

    /**
     * Prints the current forest state as JSON to the console.
     */
    public static void printAsJson() {
        System.out.println(ForestToJson.toJson(forest));
    }

    /**
     * Saves the current forest state to a file in JSON format.
     * Prompts user for filename.
     */
    public static void saveAsJson() {
        String filename = menu.prompt("Enter filename to save to: ");
        ForestToJson.saveAsJson(forest, filename);
    }
}
