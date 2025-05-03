package eu.oschl.textadventure.objects;

public class Weapon extends PickableObject {
    public final String attackText;
    public final int damage;

    public Weapon(String name, String description, String attackText, int damage) {
        super(name, description);
        this.attackText = attackText;
        this.damage = damage;
    }

    public String getAttackText() {
        return attackText;
    }

    @Override
    public boolean pickUp() {
        if (game.getCurrentRoom().isBlockedByEnemy()) {
            return false;
        }

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
