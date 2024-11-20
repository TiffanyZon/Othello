package model;

public class Cruiser extends Ship {

    public Cruiser() {
        super(4);  // Assuming a submarine has length 3
    }

    @Override
    public String getDisplayName() {
        return "kryssare";
    }
}