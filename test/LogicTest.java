import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));

        // Initialize the game environment
        Main.setGamePlay(true);
        Player.setInventory(new ArrayList<>());
        Player.setInventorySize(3);

        // Initialize rooms
        HashMap<String, Room> rooms = new HashMap<>();
        for (Rooms roomEnum : Rooms.values()) {
            rooms.put(roomEnum.name(), roomEnum.getRoom());
        }
        Main.setRooms(rooms);
        Main.setCurrentRoom(Rooms.LABORATORY.name());
    }

    @AfterEach
    public void tearDown() {
        Main.setGamePlay(false);
    }

    @Test
    public void testHelpCommand() {
        Logic.handleCommand("help");

        String expectedOutput = """
                Type 'help' to show this message.
                Type 'goTo ROOMNAME' to move to a different room.
                Type 'whereAmI' to check your current location.
                Type 'showMap' to display game map
                Type 'search' to show items in current room
                Type 'pick ITEM' to put item to your inventory
                Type 'inventory' to show your inventory
                Type 'startRocket' to win the game
                Type 'exit' to end the game.
                Press Enter to start the adventure...
                """;

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGoToCommandValid() {
        Logic.handleCommand("goTo LAUNCH_PAD");
        assertEquals(Rooms.LAUNCH_PAD.name(), Main.getCurrentRoom());
    }

    @Test
    public void testGoToCommandInvalid() {
        Logic.handleCommand("goTo invalid_room");
        assertEquals(Rooms.LABORATORY.name(), Main.getCurrentRoom());
    }

    @Test
    public void testWhereAmICommand() {
        Logic.handleCommand("whereAmI");
        String expectedOutput = "You are in the laboratory. The scent of chemicals lingers in the air. Tables are cluttered with scientific instruments, glassware, and ongoing experiments.";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMapCommand() {
        Logic.handleCommand("map");
        String expectedOutput = """
                                      
                                     ┌─────────────┐
                                     │launch pad 🚀│
                                     └─────┬┬──────┘
                                           ││
                                      ┌────┴┴────┐        ┌───────────┐
                                      │white room│        │server room│
                                      └────┬┬────┘        └────┬┬─────┘
                                           ││                  ││
                   ┌──────────┐   ┌────────┴┴────────┐   ┌─────┴┴───────┐
                   │laboratory│===│technical facility│===│control center│
                   └──────────┘   └────────┬┬────────┘   └──────────────┘
                                           ││
                                   ┌───────┴┴───────┐
                                   │Storage facility│
                                   └────────────────┘
                """;

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPickItemCommandValid() {
        Main.setCurrentRoom(Rooms.SERVER_ROOM.name());
        Logic.handleCommand("pick rocket_computer");
        Assertions.assertTrue(Player.getInventory().contains("rocket_computer"));
        Assertions.assertFalse(Main.getCurrentRoom().contains("rocket_computer"));
    }

    @Test
    public void testPickItemCommandInvalid() {
        Main.setCurrentRoom(Rooms.LABORATORY.name());
        Logic.handleCommand("pick invalid_item");
        Assertions.assertFalse(Player.getInventory().contains("invalid_item"));
    }

    @Test
    public void testDropItemCommandInvalid() {
        Main.setCurrentRoom(Rooms.LABORATORY.name());
        Logic.handleCommand("drop invalid_item");
        Assertions.assertFalse(Main.getCurrentRoom().contains("invalid_item"));
    }

    @Test
    public void testInventoryCommand() {
        Logic.handleCommand("pick beaker");
        Logic.handleCommand("inventory");
        Player.getInventory().add("beaker");
        String expectedOutput = "Picked up beaker.[beaker]";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testSudoWinCommand() {
        Logic.handleCommand("sudoWin");
        Assertions.assertFalse(Main.isGamePlay());
    }

    @Test
    public void testStartRocketCommandSuccess() {
        Main.setCurrentRoom(Rooms.LAUNCH_PAD.name());
        Player.getInventory().addAll(List.of("rocket_computer", "canister_with_fuel"));
        Logic.handleCommand("startRocket");
        Assertions.assertFalse(Main.isGamePlay());
    }

    @Test
    public void testStartRocketCommandMissingItem() {
        Main.setCurrentRoom(Rooms.LAUNCH_PAD.name());
        Player.getInventory().add("rocket_computer");
        Logic.handleCommand("startRocket");
        Assertions.assertTrue(Main.isGamePlay());
    }

    @Test
    public void testStartRocketCommandWrongRoom() {
        Main.setCurrentRoom(Rooms.LABORATORY.name());
        Player.getInventory().addAll(List.of("rocket_computer", "canister_with_fuel"));
        Logic.handleCommand("startRocket");
        Assertions.assertTrue(Main.isGamePlay());
    }

    @Test
    public void testInvalidCommand() {
        Logic.handleCommand("invalidCommand");
        String expectedOutput = "\uD83D\uDD34\uD83D\uDD34\uD83D\uDD34 Invalid command.";
        assertEquals(expectedOutput, outContent.toString());
    }
}