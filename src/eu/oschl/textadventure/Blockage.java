package eu.oschl.textadventure;

public class Blockage {
    private Game game;

    private final String name;
    private final String description;
    private int requiredItemsToPass;

    public Blockage(Game game, String name, String description, int requiredItemsToPass) {
        this.game = game;
        this.name = name;
        this.description = description;
        this.requiredItemsToPass = requiredItemsToPass;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void unblock() {
        this.requiredItemsToPass--;
    }

    public boolean canPass() {
        return requiredItemsToPass <= 0;
    }
}
