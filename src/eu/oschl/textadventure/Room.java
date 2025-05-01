package eu.oschl.textadventure;

import eu.oschl.textadventure.entities.Enemy;
import eu.oschl.textadventure.objects.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class Room {
    private Game game;

    private final String name;
    private final String description;

    private final HashSet<Passage> passages;
    private Blockage blockage;
    private final ArrayList<GameObject> objects;
    private Enemy enemy;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.passages = new HashSet<>();
        this.objects = new ArrayList<>();
    }

    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;

        for (Passage passage : passages) {
            passage.setGame(game);
        }

        if (blockage != null) {
            blockage.setGame(game);
        }

        if (enemy != null) {
            enemy.setGame(game);
        }

        for (GameObject object : objects) {
            object.setGame(game);
        }
    }

    public void addPassage(Passage passage) {
        passage.setGame(game);
        passages.add(passage);
    }

    public void setBlockage(Blockage blockage) {
        blockage.setGame(game);
        this.blockage = blockage;
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