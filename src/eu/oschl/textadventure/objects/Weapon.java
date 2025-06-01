package eu.oschl.textadventure.objects;

/**
 * Represents a weapon in the game that can be picked up and used to attack enemies.
 *
 * @author Ondřej Schlaichert
 */
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

    public int getDamage() {
        return damage;
    }

    /**
     * Attempts to pick up the weapon. If the current room is blocked by an enemy or if the player already has a more
     * powerful weapon, the weapon cannot be picked up.
     *
     * @return true if the weapon was successfully picked up, false otherwise
     */
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
}
