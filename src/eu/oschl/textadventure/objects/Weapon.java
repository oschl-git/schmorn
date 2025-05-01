package eu.oschl.textadventure.objects;

public class Weapon extends GameObject {
    public final int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
