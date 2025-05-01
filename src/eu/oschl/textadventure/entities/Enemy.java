package eu.oschl.textadventure.entities;

import eu.oschl.textadventure.exceptions.InvalidGameState;

public class Enemy extends Entity {
    public final int strength;
    public final boolean finalBoss;

    public Enemy(String name, String description, int strength) {
        super(name, description);
        this.strength = strength;
        this.finalBoss = false;
    }

    public Enemy(String name, String description, int strength, boolean finalBoss) {
        super(name, description);
        this.strength = strength;
        this.finalBoss = finalBoss;
    }

    public boolean kill() {
        var weapon = game.getInventory().getWeapon();
        if (strength != 0 || weapon.isEmpty() || weapon.get().getDamage() <= strength) {
            return false;
        }

        try {
            game.getCurrentRoom().setEnemy(null);
        } catch (Exception exception) {
            throw new InvalidGameState("Attempted to kill enemy that is not in current room", exception);
        }

        if (finalBoss) {
            game.finish();
        }

        return true;
    }
}
