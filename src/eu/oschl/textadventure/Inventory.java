package eu.oschl.textadventure;

import eu.oschl.textadventure.objects.PickableObject;

import java.util.ArrayList;

public class Inventory {
    private final Game game;

    public final ArrayList<PickableObject> items;

    public Inventory(Game game) {
        this.game = game;
        items = new ArrayList<>();
    }

    public void addItem(PickableObject item) {
        items.add(item);
    }

    public void removeItem(PickableObject item) {
        items.remove(item);
    }

    public ArrayList<PickableObject> getItems() {
        return items;
    }
}
