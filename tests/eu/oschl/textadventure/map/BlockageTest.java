package eu.oschl.textadventure.map;

import eu.oschl.textadventure.BaseGameTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockageTest extends BaseGameTest {
    @Test
    void testConstructorInitializesFields() {
        Blockage blockage = new Blockage("A locked door", 3);

        assertEquals("A locked door", blockage.getDescription());
        assertEquals(3, blockage.getRequiredInteractionsToPass());
    }

    @Test
    void testInteractReducesInteractionCount() {
        Blockage blockage = new Blockage("Puzzle", 2);
        assertFalse(blockage.canPass());

        blockage.interact();
        assertEquals(1, blockage.getRequiredInteractionsToPass());
        assertFalse(blockage.canPass());

        blockage.interact();
        assertEquals(0, blockage.getRequiredInteractionsToPass());
        assertTrue(blockage.canPass());
    }

    @Test
    void testCanPassReturnsTrueWhenCountIsZeroOrLess() {
        Blockage blockage = new Blockage("Final door", 1);
        assertFalse(blockage.canPass());

        blockage.interact();
        assertTrue(blockage.canPass());

        blockage.interact();
        assertTrue(blockage.canPass());
    }
}