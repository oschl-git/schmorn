package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.entities.Enemy;
import eu.oschl.textadventure.objects.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

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

    public boolean enter() {
        game.setCurrentRoom(this);
        entered = true;

        return true;
    }

    public boolean wasEntered() {
        return entered;
    }

    public void addPassage(Passage passage) {
        passage.setGame(game);
        passages.add(passage);
    }

    public HashSet<Passage> getPassages() {
        return passages;
    }

    public void setEnemy(Enemy enemy) {
        if (enemy != null) {
            enemy.setGame(game);
        }

        this.enemy = enemy;
    }

    public Optional<Enemy> getEnemy() {
        return Optional.ofNullable(enemy);
    }

    public boolean isBlockedByEnemy() {
        return getEnemy().isPresent() && getEnemy().get().isAlive();
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject object) {
        object.setGame(game);
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }
}