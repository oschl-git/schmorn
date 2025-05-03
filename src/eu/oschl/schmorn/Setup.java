package eu.oschl.schmorn;

import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;
import eu.oschl.textadventure.entities.Enemy;
import eu.oschl.textadventure.objects.Button;
import eu.oschl.textadventure.objects.InventoryItem;
import eu.oschl.textadventure.objects.Weapon;

class Setup {
    public static Game createGame() {
        // Base game setup
        var prologue = """
                You wake up in a dark room. You don't know how you got here.
                """;

        var epilogue = """
                Everyone is dead. You are the last one left.
                """;

        var unknownCommandMessages = new String[]{
                "Schmorn doesn't know how to do that.",
                "Schmorn does not understand what that means.",
                "Schmorn is confused by what you mean.",
        };

        // Rooms
        var royalWell = new Room(
                "Royal Well",
                "The well that the evil witch, Oglunda, ruler of the 7 swamps, threw Schmorn into. It is dark and wet."
        );

        var crossroads = new Room(
                "Crossroads",
                "A dark room in the underground, full of tunnels and entrances to other locations."
        );

        var sewerEntrance = new Room(
                "Sewer Entrance",
                "An entrance room to the Royal Sewer."
        );

        var swordsmithRatsLair = new Room(
                "Swordsmith Rat's Lair",
                "The lair of the Rat Swordsmith. He is a master of his craft, but he is also a rat."
        );

        var prisonEntrance = new Room(
                "Prison Entrance",
                "An entrance room to the Royal Prison."
        );

        var cellA = new Room(
                "Cell A",
                "The first cell of the Royal Prison."
        );

        var cellB = new Room(
                "Cell B",
                "The second cell of the Royal Prison."
        );

        var guardRoom = new Room(
                "Guard Room",
                "Guard room of the Royal Prison."
        );

        var prisonsEnd = new Room(
                "Prison's End",
                "The end of the Royal Prison."
        );

        var darkEntrance = new Room(
                "Dark Entrance",
                "An entrance to the deepest and darkest parts of the Royal Dungeon."
        );

        var blackAltar = new Room(
                "Black Altar",
                "A black altar in the middle of the Royal Dungeon. It is covered in blood and bones."
        );

        var shadowChamber = new Room(
                "Shadow Chamber",
                "A dark chamber in the Royal Dungeon. It is full of shadows and darkness."
        );

        var pathwaysEnd = new Room(
                "Pathway's End",
                "The end of the Pathway of Darkness, the darkest part of the Royal Dungeon."
        );

        var royalKitchensEntrance = new Room(
                "Royal Kitchens Entrance",
                "An entrance to the Royal Kitchens."
        );

        var pantry = new Room(
                "Pantry",
                "A room with food and supplies for the Royal Kitchens."
        );

        var kitchensEnd = new Room(
                "Kitchen's End",
                "The end of the Royal Kitchens."
        );

        var dungeonExit = new Room(
                "Dungeon Exit",
                "A way out. Are you certain you are ready to leave?"
        );

        var royalWellCrossroadsPassage = new Passage("rusted door", false);
        royalWellCrossroadsPassage.addRoom(royalWell);
        royalWellCrossroadsPassage.addRoom(crossroads);

        var crossroadsSewerEntrancePassage = new Passage("sewer hatch", false);
        crossroadsSewerEntrancePassage.addRoom(crossroads);
        crossroadsSewerEntrancePassage.addRoom(sewerEntrance);

        var sewerEntranceSwordsmithRatsLairPassage = new Passage("sliding iron door", true);
        sewerEntranceSwordsmithRatsLairPassage.addRoom(sewerEntrance);
        sewerEntranceSwordsmithRatsLairPassage.addRoom(swordsmithRatsLair);
        var sewerEntranceSwordsmithRatsLairPassageBlockage = new Blockage(
                "The door can't be opened and does not have a keyhole.",
                1
        );
        sewerEntranceSwordsmithRatsLairPassage.setBlockage(sewerEntranceSwordsmithRatsLairPassageBlockage);

        var crossroadsPrisonEntrancePassage = new Passage("prison threshold", true);
        crossroadsPrisonEntrancePassage.addRoom(crossroads);
        crossroadsPrisonEntrancePassage.addRoom(prisonEntrance);

        var prisonEntranceCellAPassage = new Passage("cell door A", false);
        prisonEntranceCellAPassage.addRoom(prisonEntrance);
        prisonEntranceCellAPassage.addRoom(cellA);

        var cellACellBPassage = new Passage("cell door B", false);
        cellACellBPassage.addRoom(cellA);
        cellACellBPassage.addRoom(cellB);

        var cellBGuardRoomPassage = new Passage("guard room door", true);
        cellBGuardRoomPassage.addRoom(cellB);
        cellBGuardRoomPassage.addRoom(guardRoom);

        var cellBPrisonsEndPassage = new Passage("iron door", false);
        cellBPrisonsEndPassage.addRoom(cellB);
        cellBPrisonsEndPassage.addRoom(prisonsEnd);

        var crossroadsDarkEntrancePassage = new Passage("dark passage", true);
        crossroadsDarkEntrancePassage.addRoom(crossroads);
        crossroadsDarkEntrancePassage.addRoom(darkEntrance);

        var crossroadsDarkEntrancePassageBlockage = new Blockage(
                "The door is locked. It has a large rusty keyhole, but you can't see through it, as if it was obscured by some dark magic.",
                1
        );
        crossroadsDarkEntrancePassage.setBlockage(crossroadsDarkEntrancePassageBlockage);

        var darkEntranceBlackAltarPassage = new Passage("menacing black door", false);
        darkEntranceBlackAltarPassage.addRoom(darkEntrance);
        darkEntranceBlackAltarPassage.addRoom(blackAltar);

        var blackAltarShadowChamberPassage = new Passage("shadow curtain", false);
        blackAltarShadowChamberPassage.addRoom(blackAltar);
        blackAltarShadowChamberPassage.addRoom(shadowChamber);

        var blackAltarPathwaysEndPassage = new Passage("rotten door", false);
        blackAltarPathwaysEndPassage.addRoom(blackAltar);
        blackAltarPathwaysEndPassage.addRoom(pathwaysEnd);

        var crossroadsRoyalKitchensEntrancePassage = new Passage("kitchen door", true);
        crossroadsRoyalKitchensEntrancePassage.addRoom(crossroads);
        crossroadsRoyalKitchensEntrancePassage.addRoom(royalKitchensEntrance);

        var crossroadsRoyalKitchensEntrancePassageBlockage = new Blockage(
                "The door is locked by a small, delicate, yet resilient lock.",
                1
        );
        crossroadsRoyalKitchensEntrancePassage.setBlockage(crossroadsRoyalKitchensEntrancePassageBlockage);

        var royalKitchensEntrancePantryPassage = new Passage("pantry door", false);
        royalKitchensEntrancePantryPassage.addRoom(royalKitchensEntrance);
        royalKitchensEntrancePantryPassage.addRoom(pantry);

        var pantryKitchensEndPassage = new Passage("kitchen door", false);
        pantryKitchensEndPassage.addRoom(pantry);
        pantryKitchensEndPassage.addRoom(kitchensEnd);

        var crossroadsDungeonExitPassage = new Passage(
                "stone lift",
                "a magical lift leading out of the dungeon",
                false
        );
        crossroadsDungeonExitPassage.addRoom(crossroads);
        crossroadsDungeonExitPassage.addRoom(dungeonExit);

        var crossroadsDungeonExitPassageBlockage = new Blockage(
                "The lift is inactive and does not move. There is a magical panel with three mysteriously shaped holes.",
                3
        );
        crossroadsDungeonExitPassage.setBlockage(crossroadsDungeonExitPassageBlockage);


        // Add game objects
        var swordsmithRatsLairEntrance = new Button(
                "steel button",
                "an ornamented button made of slightly rusted steel",
                sewerEntranceSwordsmithRatsLairPassageBlockage
        );
        prisonEntrance.addObject(swordsmithRatsLairEntrance);

        var sword = new Weapon(
                "ratsword",
                "a steel sword, expertly crafted by the Swordsmith Rat",
                10
        );
        swordsmithRatsLair.addObject(sword);
        
        var darkKey = new InventoryItem(
                "dark key",
                "a huge, menacing key, made out of black iron",
                new Room[]{crossroads},
                crossroadsDarkEntrancePassageBlockage
        );
        guardRoom.addObject(darkKey);

        var kitchenKey = new InventoryItem(
                "kitchen key",
                "a small, golden, delicate key",
                new Room[]{crossroads},
                crossroadsRoyalKitchensEntrancePassageBlockage
        );
        shadowChamber.addObject(kitchenKey);

        var frozenStone = new InventoryItem(
                "frozen stone",
                "a mysteriously shaped stone that is so cold it hurts to touch",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        prisonsEnd.addObject(frozenStone);

        var darkStone = new InventoryItem(
                "dark stone",
                "a mysteriously shaped stone that is so dark it seems to absorb all light",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        pathwaysEnd.addObject(darkStone);

        var fireStone = new InventoryItem(
                "fire stone",
                "a mysteriously shaped stone that is so hot it's almost impossible to carry",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        kitchensEnd.addObject(fireStone);

        var unbreakableLadle = new Weapon(
                "unbreakable ladle",
                "a mighty weapon, the most prized possession of the Head Chef, unbreakable and stronger than any sword",
                20
        );
        kitchensEnd.addObject(unbreakableLadle);

        // Add enemies
        var swordsmithRat = new Enemy(
                "Swordsmith Rat",
                "A world-renowned swordsmith, who has been locked up by Schmorn 10 years ago in his royal dungeon.",
                0
        );
        swordsmithRatsLair.setEnemy(swordsmithRat);

        var awakenedPrisoner = new Enemy(
                "Awakened Prisoner",
                "From this prisoner's appearance, you can tell that he's already been dead. However, he appears to be alive once more.",
                5
        );
        cellA.setEnemy(awakenedPrisoner);

        var fallenRatLord = new Enemy(
                "Jeremy, the Fallen Rat Lord",
                "A rat lord that has once rallied all the rats by singing and dancing. Schmorn has locked him up in his royal dungeon.",
                5
        );
        cellB.setEnemy(fallenRatLord);

        var x = new Enemy(
                "X",
                "A mysterious creature that is so dark it seems to absorb all light.",
                5
        );
        blackAltar.setEnemy(x);

        var headChef = new Enemy(
                "Head Chef",
                "The head chef of the Royal Kitchens. He does not approve of visitors.",
                5
        );
        pantry.setEnemy(headChef);

        // Create game object
        var game = new Game(
                prologue,
                epilogue,
                unknownCommandMessages
        );

        game.addRoom(royalWell);
        game.addRoom(crossroads);
        game.addRoom(sewerEntrance);
        game.addRoom(swordsmithRatsLair);
        game.addRoom(prisonEntrance);
        game.addRoom(cellA);
        game.addRoom(cellB);
        game.addRoom(guardRoom);
        game.addRoom(prisonsEnd);
        game.addRoom(darkEntrance);
        game.addRoom(blackAltar);
        game.addRoom(shadowChamber);
        game.addRoom(pathwaysEnd);
        game.addRoom(royalKitchensEntrance);
        game.addRoom(pantry);
        game.addRoom(kitchensEnd);
        game.addRoom(dungeonExit);

        game.setCurrentRoom(royalWell);

        return game;
    }
}
