package eu.oschl.textadventure;

import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Room;
import eu.oschl.textadventure.objects.InventoryItem;
import eu.oschl.textadventure.objects.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Inventory class.
 *
 * @author Ondřej Schlaichert
 */
class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testConstructorInitializesEmptyInventory() {
        assertTrue(inventory.getItems().isEmpty());
        assertTrue(inventory.getWeapon().isEmpty());
    }

    @Test
    void testAddItemSucceedsWhenUnderLimit() {
        for (int i = 0; i < 5; i++) {
            InventoryItem item = makeItem("Item" + i);
            assertTrue(inventory.addItem(item));
        }
    }

    @Test
    void testAddItemFailsWhenOverLimit() {
        for (int i = 0; i < 5; i++) {
            assertTrue(inventory.addItem(makeItem("Item" + i)));
        }

        InventoryItem extra = makeItem("Overflow");
        assertFalse(inventory.addItem(extra));
    }

    @Test
    void testRemoveItemSucceedsIfPresent() {
        InventoryItem item = makeItem("Potion");
        inventory.addItem(item);

        assertTrue(inventory.removeItem(item));
        assertFalse(inventory.getItems().contains(item));
    }

    @Test
    void testRemoveItemFailsIfAbsent() {
        InventoryItem item = makeItem("Ghost Item");

        assertFalse(inventory.removeItem(item));
    }

    @Test
    void testSetWeaponReplacesWeapon() {
        Weapon axe = new Weapon("Axe", "Choppy", "CHOP", 15);
        Weapon sword = new Weapon("Sword", "Sharp", "SLASH", 25);

        inventory.setWeapon(axe);
        assertEquals(axe, inventory.getWeapon().orElse(null));

        inventory.setWeapon(sword);
        assertEquals(sword, inventory.getWeapon().orElse(null));
    }

    private InventoryItem makeItem(String name) {
        return new InventoryItem(
                name,
                "Desc of " + name,
                "Use text for " + name,
                new Room[0],
                new Blockage("Fake", 1)
        );
    }
}