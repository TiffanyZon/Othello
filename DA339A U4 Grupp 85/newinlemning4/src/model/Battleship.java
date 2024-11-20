package model;

public class Battleship extends Ship {

    public Battleship() {
        super(3);  // Assuming a submarine has length 3
    }

    @Override
    public String getDisplayName() {
        return "Slagskepp";
    }
}