package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.BaseGameTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Weapon class.
 *
 * @author Ondřej Schlaichert
 */
class WeaponTest extends BaseGameTest {
    @Test
    void testConstructorInitializesFields() {
        Weapon weapon = new Weapon(
                "Axe",
                "A brutal battle axe.",
                "You swing the axe with rage.",
                15
        );

        assertEquals("Axe", weapon.getName());
        assertEquals("A brutal battle axe.", weapon.getDescription());
        assertEquals("You swing the axe with rage.", weapon.getAttackText());
        assertEquals(15, weapon.getDamage());
    }

    @Test
    void testPickUpSucceedsWhenNoWeaponAndRoomUnblocked() {
        Weapon weapon = new Weapon(
                "Sword",
                "A sharp sword.",
                "You slash cleanly.",
                10
        );
        game.getCurrentRoom().addObject(weapon);
        weapon.setGame(game);

        boolean picked = weapon.pickUp();

        assertTrue(picked);
        assertEquals(weapon, game.getInventory().getWeapon().orElse(null));
        assertFalse(game.getCurrentRoom().getObjects().contains(weapon));
    }

    @Test
    void testPickUpFailsIfRoomBlockedByEnemy() {
        game.setCurrentRoom(game.getRooms().get(1));

        Weapon weapon = new Weapon(
                "Knife",
                "A tiny knife.",
                "You stab weakly.",
                2
        );
        game.getCurrentRoom().addObject(weapon);
        weapon.setGame(game);

        boolean picked = weapon.pickUp();

        assertFalse(picked);
        assertFalse(game.getInventory().getWeapon().isPresent());
    }

    @Test
    void testPickUpFailsIfWeakerThanCurrentWeapon() {
        Weapon strong = new Weapon("Claymore", "Two-handed blade", "You cleave mightily.", 20);
        Weapon weak = new Weapon("Dagger", "Rusty dagger", "You poke pathetically.", 5);

        strong.setGame(game);
        weak.setGame(game);

        game.getInventory().setWeapon(strong);
        game.getCurrentRoom().addObject(weak);

        boolean picked = weak.pickUp();

        assertFalse(picked);
        assertEquals(strong, game.getInventory().getWeapon().orElse(null));
    }

    @Test
    void testPickUpReplacesWeakerWeapon() {
        Weapon weak = new Weapon("Stick", "A simple stick", "You bop someone with it.", 2);
        Weapon stronger = new Weapon("Hammer", "Heavy and deadly", "You smash hard.", 15);

        weak.setGame(game);
        stronger.setGame(game);

        game.getInventory().setWeapon(weak);
        game.getCurrentRoom().addObject(stronger);

        boolean picked = stronger.pickUp();

        assertTrue(picked);
        assertEquals(stronger, game.getInventory().getWeapon().orElse(null));
        assertFalse(game.getCurrentRoom().getObjects().contains(stronger));
    }
}