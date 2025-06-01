package eu.oschl.textadventure.entities;

import java.util.Optional;

/**
 * Represents an enemy in the game.
 *
 * @author Ondřej Schlaichert
 */
public class Enemy extends Entity {
    public final String killText;

    public final int strength;
    public final boolean finalBoss;
    public boolean alive;

    public Enemy(String name, String description, String killText, int strength) {
        this(name, description, killText, strength, false);
        alive = true;
    }

    public Enemy(String name, String description, String killText, int strength, boolean finalBoss) {
        super(name, description);
        this.killText = killText;
        this.strength = strength;
        this.finalBoss = finalBoss;
    }

    public Enemy(String name, String description, int strength) {
        this(name, description, null, strength, false);
        alive = true;
    }

    public Enemy(String name, String description, int strength, boolean finalBoss) {
        this(name, description, null, strength, finalBoss);
    }

    public Optional<String> getKillText() {
        return Optional.ofNullable(killText);
    }

    public int getStrength() {
        return strength;
    }

    public boolean isFinalBoss() {
        return finalBoss;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * Kills the enemy if the player has enough strength to do so.
     *
     * @return true if the enemy was killed, false otherwise
     */
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
}
