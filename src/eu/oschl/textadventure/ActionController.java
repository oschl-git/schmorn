package eu.oschl.textadventure;

import eu.oschl.textadventure.map.Passage;

public class ActionController {
    private Game game;

    public ActionController(Game game) {
        this.game = game;
    }

    public boolean walkThroughPassage(Passage passage) {
        var passages = this.game.getCurrentRoom().getPassages();

        if (!passages.contains(passage)) {
            return false;
        }

        return passage.passThrough();
    }
}
