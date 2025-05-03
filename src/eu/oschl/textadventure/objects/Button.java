package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;

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

    public boolean press() {
        if (pressed) {
            return false;
        }

        interactsWith.interact();
        pressed = true;
        return true;
    }
}
