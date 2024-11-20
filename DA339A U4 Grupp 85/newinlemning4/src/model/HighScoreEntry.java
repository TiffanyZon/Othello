package model;

/**
 * @author Johan Ayaz
 * Represents an entry in the high score leaderboard.
 */
public class HighScoreEntry implements Comparable<HighScoreEntry> {

    /** The name of the player. */
    private String name;

    /** The number of shots taken by the player. */
    private int shotsTaken;

    /**
     * Constructs a new HighScoreEntry object.
     * @param name The name of the player.
     * @param shotsTaken The number of shots taken by the player.
     * U4FG17
     */
    public HighScoreEntry(String name, int shotsTaken) {
        this.name = name;
        this.shotsTaken = shotsTaken;
    }

    /**
     * Retrieves the name of the player.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number of shots taken by the player.
     * @return The number of shots taken.
     */
    public int getShotsTaken() {
        return shotsTaken;
    }

    @Override
    public int compareTo(HighScoreEntry other) {
        return Integer.compare(this.shotsTaken, other.shotsTaken);
    }
}
