package eu.oschl.textadventure;

import eu.oschl.textadventure.objects.GameObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Room {
    private Game game;

    private final String name;
    private final String description;

    private final HashSet<Passage> passages;
    private Blockage blockage;
    private final ArrayList<GameObject> objects;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.passages = new HashSet<>();
        this.objects = new ArrayList<>();
    }

    public void setGame(Game game) {
        this.game = game;

        for (Passage passage : passages) {
            passage.setGame(game);
        }

        if (blockage != null) {
            blockage.setGame(game);
        }

        for (GameObject object : objects) {
            object.setGame(game);
        }
    }

    public void addPassage(Passage passage) {
        passage.setGame(game);
        passages.add(passage);
    }

    public void setBlockage(Blockage blockage) {
        blockage.setGame(game);
        this.blockage = blockage;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject object) {
        object.setGame(game);
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }
}