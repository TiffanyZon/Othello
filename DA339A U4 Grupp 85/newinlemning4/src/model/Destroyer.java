package model;

public class Destroyer extends Ship {

    public Destroyer() {
        super(3);  // Assuming a submarine has length 3
    }

    @Override
    public String getDisplayName() {
        return "Jagare";
    }
}