package eu.oschl.textadventure;

public class Object {
    private Game game;

    private String name;
    private String description;

    private boolean canBeTaken;
    private boolean canBeUsedFromInventory;
    private boolean canBeUsedDirectly;

    private String gameStateWhenUsed;
    private Room mustBeUsedIn;

    public Object(
            String name,
            String description,
            boolean canBeTaken,
            boolean canBeUsedFromInventory,
            boolean canBeUsedDirectly,
            String gameStateWhenUsed,
            Room mustBeUsedIn
    ) {
        this.name = name;
        this.description = description;
        this.canBeTaken = canBeTaken;
        this.canBeUsedFromInventory = canBeUsedFromInventory;
        this.canBeUsedDirectly = canBeUsedDirectly;
        this.gameStateWhenUsed = gameStateWhenUsed;
        this.mustBeUsedIn = mustBeUsedIn;
    }

    public Object(
            String name,
            String description,
            boolean canBeTaken,
            boolean canBeUsedFromInventory,
            boolean canBeUsedDirectly,
            String gameStateWhenUsed
    ) {
        this (
            name,
            description,
            canBeTaken,
            canBeUsedFromInventory,
            canBeUsedDirectly,
            gameStateWhenUsed,
            null
        );
    }

    public static Object createPickableObject(
            String name,
            String description,
            String gameStateWhenUsed,
            Room mustBeUsedIn
    ) {
        return new Object(
                name,
                description,
                true,
                false,
                false,
                gameStateWhenUsed,
                mustBeUsedIn
        );
    }
}
