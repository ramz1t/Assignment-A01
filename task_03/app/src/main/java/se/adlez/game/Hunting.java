package se.adlez.game;

/**
 * Interface defining hunting behavior in the game.
 * Provides functionality for one entity to hunt another within the forest environment.
 */
public interface Hunting {
    /**
     * Performs a hunting action on the specified prey within the given forest.
     *
     * @param pray The movable item that is being hunted
     * @param forest The forest environment where the hunting takes place
     */
    void hunt(AbstractMoveableItem pray, Forest forest);
}
