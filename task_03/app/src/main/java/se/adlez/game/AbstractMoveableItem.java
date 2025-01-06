package se.adlez.game;

/**
 * Abstract base class for game items that can move around the game world.
 * Extends AbstractItem and implements the Moveable interface to provide position tracking.
 */
public class AbstractMoveableItem extends AbstractItem implements Moveable {
    /** The current position of this item in the game world */
    final protected Position position;

    /**
     * Constructs a new AbstractMoveableItem with the specified description, graphic and position.
     *
     * @param description The text description of the item
     * @param graphic The visual representation of the item
     * @param position The initial position of the item in the game world
     */
    public AbstractMoveableItem(String description, String graphic, Position position) {
        super(description, graphic);
        this.position = position;
    }

    /**
     * Gets the current position of this item.
     *
     * @return The item's position in the game world
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Returns a string representation of this item,
     * combining its description, graphic and position.
     *
     * @return A string in the format "description graphic position"
     */
    @Override
    public String toString() {
        return super.toString() + " " + position.toString();
    }
}
