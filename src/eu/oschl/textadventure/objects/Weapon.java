package eu.oschl.textadventure.objects;

public class Weapon extends GameObject {
    public Weapon(String name, String description) {
        super(name, description, unblocks);
        this.mustBeUsedIn = mustBeUsedIn;
    }

    public void pickUp() {
        game.getInventory().addItem(this);
        game.getCurrentRoom().removeObject(this);
    }

    @Override
    public boolean use() {
        return false;
    }
}
