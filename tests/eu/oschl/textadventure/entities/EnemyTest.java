package eu.oschl.textadventure.entities;

import eu.oschl.textadventure.BaseGameTest;
import eu.oschl.textadventure.objects.Weapon;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Enemy class.
 *
 * @author Ondřej Schlaichert
 */
class EnemyTest extends BaseGameTest {
    @Test
    void testConstructorInitializesFields() {
        Enemy enemy = new Enemy("Goblin", "A mean goblin", "You have slain the goblin!", 5, true);

        assertEquals("Goblin", enemy.getName());
        assertEquals("A mean goblin", enemy.getDescription());
        assertEquals(Optional.of("You have slain the goblin!"), enemy.getKillText());
        assertEquals(5, enemy.getStrength());
        assertTrue(enemy.isFinalBoss());
        assertTrue(enemy.isAlive());
    }

    @Test
    void testKillWithoutWeaponAndStrengthZeroSucceeds() {
        Enemy enemy = new Enemy("Wimp", "A weakling", 0);
        enemy.setGame(game);

        boolean result = enemy.kill();

        assertTrue(result);
        assertFalse(enemy.isAlive());
    }

    @Test
    void testKillWithoutWeaponAndStrengthPositiveFails() {
        Enemy enemy = new Enemy("Troll", "Big and scary", 10);
        enemy.setGame(game);

        boolean result = enemy.kill();

        assertFalse(result);
        assertTrue(enemy.isAlive());
    }

    @Test
    void testKillWithWeakWeaponFails() {
        Weapon knife = new Weapon("Knife", "A small knife", "You attacked with the knife", 1);
        game.getInventory().setWeapon(knife);

        Enemy enemy = new Enemy("Orc", "Strong", 5);
        enemy.setGame(game);

        boolean result = enemy.kill();

        assertFalse(result);
        assertTrue(enemy.isAlive());
    }

    @Test
    void testKillWithStrongWeaponSucceeds() {
        Weapon sword = new Weapon("Sword", "Sharp and deadly", "You attacked with the sword", 10);
        game.getInventory().setWeapon(sword);

        Enemy enemy = new Enemy("Bandit", "Sneaky thief", 5);
        enemy.setGame(game);

        boolean result = enemy.kill();

        assertTrue(result);
        assertFalse(enemy.isAlive());
    }

    @Test
    void testKillFinalBossWithSufficientWeaponFinishesGame() {
        Weapon godSword = new Weapon("Excalibur", "Crazy!!!", "You attacked with the Excalibur", 100);
        game.getInventory().setWeapon(godSword);

        Enemy finalBoss = new Enemy("Dragon", "The final challenge", "You have vanquished the dragon!", 50, true);
        finalBoss.setGame(game);

        boolean result = finalBoss.kill();

        assertTrue(result);
        assertFalse(finalBoss.isAlive());
        assertFalse(game.isRunning());
    }
}