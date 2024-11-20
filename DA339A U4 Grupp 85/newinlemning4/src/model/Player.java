package model;

/**
 * Represents a player in the game with attributes such as name, score, and their board.
 * @author Johan Ayaz
 */
public class Player {

    private String name;
    private Board board;
    private int score;
    private int shotsTaken = 0;

    /**
     * Constructs a new player with the given name, initializing a default board and score.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.score = 0;
    }

    /**
     * Retrieves the player's name.
     * @return The name of the player.
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

    /**
     * Sets the board for the player.
     * @param board The board to be set for the player.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Allows the player to take a shot on the board at the specified coordinates.
     * @param x The x-coordinate to shoot at.
     * @param y The y-coordinate to shoot at.
     * @return The result of the shot.
     * U4FG16
     */
    public ShotResult takeShot(int x, int y) {
        shotsTaken++;
        ShotResult result = board.shoot(x, y);

        // Uppdatera spelarens poäng baserat på skottresultatet
        switch (result) {
            case HIT -> score += 1;  // Lägg till poäng när spelaren träffar ett skepp

            case SUNK -> score += 1;  // Lägg till mer poäng när spelaren sänker ett skepp
            case MISS -> score += 1;
            default -> {
            }

        }
        System.out.println(result);
        return result;

    }
}
