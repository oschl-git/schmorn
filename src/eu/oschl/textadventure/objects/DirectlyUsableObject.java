package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.Blockage;

public class DirectlyUsableObject extends GameObject {

    public DirectlyUsableObject(String name, String description, Blockage unblocks) {
        super(name, description, unblocks);
    }

    @Override
    public boolean use() {
        unblocks.unblock();
        return true;
    }
}
