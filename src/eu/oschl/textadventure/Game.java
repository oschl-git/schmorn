package eu.oschl.textadventure;

import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;

import java.util.ArrayList;
import java.util.Optional;

public class Game {
    private final ActionController actionController;

    private final String prologue;
    private final String epilogue;
    private final String[] unknownCommandMessages;

    private final Inventory inventory;
    private final ArrayList<Room> rooms;
    private Room currentRoom;
    private Passage lastPassage;
    private boolean runnning;

    public Game(String prologue, String epilogue, String[] unknownCommandMessages) {
        this.actionController = new ActionController(this);

        this.prologue = prologue;
        this.epilogue = epilogue;
        this.unknownCommandMessages = unknownCommandMessages;

        this.inventory = new Inventory();
        this.rooms = new ArrayList<>();
        this.runnning = true;
    }

    public ActionController getActionController() {
        return actionController;
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

    public void addRoom(Room room) {
        room.setGame(this);
        this.rooms.add(room);
    }

    public void setCurrentRoom(Room room) {
        room.setGame(this);
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setLastPassage(Passage lastPassage) {
        this.lastPassage = lastPassage;
    }

    public Optional<Passage> getLastPassage() {
        return Optional.ofNullable(lastPassage);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isRunning() {
        return runnning;
    }

    public void finish() {
        this.runnning = false;
    }
}
