package eu.oschl.textadventure;

import java.util.ArrayList;

public class Game {
    private final String prologue;
    private final String epilogue;
    private final String[] unknownCommandMessages;

    private boolean runnning;
    private final Player player;
    private ArrayList<Room> rooms;
    private Room currentRoom;

    private ArrayList<String> gameState;

    public Game(String prologue, String epilogue, String[] unknownCommandMessages) {
        this.prologue = prologue;
        this.epilogue = epilogue;
        this.unknownCommandMessages = unknownCommandMessages;

        this.runnning = true;
        this.player = new Player();
        this.gameState = new ArrayList<>();
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

    public void stop() {
        this.runnning = false;
    }

    public void addRoom(Room room) {
        room.setGame(this);
        this.rooms.add(room);
    }
}
