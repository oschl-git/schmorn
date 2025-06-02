package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.BaseGameTest;
import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the InventoryItem class.
 *
 * @author Ondřej Schlaichert
 */
class InventoryItemTest extends BaseGameTest {
    @Test
    void testConstructorInitializesFields() {
        Blockage blockage = new Blockage("Door", 1);
        Room[] usableRooms = { game.getCurrentRoom() };

        InventoryItem item = new InventoryItem(
                "Key",
                "A small bronze key.",
                "You insert the key into the lock.",
                usableRooms,
                blockage
        );

        assertEquals("Key", item.getName());
        assertEquals("A small bronze key.", item.getDescription());
        assertEquals("You insert the key into the lock.", item.getUseText());
        assertArrayEquals(usableRooms, item.getCanBeUsedIn());
        assertEquals(blockage, item.getInteractsWith());
    }

    @Test
    void testPickUpSucceedsWhenRoomUnblocked() {
        Room current = game.getCurrentRoom();

        InventoryItem item = new InventoryItem(
                "Gem",
                "A glowing gem.",
                "You press it into the socket.",
                new Room[]{ current },
                new Blockage("Gem Lock", 1)
        );

        current.addObject(item);
        item.setGame(game);

        boolean result = item.pickUp();

        assertTrue(result);
        assertTrue(game.getInventory().getItems().contains(item));
        assertFalse(current.getObjects().contains(item));
    }

    @Test
    void testPickUpFailsIfRoomBlockedByEnemy() {
        Room enemyRoom = game.getRooms().get(1);
        game.setCurrentRoom(enemyRoom);

        InventoryItem item = new InventoryItem(
                "Potion",
                "A health potion.",
                "You drink it.",
                new Room[]{ enemyRoom },
                new Blockage("Blocked", 1)
        );

        enemyRoom.addObject(item);
        item.setGame(game);

        boolean result = item.pickUp();

        assertFalse(result);
        assertFalse(game.getInventory().getItems().contains(item));
        assertTrue(enemyRoom.getObjects().contains(item));
    }

    @Test
    void testUseSucceedsInCorrectRoom() {
        Room allowed = game.getCurrentRoom();
        Blockage blockage = new Blockage("Vault", 1);

        InventoryItem item = new InventoryItem(
                "Code Disk",
                "A magnetic data disk.",
                "The vault unlocks.",
                new Room[]{ allowed },
                blockage
        );

        item.setGame(game);
        game.getInventory().addItem(item);

        boolean used = item.use();

        assertTrue(used);
        assertEquals(0, blockage.getRequiredInteractionsToPass());
        assertFalse(game.getInventory().getItems().contains(item));
    }

    @Test
    void testUseFailsInWrongRoom() {
        Room wrongRoom = game.getRooms().get(1);
        Blockage blockage = new Blockage("Panel", 1);

        InventoryItem item = new InventoryItem(
                "Battery",
                "A high-voltage battery.",
                "You insert the battery.",
                new Room[]{ wrongRoom },
                blockage
        );

        item.setGame(game);
        game.getInventory().addItem(item);

        boolean result = item.use();

        assertFalse(result);
        assertEquals(1, blockage.getRequiredInteractionsToPass());
        assertTrue(game.getInventory().getItems().contains(item));
    }
}