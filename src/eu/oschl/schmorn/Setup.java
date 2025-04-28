package eu.oschl.schmorn;

import eu.oschl.textadventure.Blockage;
import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.Passage;
import eu.oschl.textadventure.Room;

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

        var game = new Game(
                prologue,
                epilogue,
                unknownCommandMessages
        );

        // Rooms
        var royalWell = new Room(
                "Royal Well",
                "The well that the evil witch, Oglunda, ruler of the 7 swamps, threw you into. It is dark and wet."
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
                "Pathways End",
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

        var royalWellCrossroadsPassage = new Passage("Rusted door", false);
        royalWellCrossroadsPassage.addRoom(royalWell);
        royalWellCrossroadsPassage.addRoom(crossroads);

        var crossroadsSewerEntrancePassage = new Passage("Sewer hatch", false);
        crossroadsSewerEntrancePassage.addRoom(crossroads);
        crossroadsSewerEntrancePassage.addRoom(sewerEntrance);

        var sewerEntranceSwordsmithRatsLairPassage = new Passage("Sliding iron door", true);
        sewerEntranceSwordsmithRatsLairPassage.addRoom(sewerEntrance);
        sewerEntranceSwordsmithRatsLairPassage.addRoom(swordsmithRatsLair);
        var sewerEntranceSwordsmithRatsLairPassageBlockage = new Blockage(
                "The door can't be opened and does not have a keyhole",
                1
        );
        sewerEntranceSwordsmithRatsLairPassage.setBlockage(sewerEntranceSwordsmithRatsLairPassageBlockage);

        var crossroadsPrisonEntrancePassage = new Passage("Prison threshold", true);
        crossroadsPrisonEntrancePassage.addRoom(crossroads);
        crossroadsPrisonEntrancePassage.addRoom(prisonEntrance);

        var prisonEntranceCellAPassage = new Passage("Cell door A", false);
        prisonEntranceCellAPassage.addRoom(prisonEntrance);
        prisonEntranceCellAPassage.addRoom(cellA);

        var cellACellBPassage = new Passage("Cell door B", false);
        cellACellBPassage.addRoom(cellA);
        cellACellBPassage.addRoom(cellB);

        var cellBGuardRoomPassage = new Passage("Guard room door", true);
        cellBGuardRoomPassage.addRoom(cellB);
        cellBGuardRoomPassage.addRoom(guardRoom);

        var cellBPrisonsEndPassage = new Passage("Iron door", false);
        cellBPrisonsEndPassage.addRoom(cellB);
        cellBPrisonsEndPassage.addRoom(prisonsEnd);

        var crossroadsDarkEntrancePassage = new Passage("Dark passage", true);
        crossroadsDarkEntrancePassage.addRoom(crossroads);
        crossroadsDarkEntrancePassage.addRoom(darkEntrance);

        var crossroadsDarkEntrancePassageBlockage = new Blockage(
                "The door is locked and has a giant, rusty keyhole.",
                1
        );
        crossroadsDarkEntrancePassage.setBlockage(crossroadsDarkEntrancePassageBlockage);

        var darkEntranceBlackAltarPassage = new Passage("Menacing black door", false);
        darkEntranceBlackAltarPassage.addRoom(darkEntrance);
        darkEntranceBlackAltarPassage.addRoom(blackAltar);

        var blackAltarShadowChamberPassage = new Passage("Shadow curtain", false);
        blackAltarShadowChamberPassage.addRoom(blackAltar);
        blackAltarShadowChamberPassage.addRoom(shadowChamber);

        var blackAltarPathwaysEndPassage = new Passage("Rotted door", false);
        blackAltarPathwaysEndPassage.addRoom(blackAltar);
        blackAltarPathwaysEndPassage.addRoom(pathwaysEnd);

        var crossroadsRoyalKitchensEntrancePassage = new Passage("Kitchen door", true);
        crossroadsRoyalKitchensEntrancePassage.addRoom(crossroads);
        crossroadsRoyalKitchensEntrancePassage.addRoom(royalKitchensEntrance);

        var crossroadsRoyalKitchensEntrancePassageBlockage = new Blockage(
                "The door is locked by a small, delicate, yet resilient lock.",
                1
        );
        crossroadsRoyalKitchensEntrancePassage.setBlockage(crossroadsRoyalKitchensEntrancePassageBlockage);

        var royalKitchensEntrancePantryPassage = new Passage("Pantry door", false);
        royalKitchensEntrancePantryPassage.addRoom(royalKitchensEntrance);
        royalKitchensEntrancePantryPassage.addRoom(pantry);

        var pantryKitchensEndPassage = new Passage("Kitchen door", false);
        pantryKitchensEndPassage.addRoom(pantry);
        pantryKitchensEndPassage.addRoom(kitchensEnd);

        return game;
    }
}
