package eu.oschl.textadventure;

import java.util.ArrayList;

public class Room {
    private Game game;

    private String name;
    private String description;

    private ArrayList<Passage> passages;
    private Blockage blockage;
    private ArrayList<Object> objects;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.passages = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public void setGame(Game game) {
        this.game = game;

        for (Passage passage : passages) {
            passage.setGame(game);
        }
    }

    public void addPassage(Passage passage) {
        passages.add(passage);
    }

    public void setBlockage(Blockage blockage) {
        this.blockage = blockage;
    }

    public void addObject(Object object) {
        objects.add(object);
    }
}