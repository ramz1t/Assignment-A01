package se.adlez.game;

/**
 * Interface representing an item in the game.
 * Provides methods to get the item's description and visual representation.
 */
public interface Item {
    /**
     * Gets the text description of this item.
     *
     * @return The item's description
     */
    public String getDescription();

    /**
     * Gets the visual representation of this item.
     *
     * @return The item's graphic symbol
     */
    public String getGraphic();
}
