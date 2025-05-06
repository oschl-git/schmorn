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
                The sun sets over a misty day as the witch Oglunda, ruler of the Seven Swamps, rides her carriage down the Royal Road. An agreement has been made, and she comes now to fulfil her side of it.
                
                The prince is waiting. Face illuminated by flickering candlelight, Schmorn, high prince of  the Grey Princedom, stares out of the window of his highest tower and watches as the witch's carriage makes its way towards the gate. When the carriage stops, he signals the guards, who reluctantly raise the iron bars.
                
                Moments later, Oglunda climbs the tower steps and enters. She sits across from the prince at a heavy wooden desk.
                
                "So, you're sure?" she asks, her voice raspy. "This is what you want? You've looked upon the world, weighed all the possibilities, and decided that what you desire most is unlimited power, no matter the cost?"
                
                "Yes," Schmorn replies.
                
                "Do you believe this will give you what you lack? The kingdoms snicker behind closed doors, whispering of your blatant incompetence, mistreatment of your subjects, the living conditions of your people. 'An insecure prince for an insecure land,' they say. Have you considered that this will not change their minds? That this is not the right way to go about it?"
                
                "They will fear me. All of them. None will be a match for what I'll be capable of," the prince replies, voice shaky with morbid excitement. "So keep your mouth shut, witch. You have not come here to speak of things already decided. Have you brought it?"
                
                "I have," Oglunda replies, reaches into her cloak and withdraws a small envelope, emptying its contents onto the table. In front of them now lies a small, silver ring, simple and unadorned, yet unnaturally beautiful.
                
                "I warn you for the last time, Schmorn, high prince of the Grey Princedom. Once you wear it, there will be consequences. Both for your realm, and for your soul."
                
                Schmorn, barely listening, eyes glued to the small ring, grabs it in his fingers and raises his hand toward a chandelier above. The ring reflects the golden light, casting delicate glimmers all around the room. He raises the ring finger of his left hand and determinedly puts it on.
                
                As he does so, his body starts to undulate and shift, and just a few moments later, the prince is gone completely. On the table now lies the ring, and, right next to it, squirms a large, repulsive worm.
                
                "I gave you a chance, idiot," the witch says, pockets the ring, grabs the worm, and makes her way down the tower. In the courtyard, people gather, murmuring.
                
                "Look here!" she calls and she raises Schmorn high above her head for everyone to see. "This is your prince. This is what remains of him. Take me to your deepest well!"
                
                The former prince's subjects lead Oglunda towards an ancient stone well in the middle of the courtyard, where she stops and triumphantly casts the worm into the depths. The people cheer, finally free of the rule of an incompetent, dangerous tyrant.
                
                And Schmorn falls...
                """;

        var epilogue = """
                Everyone is dead. You are the last one left.
                """;

        var unknownCommandMessages = new String[]{
                "Schmorn doesn't know how to do that.",
                "Schmorn does not understand what that means.",
                "Schmorn is confused.",
        };

        // Rooms
        var royalWell = new Room(
                "Royal Well",
                "The bottom of the deepest well of the Grey Princedom. Fortunately, there is no water in it."
        );

        var crossroads = new Room(
                "Crossroads",
                "A damp chamber in the Royal Dungeon, full of unnerving, dark tunnels and entrances to other locations."
        );

        var sewerEntrance = new Room(
                "Sewer Entrance",
                "An entrance room of the Royal Sewer."
        );

        var swordsmithRatsLair = new Room(
                "Swordsmith Rat's Lair",
                "The lair of the Rat Swordsmith. He is a master of his craft, but he is also a rat.",
                "Schmorn enters the chamber and is greeted by an unnerving sight. The Swordsmith Rat is lying on the floor, surrounded by his tools and weapons. He looks up at Schmorn with a mixture of fear and anger. Once a swordsmith of the greatest reputation, he has been broken down by Schmorn's imprisonment and has produced no remarkable weapons to speak of."
        );

        var prisonEntrance = new Room(
                "Prison Entrance",
                "An entrance room to Schmorn's Royal Prison."
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
                "In days long gone, when the Grey Princedom wasn't in disarray, this room was occuppied by a guard who took care of the prisoners."
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
                "An enormous, menacing room, full of bones and dead bodies. The centerpiece of the room is a large, black altar made of stone, surrounded by a circle of candles. On the surface are strange runes and symbols, which appear to be bleeding.",
                "Fear sets in as Schmorn enters the room, and nausea overcomes him. The air is putrid, and the stench of death is overwhelming. In the middle of the room stands a stone altar, covered in blood, surrounded by ominously flickering candles. The walls are adorned with grotesque carvings of twisted faces and bodies, and the floor is littered with bones and remnants of past sacrifices. Schmorn knows he is not alone. There is a being here, one not of this world."
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

        var royalWellCrossroadsPassage = new Passage("crack in the wall", true);
        royalWellCrossroadsPassage.addRoom(royalWell);
        royalWellCrossroadsPassage.addRoom(crossroads);

        var crossroadsSewerEntrancePassage = new Passage("sewer hatch", true);
        crossroadsSewerEntrancePassage.addRoom(crossroads);
        crossroadsSewerEntrancePassage.addRoom(sewerEntrance);

        var sewerEntranceSwordsmithRatsLairPassage = new Passage("sliding iron door", false);
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

        var cellBPrisonsEndPassage = new Passage("iron door", true);
        cellBPrisonsEndPassage.addRoom(cellB);
        cellBPrisonsEndPassage.addRoom(prisonsEnd);

        var crossroadsDarkEntrancePassage = new Passage("dark passage", false);
        crossroadsDarkEntrancePassage.addRoom(crossroads);
        crossroadsDarkEntrancePassage.addRoom(darkEntrance);

        var crossroadsDarkEntrancePassageBlockage = new Blockage(
                "The door is locked. It has a large rusty keyhole, which is impossible to see through, as if it was obscured by some dark magic.",
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

        var royalKitchensEntrancePantryPassage = new Passage("pantry door", true);
        royalKitchensEntrancePantryPassage.addRoom(royalKitchensEntrance);
        royalKitchensEntrancePantryPassage.addRoom(pantry);

        var pantryKitchensEndPassage = new Passage("backdoor", true);
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
                "The lift is dark, inactive and does not move. Inside, there is a magical stone tablet with three mysteriously shaped holes.",
                3
        );
        crossroadsDungeonExitPassage.setBlockage(crossroadsDungeonExitPassageBlockage);


        // Add game objects
        var swordsmithRatsLairEntrance = new Button(
                "steel button",
                "an ornamented button made of slightly rusted steel",
                "Schmorn presses the button and hears a distant scraping sound.",
                sewerEntranceSwordsmithRatsLairPassageBlockage
        );
        prisonEntrance.addObject(swordsmithRatsLairEntrance);

        var sword = new Weapon(
                "ratsword",
                "a steel sword, expertly crafted by the Swordsmith Rat",
                "Schmorn attacks with the ratsword.",
                10
        );
        swordsmithRatsLair.addObject(sword);
        
        var darkKey = new InventoryItem(
                "dark key",
                "a huge, menacing key, made out of black iron",
                "Schmorn used the key to unlock the dark passage.",
                new Room[]{crossroads},
                crossroadsDarkEntrancePassageBlockage
        );
        guardRoom.addObject(darkKey);

        var kitchenKey = new InventoryItem(
                "kitchen key",
                "a small, golden, delicate key",
                "Schmorn used the key to unlock the kitchen door.",
                new Room[]{crossroads},
                crossroadsRoyalKitchensEntrancePassageBlockage
        );
        shadowChamber.addObject(kitchenKey);

        var frozenStone = new InventoryItem(
                "frozen stone",
                "a mysteriously shaped stone that is so cold it hurts to touch",
                "Schmorn inserted the stone into the lift panel.",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        prisonsEnd.addObject(frozenStone);

        var darkStone = new InventoryItem(
                "dark stone",
                "a mysteriously shaped stone that is so dark it seems to absorb all light",
                "Schmorn inserted the stone into the lift panel.",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        pathwaysEnd.addObject(darkStone);

        var fireStone = new InventoryItem(
                "fire stone",
                "a mysteriously shaped stone that is so hot it's almost impossible to carry",
                "Schmorn inserted the stone into the lift panel.",
                new Room[]{crossroads},
                crossroadsDungeonExitPassageBlockage
        );
        kitchensEnd.addObject(fireStone);

        var unbreakableLadle = new Weapon(
                "unbreakable ladle",
                "a mighty weapon, the most prized possession of the Head Chef, unbreakable and stronger than any sword",
                "Schmorn attacks with the mighy Unbreakable Ladle.",
                20
        );
        royalKitchensEntrance.addObject(unbreakableLadle);

        // Add enemies
        var swordsmithRat = new Enemy(
                "Swordsmith Rat",
                "A world-renowned swordsmith, locked up in the Royal Dungeon by prince Schmorn in hopes of getting him to craft powerful weapons for his armies.",
                "The swordsmith does not protest or fight back, accepting his fate. Schmorn kills him with a single unarmed blow.",
                0
        );
        swordsmithRatsLair.setEnemy(swordsmithRat);

        var awakenedPrisoner = new Enemy(
                "Awakened Prisoner",
                "From this prisoner's appearance, it is clear that she is already dead. Nonetheless, she is moving and dangerous.",
                5
        );
        cellA.setEnemy(awakenedPrisoner);

        var jeremy = new Enemy(
                "Jeremy",
                "A rat lord who has once rallied all the rats by singing and dancing. Jealous of his charisma and influence, Schmorn has locked him up in his Royal Dungeon.",
                5
        );
        cellB.setEnemy(jeremy);

        var x = new Enemy(
                "X",
                "A terrifying, dark creature, not of this world. Despite the candlelight, only darkness can be seen where X is standing; it's impossible to make out any of its features. The creature is a guardian of the altar.",
                "As the blade of the Ratsword sinks into the creature's body, it lets out a horrible screech. The sound is so loud that it shatters the candles surrounding the altar, and the darkness surrounding X dissipates. The creature's body evaporates into smoke, leaving nothing behind.",
                5
        );
        blackAltar.setEnemy(x);

        var headChef = new Enemy(
                "Head Chef",
                "The head chef of the Royal Kitchens. He does not approve of visitors.",
                10
        );
        pantry.setEnemy(headChef);

        var oglunda = new Enemy(
                "Oglunda",
                "The witch Oglunda, ruler of the Seven Swamps. She is the one who cursed Schmorn.",
                15,
                true
        );

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
