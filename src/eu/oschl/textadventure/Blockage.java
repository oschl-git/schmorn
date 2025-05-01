package eu.oschl.textadventure;

public class Blockage {
    private Game game;

    private final String name;
    private final String description;
    private int requiredInteractionsToPass;

    public Blockage(String name, String description, int requiredInteractionsToPass) {
        this.name = name;
        this.description = description;
        this.requiredInteractionsToPass = requiredInteractionsToPass;
    }

    public Blockage(String name, int requiredInteractionsToPass) {
        this(name, null, requiredInteractionsToPass);
    }

    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void interact() {
        this.requiredInteractionsToPass--;
    }

    public boolean canPass() {
        return requiredInteractionsToPass <= 0;
    }
}
