package eu.oschl.textadventure.objects;

public class Weapon extends PickableObject {
    public final int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    @Override
    public boolean pickUp() {
        if (game.getInventory().getWeapon().isPresent() && game.getInventory().getWeapon().get().getDamage() > damage) {
            return false;
        }

        game.getCurrentRoom().removeObject(this);
        game.getInventory().setWeapon(this);

        return true;
    }

    public int getDamage() {
        return damage;
    }
}
