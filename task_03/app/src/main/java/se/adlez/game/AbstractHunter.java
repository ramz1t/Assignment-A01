package se.adlez.game;

/**
 * Abstract base class for hunter entities in the game.
 * Extends AbstractMovableItem and implements the Hunting interface to provide hunting behavior.
 */
public class AbstractHunter extends AbstractMovableItem implements Hunting {
    
    /**
     * Constructs a new AbstractHunter with the specified description, graphic and position.
     *
     * @param description The text description of the hunter
     * @param graphic The visual representation of the hunter
     * @param position The initial position of the hunter in the forest
     */
    public AbstractHunter(String description, String graphic, Position position) {
        super(description, graphic, position);
    }

    /**
     * Implements hunting behavior to move the hunter towards its prey.
     * The hunter will attempt to move one square closer to the prey each turn,
     * prioritizing the axis with the greater distance. If the primary movement
     * direction is blocked, it will try the alternative axis.
     * 
     * When the prey is diagonally adjacent, the hunter will move diagonally to catch it.
     *
     * @param prey The AbstractMovableItem that this hunter is pursuing
     * @param forest The Forest instance containing the game state
     */
    @Override
    public void hunt(AbstractMovableItem prey, Forest forest) {
        Position preyPos = prey.getPosition();
        Position hunterPos = this.getPosition();
        
        int dx = preyPos.getX() - hunterPos.getX();
        int dy = preyPos.getY() - hunterPos.getY();
        
        // Determine movement direction
        int moveX = 0;
        int moveY = 0;
        
        // Check if prey is one square diagonally away
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            // Move diagonally to catch prey
            moveX = dx;
            moveY = dy;
        } else {
            // Otherwise use normal movement logic
            if (Math.abs(dx) >= Math.abs(dy)) {
                // Try horizontal movement first
                if (dx != 0) {
                    moveX = Integer.compare(dx, 0);
                    Position newPos = new Position(hunterPos);
                    newPos.move(new Position(moveX, 0));
                    
                    // If horizontal move is blocked, try vertical
                    if (!forest.isValidPosition(newPos) || !forest.isEmptyPosition(newPos)) {
                        moveX = 0;
                        if (dy != 0) {
                            moveY = Integer.compare(dy, 0);
                        }
                    }
                }
            } else {
                // Try vertical movement first
                if (dy != 0) {
                    moveY = Integer.compare(dy, 0);
                    Position newPos = new Position(hunterPos);
                    newPos.move(new Position(0, moveY));
                    
                    // If vertical move is blocked, try horizontal
                    if (!forest.isValidPosition(newPos) || !forest.isEmptyPosition(newPos)) {
                        moveY = 0;
                        if (dx != 0) {
                            moveX = Integer.compare(dx, 0);
                        }
                    }
                }
            }
        }
        
        Position finalPos = new Position(hunterPos);
        finalPos.move(new Position(moveX, moveY));
        
        if (forest.isValidPosition(finalPos) && forest.isEmptyPosition(finalPos)) {
            forest.updateHunterPosition(hunterPos, finalPos);
            getPosition().move(new Position(moveX, moveY));
        }
    }
}
