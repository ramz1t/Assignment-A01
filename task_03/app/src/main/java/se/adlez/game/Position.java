package se.adlez.game;

import java.io.Serializable;

/**
 * Represents a 2D position with x and y coordinates.
 * This class is immutable and provides methods for position manipulation.
 * Implements Serializable to allow instances to be serialized.
 */
public class Position implements Serializable {
    private int x;
    private int y;

    /**
     * Constructs a new {@code Position} with the specified coordinates.
     *
     * @param x the x-coordinate of the position.
     * @param y the y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new {@code Position} by copying the coordinates of another position.
     *
     * @param position the position to copy.
     */
    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    /**
     * Retrieves the x-coordinate of the position.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the position.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x the new x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y the new y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks whether this position is equal to another object.
     * Two positions are equal if their x and y coordinates are the same.
     *
     * @param o the object to compare to.
     * @return {@code true} if the positions are equal, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position pos = (Position) o;
        return x == pos.x && y == pos.y;
    }

    /**
     * Computes the hash code for this position.
     *
     * @return the hash code, calculated as {@code 31 * x + y}.
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * Returns a string representation of the position.
     * The format is "(x, y)".
     *
     * @return the string representation of the position.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Moves the position by the specified relative coordinates.
     *
     * @param relative the position containing relative x and y offsets.
     */
    public void move(Position relative) {
        this.x += relative.x;
        this.y += relative.y;
    }
}
