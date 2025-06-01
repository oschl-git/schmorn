package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.entities.Enemy;
import eu.oschl.textadventure.objects.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

/**
 * Represents a room in the game.
 *
 * @author Ondřej Schlaichert
 */
public class Room {
    private Game game;

    private final String name;
    private final String description;
    private final String enterText;

    private final HashSet<Passage> passages;
    private final ArrayList<GameObject> objects;
    private Enemy enemy;

    private boolean entered;

    public Room(String name, String description, String enterText) {
        this.name = name;
        this.description = description;
        this.enterText = enterText;
        this.passages = new HashSet<>();
        this.objects = new ArrayList<>();
        this.entered = false;
    }

    public Room(String name, String description) {
        this(name, description, null);
    }

    /**
     * Sets the game instance. This is typically called during the game's setup phase.
     *
     * @param game the game instance to associate
     */
    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;

        for (Passage passage : passages) {
            passage.setGame(game);
        }

        if (enemy != null) {
            enemy.setGame(game);
        }

        for (GameObject object : objects) {
            object.setGame(game);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Optional<String> getEnterText() {
        return Optional.ofNullable(enterText);
    }

    public HashSet<Passage> getPassages() {
        return passages;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public Optional<Enemy> getEnemy() {
        return Optional.ofNullable(enemy);
    }

    public boolean wasEntered() {
        return entered;
    }

    /**
     * Checks if the room is blocked by an enemy.
     * An enemy is considered to block the room if it is present and alive.
     *
     * @return true if the room is blocked by an enemy, false otherwise
     */
    public boolean isBlockedByEnemy() {
        return getEnemy().isPresent() && getEnemy().get().isAlive();
    }

    /**
     * Marks the room as entered and sets it as the current room in the game.
     * This method should be called when the player enters the room.
     *
     * @return true if the room was successfully entered, false otherwise
     */
    public boolean enter() {
        game.setCurrentRoom(this);
        entered = true;

        return true;
    }

    /**
     * Adds a passage to this room. A room can have multiple passages.
     *
     * @param passage the passage to add
     */
    public void addPassage(Passage passage) {
        passage.setGame(game);
        passages.add(passage);
    }

    /**
     * Sets an enemy for this room.
     *
     * @param enemy the enemy to set, or null to remove any existing enemy
     */
    public void setEnemy(Enemy enemy) {
        if (enemy != null) {
            enemy.setGame(game);
        }

        this.enemy = enemy;
    }

    /**
     * Adds a game object to this room.
     *
     * @param object the object to add
     */
    public void addObject(GameObject object) {
        object.setGame(game);
        objects.add(object);
    }

    /**
     * Removes a game object from this room.
     *
     * @param object the object to remove
     */
    public void removeObject(GameObject object) {
        objects.remove(object);
    }
}