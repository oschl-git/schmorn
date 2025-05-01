package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.exceptions.InvalidGameState;

import java.util.Optional;

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

    public String getDescription() {
        return description;
    }

    public boolean isSeeThrough() {
        return seeThrough;
    }

    public Room[] getRooms() {
        return rooms;
    }

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

    public boolean passThrough() {
        if (!canPass()) {
            return false;
        }

        this.game.setLastPassage(this);
        this.game.setCurrentRoom(getOtherRoom(game.getCurrentRoom()));

        return true;
    }

    public Optional<Blockage> getBlockage() {
        return Optional.ofNullable(blockage);
    }

    public void setBlockage(Blockage blockage) {
        blockage.setGame(game);
        this.blockage = blockage;
    }

    public boolean canPass() {
        return this.getBlockage().isEmpty();
    }

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