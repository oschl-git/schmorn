package eu.oschl.textadventure;

import java.util.ArrayList;

public class Game {
    private final String prologue;
    private final String epilogue;

    private final Player player;
    private ArrayList<Room> rooms;
    private Room currentRoom;

    private ArrayList<String> gameState;

    public Game(String prologue, String epilogue) {
        this.prologue = prologue;
        this.epilogue = epilogue;
        this.player = new Player();
        this.gameState = new ArrayList<>();
    }

    public String getPrologue() {
        return prologue;
    }

    public String getEpilogue() {
        return epilogue;
    }

    public void addRoom(Room room) {
        room.setGame(this);
        this.rooms.add(room);
    }
}
