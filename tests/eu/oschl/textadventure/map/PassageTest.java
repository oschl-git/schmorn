package eu.oschl.textadventure.map;

import eu.oschl.textadventure.BaseGameTest;
import eu.oschl.textadventure.exceptions.InvalidGameState;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Passage class.
 *
 * @author Ondřej Schlaichert
 */
class PassageTest extends BaseGameTest {
    @Test
    void testConstructorSetsFieldsCorrectly() {
        Passage passage = new Passage("Tunnel", "A dark tunnel", true);

        assertEquals("Tunnel", passage.getName());
        assertEquals(Optional.of("A dark tunnel"), passage.getDescription());
        assertTrue(passage.isSeeThrough());
        assertNotNull(passage.getRooms());
        assertEquals(2, passage.getRooms().length);
    }

    @Test
    void testSetGameAlsoSetsGameOnBlockage() {
        Passage passage = new Passage("Hidden Path", true);
        Blockage blockage = new Blockage("Stone wall", 2);
        passage.setBlockage(blockage);

        passage.setGame(game);
    }

    @Test
    void testAddRoomAssociatesRoomsCorrectly() {
        Passage passage = new Passage("Bridge", true);
        passage.setGame(game);

        Room room1 = game.getRooms().get(0);
        Room room2 = game.getRooms().get(1);

        passage.addRoom(room1);
        passage.addRoom(room2);

        Room[] connected = passage.getRooms();
        assertTrue(connected[0] == room1 || connected[1] == room1);
        assertTrue(connected[0] == room2 || connected[1] == room2);
    }

    @Test
    void testAddThirdRoomThrows() {
        Passage passage = new Passage("Impossible Triangle", true);
        passage.setGame(game);

        Room extra = new Room("Extra", "Extra room");
        passage.addRoom(game.getRooms().get(0));
        passage.addRoom(game.getRooms().get(1));

        assertThrows(InvalidGameState.class, () -> passage.addRoom(extra));
    }

    @Test
    void testCanPassWithUninteractedBlockageFails() {
        Passage passage = game.getRooms().getFirst().getPassages().iterator().next();
        game.setCurrentRoom(game.getRooms().getFirst());

        assertFalse(passage.canPass());
    }

    @Test
    void testCanPassWithClearedBlockageSucceeds() {
        Passage passage = game.getRooms().get(0).getPassages().iterator().next();
        Blockage blockage = passage.getBlockage().orElseThrow();

        blockage.interact();
        assertTrue(blockage.canPass());

        assertTrue(passage.canPass());
    }

    @Test
    void testCanPassFailsIfNotConnectedToCurrentRoom() {
        Passage passage = new Passage("Disconnected", true);
        passage.setGame(game);

        Room outsider = new Room("Outsider", "Nowhere");
        passage.addRoom(outsider);
        passage.addRoom(game.getRooms().get(1));

        game.setCurrentRoom(game.getRooms().get(0));

        assertFalse(passage.canPass());
    }

    @Test
    void testCanPassFailsWhenEnemyIsPresent() {
        game.setCurrentRoom(game.getRooms().get(1));
        Passage passage = game.getRooms().get(1).getPassages().iterator().next();

        assertFalse(passage.canPass());
    }

    @Test
    void testCanPassWhenGoingBackFromEnemyRoom() {
        Passage passage = game.getRooms().get(0).getPassages().iterator().next();
        Blockage blockage = passage.getBlockage().orElseThrow();
        blockage.interact();

        game.addPreviousPassage(passage);
        game.setCurrentRoom(game.getRooms().get(1));

        assertTrue(passage.canPass());
    }

    @Test
    void testPassThroughSuccessMovesPlayer() {
        Passage passage = game.getRooms().getFirst().getPassages().iterator().next();
        Blockage blockage = passage.getBlockage().orElseThrow();
        blockage.interact();

        Room to = game.getRooms().get(1);

        boolean result = passage.passThrough(false);

        assertTrue(result);
        assertEquals(to, game.getCurrentRoom());
    }

    @Test
    void testPassThroughFailsIfBlocked() {
        Passage passage = game.getRooms().getFirst().getPassages().iterator().next();
        passage.getBlockage().orElseThrow();

        boolean result = passage.passThrough(false);

        assertFalse(result);
        assertEquals(game.getRooms().getFirst(), game.getCurrentRoom());
    }

    @Test
    void testGetOtherRoomReturnsCorrectRoom() {
        Passage passage = game.getRooms().get(0).getPassages().iterator().next();
        Room room1 = game.getRooms().get(0);
        Room room2 = game.getRooms().get(1);

        assertEquals(room2, passage.getOtherRoom(room1));
        assertEquals(room1, passage.getOtherRoom(room2));
    }

    @Test
    void testGetOtherRoomThrowsIfRoomNotInPassage() {
        Passage passage = new Passage("Secret Path", true);
        Room roomA = new Room("A", "A");
        Room roomB = new Room("B", "B");

        passage.addRoom(roomA);
        passage.addRoom(roomB);

        Room outsider = new Room("Outsider", "Nope");

        assertThrows(InvalidGameState.class, () -> passage.getOtherRoom(outsider));
    }
}