package model;

public class Submarine extends Ship {

    public Submarine() {
        super(1);  // Assuming a submarine has length 3
    }

    @Override
    public String getDisplayName() {
        return "Submarine";
    }
}