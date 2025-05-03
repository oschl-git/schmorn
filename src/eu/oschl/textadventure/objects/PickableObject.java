package eu.oschl.textadventure.objects;

public abstract class PickableObject extends GameObject {
    public PickableObject(String name, String description) {
        super(name, description);
    }

    public abstract boolean pickUp();
}
