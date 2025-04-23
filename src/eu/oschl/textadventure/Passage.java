package eu.oschl.textadventure;

import java.util.Optional;

public class Passage {
    private String name;
    private String description;
    private Room room1;
    private Direction direction1;
    private Room room2;
    private Direction direction2;
    private Optional<Blockage> blockage;

    public Passage(String name, String description, Room room1, Direction direction1, Room room2, Direction direction2, Blockage blockage) {
        this.name = name;
        this.description = description;
        this.room1 = room1;
        this.direction1 = direction1;
        this.room2 = room2;
        this.direction2 = direction2;
        this.blockage = Optional.ofNullable(blockage);
    }
}