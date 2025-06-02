package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.BaseGameTest;
import eu.oschl.textadventure.map.Blockage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ButtonTest extends BaseGameTest {
    private Blockage blockage;
    private Button button;

    @BeforeEach
    void setUp() {
        blockage = new Blockage("Door", 1);
        button = new Button(
                "Red Button",
                "A suspiciously large red button.",
                "You hear a satisfying *click*.",
                blockage
        );
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals("Red Button", button.getName());
        assertEquals("A suspiciously large red button.", button.getDescription());
        assertEquals("You hear a satisfying *click*.", button.getPressText());
        assertEquals(blockage, button.getInteractsWith());
        assertFalse(button.isPressed());
    }

    @Test
    void testPressReturnsTrueFirstTime() {
        assertEquals(1, blockage.getRequiredInteractionsToPass());

        boolean result = button.press();

        assertTrue(result);
        assertTrue(button.isPressed());
        assertEquals(0, blockage.getRequiredInteractionsToPass());
    }

    @Test
    void testPressReturnsFalseIfAlreadyPressed() {
        assertTrue(button.press());
        assertFalse(button.press());
    }

    @Test
    void testMultipleButtonsAffectSameBlockage() {
        Button anotherButton = new Button(
                "Blue Button",
                "A secondary button.",
                "You press it too.",
                blockage
        );

        assertTrue(button.press());
        assertFalse(button.press());

        assertEquals(0, blockage.getRequiredInteractionsToPass());

        assertTrue(anotherButton.press());
        assertTrue(anotherButton.isPressed());
    }
}