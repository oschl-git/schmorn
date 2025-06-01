package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.exceptions.InvalidGameState;

import java.util.Optional;

/**
 * Represents a passage between two rooms in the game.
 *
 * @author Ondřej Schlaichert
 */
public class Passage {
    private Game game;

    private final String name;
    private final String description;
    private final boolean seeThrough;

    private final Room[] rooms;
    private Blockage blockage;

    public Passage(String name, String description, boolean seeThrough) {
        this.name = name;
        this.description = description;
        this.seeThrough = seeThrough;
        this.rooms = new Room[2];
    }

    public Passage(String name, boolean seeThrough) {
        this(name, null, seeThrough);
    }

    /**
     * Sets the game instance. This is typically called during the game's setup phase.
     *
     * @param game the game instance to associate
     */
    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;

        if (blockage != null) {
            blockage.setGame(game);
        }
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public boolean isSeeThrough() {
        return seeThrough;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Optional<Blockage> getBlockage() {
        return Optional.ofNullable(blockage);
    }

    /**
     * Adds a room to this passage. A passage can only have two rooms.
     *
     * @param room the room to add
     * @throws InvalidGameState if the passage already has two rooms
     */
    public void addRoom(Room room) {
        room.addPassage(this);
        room.setGame(game);

        for (Room r : rooms) {
            if (r == room) {
                return;
            }
        }

        if (rooms[0] == null) {
            rooms[0] = room;
        } else if (rooms[1] == null) {
            rooms[1] = room;
        } else {
            throw new InvalidGameState("Attempted to add third room to passage");
        }
    }

    /**
     * Attempts to pass through the passage to the other room.
     *
     * @param goingBack true if the player is going back through the passage they entered before, false otherwise
     * @return true if the passage was successfully passed, false otherwise
     */
    public boolean passThrough(boolean goingBack) {
        if (!canPass()) {
            return false;
        }

        if (!goingBack) {
            game.addPreviousPassage(this);
        }

        getOtherRoom(game.getCurrentRoom()).enter();

        return true;
    }

    /**
     * Sets the blockage for this passage.
     *
     * @param blockage the blockage to set
     */
    public void setBlockage(Blockage blockage) {
        blockage.setGame(game);
        this.blockage = blockage;
    }

    /**
     * Checks if the player can pass through this passage.
     *
     * @return true if the player can pass, false otherwise
     */
    public boolean canPass() {
        var room = this.game.getCurrentRoom();

        if (!room.getPassages().contains(this)) {
            return false;
        }

        var playerGoingBack = game.getLastPassage().isPresent() && game.getLastPassage().get() == this;
        if (room.isBlockedByEnemy() && !playerGoingBack) {
            return false;
        }

        return getBlockage().isEmpty() || getBlockage().get().canPass();
    }

    /**
     * Gets the other room connected by this passage.
     *
     * @param room the current room
     * @return the other room connected by this passage
     * @throws InvalidGameState if the provided room is not one of the rooms connected by this passage
     */
    public Room getOtherRoom(Room room) {
        if (rooms[0] == room) {
            return rooms[1];
        } else if (rooms[1] == room) {
            return rooms[0];
        } else {
            throw new InvalidGameState("Neither room leading to accessed passage is the current room");
        }
    }
}