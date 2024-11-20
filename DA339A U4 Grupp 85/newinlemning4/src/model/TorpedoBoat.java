package model;

public class TorpedoBoat extends Ship {

    public TorpedoBoat() {
        super(2);
    }

    @Override
    public String getDisplayName() {
        return "Torpedbåt";
    }
}