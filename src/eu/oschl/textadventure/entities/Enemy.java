package eu.oschl.textadventure.entities;

public class Enemy extends Entity {
    public final int strength;
    public final boolean finalBoss;
    public boolean alive;

    public Enemy(String name, String description, int strength) {
        super(name, description);
        this.strength = strength;
        this.finalBoss = false;
        alive = true;
    }

    public Enemy(String name, String description, int strength, boolean finalBoss) {
        super(name, description);
        this.strength = strength;
        this.finalBoss = finalBoss;
    }

    public boolean kill() {
        var weapon = game.getInventory().getWeapon();

        if (weapon.isPresent()) {
            if (strength > weapon.get().getDamage()) {
                return false;
            }
        } else {
            if (strength > 0) {
                return false;
            }
        }

        alive = false;

        if (finalBoss) {
            game.finish();
        }

        return true;
    }

    public boolean isAlive() {
        return alive;
    }
}
