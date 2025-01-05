package se.adlez.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a forest game board where items, player, hunter and home can be placed and moved.
 * The forest is a grid of cells with a fixed width and height.
 */
public class Forest implements Serializable {
    final public static int WIDTH = 10;
    final public static int HEIGHT = 10;
    final private String EMPTY_CELL = "🟩";
    private Map<Position, Item> items;
    private AbstractMovableItem player;
    private AbstractHunter hunter;
    private AbstractMovableItem home;
    private boolean gameOver = false;
    final private StringBuilder status = new StringBuilder();

    /**
     * Initializes the forest by creating a new empty items map.
     */
    public void init() {
        items = new HashMap<>();
    }

    /**
     * Generates a string representation of the current game board.
     * @return A string showing the current state of the forest with all items.
     */
    public String getGamePlan() {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= HEIGHT; y++) {
            for (int x = 1; x <= WIDTH; x++) {
                Position position = new Position(x, y);
                if (items.containsKey(position)) {
                    sb.append(items.get(position).getGraphic());
                } else {
                    sb.append(EMPTY_CELL);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Adds an item to the forest at the specified position.
     * @param item The item to add
     * @param position The position where to add the item
     */
    public void addItem(Item item, Position position) {
        items.put(position, item);
    }

    /**
     * Lists all items currently in the forest with their positions.
     * @return A string containing all items and their positions
     */
    public String listItems() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Position, Item> entry : items.entrySet()) {
            sb.append(entry.getKey().toString()).append(" ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Attempts to add an item to the forest if the position is empty.
     * @param item The item to add
     * @param position The position where to add the item
     * @return true if the item was added successfully, false if position was occupied
     */
    public boolean tryAddItem(Item item, Position position) {
        if (items.containsKey(position)) {
            return false;
        }
        items.put(position, item);
        return true;
    }

    /**
     * Adds a player to the forest and stores it as the current player.
     * @param player The player item to add
     */
    public void addPlayerItem(AbstractMovableItem player) {
        items.put(player.getPosition(), player);
        this.player = player;
    }

    /**
     * Adds a hunter to the forest and stores it as the current hunter.
     * @param hunter The hunter item to add
     */
    public void addHunterItem(AbstractHunter hunter) {
        items.put(hunter.getPosition(), hunter);
        this.hunter = hunter;
    }

    /**
     * Adds a home to the forest and stores it as the current home.
     * @param home The home item to add
     */
    public void addHomeItem(AbstractMovableItem home) {
        items.put(home.getPosition(), home);
        this.home = home;
    }

    /**
     * Attempts to move the player in the specified direction and updates game state.
     * The hunter will attempt to catch the player after each successful move.
     * @param relative The relative movement vector
     */
    public void movePlayer(Position relative) {
        if (player == null) {
            status.append("Player not found!\n");
            return;
        }
        if (gameOver) {
            System.out.println("Game over!\n");
            return;
        }
        if (home == null) {
            System.out.println("Home not found!\n");
            return;
        }

        // Player's position after move
        Position playerPos = player.getPosition();
        Position newPos = new Position(playerPos);
        newPos.move(relative);

        if (!isValidPosition(newPos)) {
            status.append("Player could not move out of bounds!\n");
            return;
        }

        // Check if player reached home
        if (newPos.equals(home.getPosition())) {
            status.append("Player reached home!\n");
            status.append("Game over!\n");
            gameOver = true;
            return;
        }

        // Move player and update hunter position
        if (!items.containsKey(newPos)) {
            player.getPosition().move(relative);
            items.remove(playerPos);
            items.put(player.getPosition(), player);
            status.append("Player moved successfully!\n");
            
            hunter.hunt(player, this);
            
            // Check if hunter caught player after its move
            if (hunter.getPosition().equals(player.getPosition())) {
                status.append("Hunter caught the player!\n");
                status.append("Game over!\n");
                gameOver = true;
            } else {
                status.append("Hunter did not catch the player!\n");
            }
        } else {
            status.append("Player could not move!\n");
        } 
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Gets the current game status messages.
     * @return A string containing all status messages
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * Updates the hunter's position in the forest.
     * @param oldPos The hunter's previous position
     * @param newPos The hunter's new position
     */
    public void updateHunterPosition(Position oldPos, Position newPos) {
        items.remove(oldPos);
        items.put(newPos, hunter);
        status.append("Hunter moved!\n");
    }

    /**
     * Checks if a position is within the forest boundaries.
     * @param position The position to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= 1 && position.getX() <= WIDTH && position.getY() >= 1 && position.getY() <= HEIGHT;
    }

    /**
     * Checks if a position is empty or only contains the player.
     * @param position The position to check
     * @return true if the position is empty or only contains the player, false if it contains any other item
     */
    public boolean isEmptyPosition(Position position) {
        Item item = items.get(position);
        return !items.containsKey(position) || (item != null && item == player);
    }
}
