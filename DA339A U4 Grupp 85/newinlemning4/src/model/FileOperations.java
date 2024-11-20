package model;

import java.io.*;
import java.util.List;

/**
 * Provides utilities for reading from and writing to files.
 * @author Johan Ayaz
 */
public class FileOperations {

    /**
     * Saves high scores to a file.
     * @param entries A list of high score entries.
     * @param filename The name of the file to save to.
     * @throws IOException if there's an error during file operations.
     * U4FG18
     */
    public static void saveHighScoresToFile(List<HighScoreEntry> entries, String filename) throws IOException {
        System.out.println("Sparar till fil: " + filename + " med innehåll: " + entries);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (HighScoreEntry entry : entries) {
                writer.write(entry.getName() + "," + entry.getShotsTaken());
                writer.newLine();
            }
        }
    }

    /**
     * Loads high scores from a file and populates the HighScore object.
     * @param highScore The HighScore object to populate.
     * @param filename The name of the file to read from.
     * @throws IOException if there's an error during file operations.
     */
    public static void loadHighScoresFromFile(HighScore highScore, String filename) throws IOException {
        System.out.println("Laddar från fil: " + filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int shotsTaken = Integer.parseInt(parts[1]);
                highScore.addEntry(new HighScoreEntry(name, shotsTaken));
            }
        }
    }
}

