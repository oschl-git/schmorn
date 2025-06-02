package eu.oschl.textadventure;

import eu.oschl.textadventure.exceptions.InvalidGameState;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;
import eu.oschl.textadventure.objects.Button;
import eu.oschl.textadventure.objects.Weapon;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest extends BaseGameTest {
    @Test
    void testGameFieldsAreInitializedCorrectly() {
        assertEquals("This is a prologue.", game.getPrologue());
        assertEquals("This is an epilogue.", game.getEpilogue());
        assertArrayEquals(
                new String[]{"This is a message informing the player that the command is unknown."},
                game.getUnknownCommandMessages()
        );
        assertNotNull(game.getInventory());
        assertEquals(2, game.getRooms().size());
        assertTrue(game.isRunning());
    }

    @Test
    void testSetCurrentRoomChangesRoomAndPreservesReference() {
        Room room2 = game.getRooms().get(1);
        game.setCurrentRoom(room2);

        assertEquals(room2, game.getCurrentRoom());
    }

    @Test
    void testAddPreviousPassageAndGetLastPassage() {
        Passage passage = game.getRooms().getFirst().getPassages().iterator().next();
        game.addPreviousPassage(passage);

        Optional<Passage> result = game.getLastPassage();
        assertTrue(result.isPresent());
        assertEquals(passage, result.get());
    }

    @Test
    void testGetLastPassageWhenEmptyReturnsEmpty() {
        assertTrue(game.getPreviousPassages().isEmpty());
        assertTrue(game.getLastPassage().isEmpty());
    }

    @Test
    void testRemoveLastPassageSucceedsWhenNotEmpty() {
        Passage passage = game.getRooms().get(0).getPassages().iterator().next();
        game.addPreviousPassage(passage);
        assertDoesNotThrow(() -> game.removeLastPassage());
        assertTrue(game.getPreviousPassages().isEmpty());
    }

    @Test
    void testRemoveLastPassageThrowsWhenEmpty() {
        assertThrows(InvalidGameState.class, () -> game.removeLastPassage());
    }

    @Test
    void testFinishStopsGame() {
        game.finish();
        assertFalse(game.isRunning());
    }

    @Test
    void testGameTraversal() {
        assertTrue(game.isRunning());

        var room1 = game.getCurrentRoom();
        var passage = room1.getPassages().iterator().next();
        assertEquals(1, room1.getPassages().size());

        var room2 = passage.getOtherRoom(room1);

        assertFalse(passage.passThrough(false));
        assertEquals(room1, game.getCurrentRoom());

        Weapon weapon = null;
        Button button = null;
        for (var object : room1.getObjects()) {
            if (object instanceof Weapon w) {
                weapon = w;
            }

            if (object instanceof Button b) {
                button = b;
            }
        }

        assertNotNull(weapon);
        weapon.pickUp();

        assertNotNull(button);
        button.press();

        assertTrue(passage.passThrough(false));
        assertEquals(room2, game.getCurrentRoom());

        assertTrue(game.getCurrentRoom().getEnemy().isPresent());
        assertTrue(game.getCurrentRoom().getEnemy().get().isAlive());
        assertTrue(game.getCurrentRoom().isBlockedByEnemy());

        assertTrue(game.getCurrentRoom().getEnemy().get().kill());

        assertFalse(game.getCurrentRoom().isBlockedByEnemy());
        assertFalse(game.getCurrentRoom().getEnemy().get().isAlive());
        assertFalse(game.isRunning());
    }
}