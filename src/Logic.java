import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The Logic class handles game logic and command processing for a text-based adventure game.
 */
public class Logic {

    /**
     * Processes a user command and executes corresponding actions.
     *
     * @param command The command entered by the user.
     */
    public static void handleCommand(String command) {
        // Split the command into parts based on spaces
        String[] parts = command.split(" ");
        // First part of the command is assumed to be the action
        String commandAction = parts[0];

        Commands enteredCommand = null;
        // Check if the entered command matches any defined Commands enum
        for (Commands cmd : Commands.values()) {
            if (cmd.toString().equalsIgnoreCase(commandAction)) {
                enteredCommand = cmd;
                break;
            }
        }

        // Execute the command if it matches a defined enum value
        if (enteredCommand != null) {
            switch (enteredCommand) {
                case help:
                    // Print help message
                    System.out.print(Strings.HELP_MESAGE);
                    break;

                case goTo:
                    // Check if a room name is provided and call goToRoom method
                    if (parts.length > 1) {
                        String roomName = parts[1];
                        goToRoom(roomName);
                    } else {
                        // Error message if room name is missing
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case whereAmI:
                    // Display current room description
                    System.out.print(Main.getRooms().get(Main.getCurrentRoom()).description());
                    break;

                case map:
                    // Display ASCII map
                    System.out.print(Strings.ASCII_MAP);
                    break;

                case search:
                    // Display items in the current room
                    System.out.print(Main.getCurrentRoom() + ": " + Main.getRooms().get(Main.getCurrentRoom()).items());
                    break;

                case pick:
                    // Check if an item name is provided and call pickItem method
                    if (parts.length > 1) {
                        String item = parts[1];
                        pickItem(item);
                    } else {
                        // Error message if item name is missing
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case drop:
                    // Check if an item name is provided and call dropItem method
                    if (parts.length > 1) {
                        String item = parts[1];
                        dropItem(item);
                    } else {
                        // Error message if item name is missing
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case inventory:
                    // Display player's inventory
                    System.out.print(Player.getInventory());
                    break;

                case sudoWin:
                    // End the game prematurely
                    Main.setGamePlay(false);
                    break;

                case startRocket:
                    // Check conditions to start the rocket launch
                    if (Objects.equals(Main.getCurrentRoom(), Rooms.LAUNCH_PAD.name())) {
                        List<String> itemsToCheck = Arrays.asList("rocket_computer", "canister_with_fuel");

                        boolean allItemsPresent = true;
                        String missingItem = "";
                        // Check if all required items are in player's inventory
                        for (String item : itemsToCheck) {
                            if (!Player.getInventory().contains(item)) {
                                missingItem = item;
                                allItemsPresent = false;
                                break;
                            }
                        }

                        // Start the rocket launch or display missing item message
                        if (allItemsPresent) {
                            Main.setGamePlay(false);
                        } else {
                            System.out.print("You are missing " + missingItem + "!");
                        }
                    } else {
                        // Error message if not at the launch pad
                        System.out.print("Go to launch pad first!");
                    }
                    break;

                default:
                    // Error message for unrecognized command
                    System.out.print(Strings.ERROR_COMMAND_MESSAGE);
            }
        } else {
            // Error message for unrecognized command
            System.out.print(Strings.ERROR_COMMAND_MESSAGE);
        }
    }

    /**
     * Changes the current room to the specified room name.
     *
     * @param roomName The name of the room to move to.
     */
    private static void goToRoom(String roomName) {
        if (Main.getRooms().containsKey(roomName)) {
            if (!roomName.equals(Main.getCurrentRoom())) {
                Main.setCurrentRoom(roomName);
                System.out.print("You have entered the " + roomName + ".");
            } else {
                System.out.print("You are already in " + roomName + ".");
            }
        } else {
            // Error message for invalid room name
            System.out.print("Invalid room name. Try again.");
        }
    }

    /**
     * Allows the player to pick up an item from the current room.
     *
     * @param item The name of the item to pick up.
     */
    private static void pickItem(String item) {
        Room currentRoom = Main.getRooms().get(Main.getCurrentRoom());
        if (currentRoom.items().contains(item)) {
            if (Player.getInventory().size() < Player.getInventorySize()) {
                Player.getInventory().add(item);
                currentRoom.removeItem(item);
                System.out.print("Picked up " + item + ".");
            } else {
                // Error message if inventory is full
                System.out.print("Inventory is full. Drop an item to pick up " + item + ".");
            }
        } else {
            // Error message if item is not found in the room
            System.out.print("Item not found in the room.");
        }
    }

    /**
     * Allows the player to drop an item into the current room.
     *
     * @param item The name of the item to drop.
     */
    private static void dropItem(String item) {
        Room currentRoom = Main.getRooms().get(Main.getCurrentRoom());
        if (Player.getInventory().contains(item)) {
            Player.getInventory().remove(item);
            currentRoom.addItem(item);
            System.out.print(item + " is dropped into: " + currentRoom.name());
        } else {
            // Error message if item is not found in the inventory
            System.out.print("Item not found in the inventory.");
        }
    }
}