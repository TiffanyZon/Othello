package model;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Johan Ayaz
 * Represents a high score leaderboard, storing top players and their scores.
 */
public class HighScore {

    /** The list of high score entries. */
    private final List<HighScoreEntry> entries;

    /**
     * Constructs a new HighScore object.
     */
    public HighScore() {
        this.entries = new ArrayList<>();
    }

    /**
     * Adds a new high score entry to the leaderboard.
     * @param entry The entry to add.
     * U4FG15
     */
    public void addEntry(HighScoreEntry entry) {
        entries.add(entry);
        Collections.sort(entries);
        if (entries.size() > 10) {
            entries.remove(10);
        }
    }

    public void loadFromFile(String filename) throws IOException {
        FileOperations.loadHighScoresFromFile(this, filename);
    }

    public void saveToFile(String filename) throws IOException {
        FileOperations.saveHighScoresToFile(getTop10Entries(), filename);
    }


    /**
     * Retrieves the top 10 high score entries.
     * @return A list of the top 10 high score entries.
     */
    public List<HighScoreEntry> getTop10Entries() {
        return Collections.unmodifiableList(entries);
    }
}
