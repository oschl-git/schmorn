package eu.oschl.textadventure;

import eu.oschl.textadventure.exceptions.IncorrectGameSetup;

public class Passage {
    private Game game;

    private String name;
    private String description;

    private final Room[] rooms;
    private Blockage blockage;

    public Passage(String name, String description) {
        this.name = name;
        this.description = description;
        this.rooms = new Room[2];
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void addRoom(Room room) {
        if (rooms[0] == null) {
            rooms[0] = room;
        } else if (rooms[1] == null) {
            rooms[1] = room;
        } else {
            throw new IncorrectGameSetup("Attempted to add third room to passage");
        }
    }

    public void setBlockage(Blockage blockage) {
        this.blockage = blockage;
    }

    public Room getOtherRoom(Room room) {
        if (rooms[0] == room) {
            return rooms[1];
        } else if (rooms[1] == room) {
            return rooms[0];
        } else {
            throw new IncorrectGameSetup("Neither room leading to accessed passage is the current room");
        }
    }
}