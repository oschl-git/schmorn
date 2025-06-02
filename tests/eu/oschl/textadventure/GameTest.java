package eu.oschl.textadventure;

import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.objects.Button;
import eu.oschl.textadventure.objects.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest extends BaseGameTest {
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