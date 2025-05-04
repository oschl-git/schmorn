package eu.oschl.textadventure;

import eu.oschl.textadventure.exceptions.InvalidGameState;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

public class Game {
    private final String prologue;
    private final String epilogue;
    private final String[] unknownCommandMessages;

    private final Inventory inventory;
    private final ArrayList<Room> rooms;
    private Room currentRoom;
    private final Stack<Passage> previousPassages;
    private boolean runnning;

    public Game(String prologue, String epilogue, String[] unknownCommandMessages) {
        this.prologue = prologue;
        this.epilogue = epilogue;
        this.unknownCommandMessages = unknownCommandMessages;

        this.inventory = new Inventory();
        this.rooms = new ArrayList<>();
        this.previousPassages = new Stack<>();
        this.runnning = true;
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

    public void addPreviousPassage(Passage previousPassage) {
        previousPassages.push(previousPassage);
    }

    public Optional<Passage> getLastPassage() {
        if (previousPassages.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(previousPassages.peek());
    }

    public void removeLastPassage() {
        if (previousPassages.isEmpty()) {
            throw new InvalidGameState("Attempted to remove last passage, but stack is empty");
        }

        previousPassages.pop();
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
