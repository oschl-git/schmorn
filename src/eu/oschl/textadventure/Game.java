package eu.oschl.textadventure;

public class Game {
    private final String prologue;
    private final String epilogue;

    private Player player;
    private Room[] rooms;
    private Passage[] passages;

    private Room currentRoom;

    public Game(Room[] rooms, Room startingRoom, String prologue, String epilogue) {
        this.player = new Player();
        this.prologue = prologue;
        this.epilogue = epilogue;
        this.rooms = rooms;
        this.currentRoom = startingRoom;
    }

    public String getPrologue() {
        return prologue;
    }

    public String getEpilogue() {
        return epilogue;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
