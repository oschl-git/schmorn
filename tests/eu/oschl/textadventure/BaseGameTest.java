package eu.oschl.textadventure;

import eu.oschl.textadventure.entities.Enemy;
import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;
import eu.oschl.textadventure.objects.Button;
import eu.oschl.textadventure.objects.Weapon;
import org.junit.jupiter.api.BeforeEach;

/**
 * Provides a base game object for other tests to use.
 *
 * @author Ondřej Schlaichert
 */
abstract class BaseGameTest {
    protected Game game;

    @BeforeEach
    void setUpGame() {
        var prologue = "This is a prologue.";
        var epilogue = "This is an epilogue.";

        var unknownCommandMessages = new String[]{
                "This is a message informing the player that the command is unknown.",
        };

        var room1 = new Room(
                "Room 1",
                "This is room number one."
        );

        var room2 = new Room(
                "Room 2",
                "This is room number two."
        );

        var passage = new Passage("Passage", true);
        passage.addRoom(room1);
        passage.addRoom(room2);

        var blockage = new Blockage("Blockage", 1);
        passage.setBlockage(blockage);

        var button = new Button(
                "Button",
                "This is a button.",
                "You have pressed the button.",
                blockage
        );
        room1.addObject(button);

        var weapon = new Weapon(
                "Sword",
                "This is a sword.",
                "You have picked up the sword.",
                10
        );
        room1.addObject(weapon);

        var finalBoss = new Enemy(
                "Enemy",
                "This is an enemy.",
                "You have defeated the enemy!",
                10,
                true
        );
        room2.setEnemy(finalBoss);

        game = new Game(
                prologue,
                epilogue,
                unknownCommandMessages
        );

        game.addRoom(room1);
        game.addRoom(room2);

        game.setCurrentRoom(room1);
    }
}