package eu.oschl.textadventure;

import java.util.ArrayList;

public class Room {
    private Game game;

    private String name;
    private String description;

    private ArrayList<Passage> passages;
    private Blockage blockage;
    private ArrayList<Object> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.passages = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addPassage(Passage passage) {
        passages.add(passage);
    }

    public ArrayList<Passage> getPassages() {
        return passages;
    }

    public void setBlockage(Blockage blockage) {
        this.blockage = blockage;
    }
}
