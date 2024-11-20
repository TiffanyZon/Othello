// Johan Ayaz
package model;

/**
 * Enumerates the potential outcomes of a shot in the game.
 *
 * @author Johan Ayaz
 */
public enum ShotResult {

    /** Indicates the shot hit a ship. */
    HIT,

    /** Indicates the shot missed any ship. */
    MISS,

    /** Indicates the shot hit and sunk a ship. */
    SUNK
}

