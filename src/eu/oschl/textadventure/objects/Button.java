package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;

public class Button extends GameObject {
    private final Blockage interactsWith;
    private boolean pressed;

    public Button(String name, String description, Blockage interactsWith) {
        super(name, description);
        this.interactsWith = interactsWith;
        this.pressed = false;
    }

    public boolean use() {
        if (pressed) {
            return false;
        }

        interactsWith.interact();
        pressed = true;
        return true;
    }
}
