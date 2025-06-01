package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;

/**
 * Represents a button in the game that can be pressed exactly once to interact with a blockage.
 *
 * @author Ondřej Schlaichert
 */
public class Button extends GameObject {
    private final String pressText;
    private final Blockage interactsWith;
    private boolean pressed;

    public Button(String name, String description, String pressText, Blockage interactsWith) {
        super(name, description);
        this.pressText = pressText;
        this.interactsWith = interactsWith;
        this.pressed = false;
    }

    public String getPressText() {
        return pressText;
    }

    public Blockage getInteractsWith() {
        return interactsWith;
    }

    public boolean isPressed() {
        return pressed;
    }

    /**
     * Attempts to press the button. If the button has already been pressed, it does nothing.
     *
     * @return true if the button was successfully pressed, false if it was already pressed
     */
    public boolean press() {
        if (isPressed()) {
            return false;
        }

        interactsWith.interact();
        pressed = true;
        return true;
    }
}
