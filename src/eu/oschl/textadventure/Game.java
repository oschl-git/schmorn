package eu.oschl.textadventure;

import eu.oschl.textadventure.exceptions.InvalidGameState;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

/**
 * Encapsulates the entire game, including its state, rooms, inventory, and control flow.
 *
 * @author Ondřej Schlaichert
 */
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

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Stack<Passage> getPreviousPassages() {
        return previousPassages;
    }

    public boolean isRunning() {
        return runnning;
    }

    /**
     * Adds a room to the game.
     *
     * @param room the room to add
     */
    public void addRoom(Room room) {
        room.setGame(this);
        this.rooms.add(room);
    }

    /**
     * Sets the current room.
     *
     * @param room the room to set as the current room
     */
    public void setCurrentRoom(Room room) {
        room.setGame(this);
        this.currentRoom = room;
    }

    /**
     * Adds a previous passage to the stack of previous passages.
     *
     * @param previousPassage the passage to add
     */
    public void addPreviousPassage(Passage previousPassage) {
        previousPassages.push(previousPassage);
    }

    /**
     * Retrieves the last passage from the stack of previous passages.
     *
     * @return an Optional containing the last passage if it exists, or an empty Optional if the stack is empty
     */
    public Optional<Passage> getLastPassage() {
        if (previousPassages.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(previousPassages.peek());
    }

    /**
     * Removes the last passage from the stack of previous passages.
     *
     * @throws InvalidGameState if the stack is empty
     */
    public void removeLastPassage() {
        if (previousPassages.isEmpty()) {
            throw new InvalidGameState("Attempted to remove last passage, but stack is empty");
        }

        previousPassages.pop();
    }

    /**
     * Finishes the game by setting the running state to false.
     */
    public void finish() {
        this.runnning = false;
    }
}
