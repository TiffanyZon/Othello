// Johan Ayaz
package model;

/**
 * Representing a 2D point or position in a grid or on a plane.
 * Useful for various applications, including board games,
 * graphical representations, and more.
 *
 * @author Johan Ayaz
 */
public class Position {

    /** The x-coordinate of the position */
    private int x;

    /** The y-coordinate of the position */
    private int y;

    /**
     * Constructs a new position with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the position.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the position.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
}
