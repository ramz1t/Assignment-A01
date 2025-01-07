package se.adlez.game;

/**
 * Represents an entity that can move within the game.
 * Any class implementing this interface must provide a method to retrieve its current position.
 */
public interface Moveable {

    /**
     * Retrieves the current position of the moveable entity.
     * @return the Position representing the entity's current location.
     */
    Position getPosition();
}
