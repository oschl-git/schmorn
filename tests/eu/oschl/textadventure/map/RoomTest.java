package eu.oschl.textadventure.map;

import eu.oschl.textadventure.BaseGameTest;
import eu.oschl.textadventure.entities.Enemy;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Room class.
 *
 * @author Ondřej Schlaichert
 */
class RoomTest extends BaseGameTest {
    @Test
    void testConstructorInitializesFieldsWithEnterText() {
        Room room = new Room("Library", "A dusty old room.", "You have entered the library.");
        assertEquals("Library", room.getName());
        assertEquals("A dusty old room.", room.getDescription());
        assertEquals(Optional.of("You have entered the library."), room.getEnterText());
        assertTrue(room.getPassages().isEmpty());
        assertTrue(room.getObjects().isEmpty());
        assertFalse(room.wasEntered());
    }

    @Test
    void testConstructorInitializesFieldsWithoutEnterText() {
        Room room = new Room("Hall", "A large echoing hall.");
        assertEquals("Hall", room.getName());
        assertEquals("A large echoing hall.", room.getDescription());
        assertEquals(Optional.empty(), room.getEnterText());
    }

    @Test
    void testEnterMarksRoomAsEnteredAndSetsCurrentRoom() {
        Room room = game.getRooms().get(1);
        assertNotEquals(room, game.getCurrentRoom());

        boolean result = room.enter();

        assertTrue(result);
        assertEquals(room, game.getCurrentRoom());
        assertTrue(room.wasEntered());
    }

    @Test
    void testAddPassageLinksPassageAndGame() {
        Room room = new Room("Secret", "Hidden behind the bookshelf");
        room.setGame(game);

        Passage passage = new Passage("Tunnel", true);
        room.addPassage(passage);

        assertTrue(room.getPassages().contains(passage));
    }

    @Test
    void testIsBlockedByEnemyReturnsCorrectly() {
        Room room = new Room("Arena", "Fight here");
        room.setGame(game);

        Enemy enemy = new Enemy("Orc", "Strong", 0);
        room.setEnemy(enemy);

        assertTrue(room.isBlockedByEnemy());

        enemy.kill();
        assertFalse(room.isBlockedByEnemy());
    }

    @Test
    void testSetEnemyNullRemovesEnemy() {
        Room room = new Room("Void", "Empty");
        room.setGame(game);

        room.setEnemy(new Enemy("Zombie", "Undead", 1));
        assertTrue(room.getEnemy().isPresent());

        room.setEnemy(null);
        assertTrue(room.getEnemy().isEmpty());
    }
}