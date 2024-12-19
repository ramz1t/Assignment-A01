package se.adlez.game;

import java.io.Serializable;

/**
 * Abstract base class for game items that have a description and graphic representation.
 * Implements the Item interface and Serializable to allow saving/loading of items.
 */
public abstract class AbstractItem implements Item, Serializable {
    /** The text description of the item */
    final private String description;
    
    /** The visual representation of the item */
    final private String graphic;

    /**
     * Constructs a new AbstractItem with the specified description and graphic.
     *
     * @param description The text description of the item
     * @param graphic The visual representation of the item
     */
    public AbstractItem(String description, String graphic) {
        this.description = description;
        this.graphic = graphic;
    }
    
    /**
     * Gets the text description of this item.
     *
     * @return The item's description
     */
    @Override
    public String getDescription() {    
        return description;
    }

    /**
     * Gets the visual representation of this item.
     *
     * @return The item's graphic
     */
    @Override
    public String getGraphic() {
        return graphic;
    }

    /**
     * Returns a string representation of this item,
     * combining its description and graphic.
     *
     * @return A string in the format "description graphic"
     */
    @Override
    public String toString() {
        return description + " " + graphic;
    }
}
