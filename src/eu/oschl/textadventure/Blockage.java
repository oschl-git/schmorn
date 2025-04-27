package eu.oschl.textadventure;

public class Blockage {
    private Game game;

    private String name;
    private String description;
    private String neededGameState;

    public Blockage(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean canPass() {
        return game.getGameState().contains(neededGameState);
    }
}
