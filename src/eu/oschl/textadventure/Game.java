package eu.oschl.textadventure;

import java.util.ArrayList;

public class Game {
    private final String prologue;
    private final String epilogue;
    private final String[] unknownCommandMessages;

    private boolean runnning;
    private final Player player;
    private final ArrayList<Room> rooms;
    private Room currentRoom;

    public Game(String prologue, String epilogue, String[] unknownCommandMessages) {
        this.prologue = prologue;
        this.epilogue = epilogue;
        this.unknownCommandMessages = unknownCommandMessages;

        this.runnning = true;
        this.player = new Player();
        this.rooms = new ArrayList<>();
    }

    public String getPrologue() {
        return prologue;
    }

    public String getEpilogue() {
        return epilogue;
    }

    public String[] getUnknownCommandMessages() {
        return unknownCommandMessages;
    }

    public boolean isRunning() {
        return runnning;
    }

    public void finish() {
        this.runnning = false;
    }

    public void addRoom(Room room) {
        room.setGame(this);
        this.rooms.add(room);
    }

    public void setStartingRoom(Room room) {
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
