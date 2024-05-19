import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Logic {
    static void handleCommand(String command) {
        String[] parts = command.split(" ");
        String commandAction = parts[0];

        Commands enteredCommand = null;
        for (Commands cmd : Commands.values()) {
            if (cmd.toString().equalsIgnoreCase(commandAction)) {
                enteredCommand = cmd;
                break;
            }
        }

        if (enteredCommand != null) {
            switch (enteredCommand) {
                case help:
                    System.out.print(Strings.HELP_MESAGE);
                    break;

                case goTo:
                    if (parts.length > 1) {
                        String roomName = parts[1];
                        goToRoom(roomName);
                    } else {
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case whereAmI:
                    System.out.print(Main.getRooms().get(Main.getCurrentRoom()).description());
                    break;

                case map:
                    System.out.print(Strings.ASCII_MAP);
                    break;

                case search:
                    System.out.print(Main.getCurrentRoom() + ": " + Main.getRooms().get(Main.getCurrentRoom()).items());
                    break;

                case pick:
                    if (parts.length > 1) {
                        String item = parts[1];
                        pickItem(item);
                    } else {
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case drop:
                    if (parts.length > 1) {
                        String item = parts[1];
                        dropItem(item);
                    } else {
                        System.out.print(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case inventory:
                    System.out.print(Player.getInventory());
                    break;

                case sudoWin:
                    Main.setGamePlay(false);

                case startRocket:
                    if (Objects.equals(Main.getCurrentRoom(), Rooms.LAUNCH_PAD.name())) {
                        List<String> itemsToCheck = Arrays.asList("rocket_computer", "canister_with_fuel");

                        boolean allItemsPresent = true;
                        String missingItem = "";
                        for (String item : itemsToCheck) {
                            if (!Player.getInventory().contains(item)) {
                                missingItem = item;
                                allItemsPresent = false;
                                break;
                            }
                        }

                        if (allItemsPresent) {
                            Main.setGamePlay(false);
                        } else {
                            System.out.print("You are missing " + missingItem + "!");
                        }
                    } else {
                        System.out.print("Go to launch pad fist!");
                    }

                    break;

                default:
                    System.out.print(Strings.ERROR_COMMAND_MESSAGE);
            }
        } else {
            System.out.print(Strings.ERROR_COMMAND_MESSAGE);
        }
    }

    private static void goToRoom(String roomName) {
        if (Main.getRooms().containsKey(roomName)) {
            if (!roomName.equals(Main.getCurrentRoom())) {
                Main.setCurrentRoom(roomName);
                System.out.print("You have entered the " + roomName + ".");
            } else {
                System.out.print("You are already in " + roomName + ".");
            }
        } else {
            System.out.print("Invalid room name. Try again.");
        }
    }

    private static void pickItem(String item) {
        Room currentRoom = Main.getRooms().get(Main.getCurrentRoom());
        if (currentRoom.items().contains(item)) {
            if (Player.getInventory().size() < Player.getInventorySize()) {
                Player.getInventory().add(item);
                currentRoom.removeItem(item);
                System.out.print("Picked up " + item + ".");
            } else {
                System.out.print("Inventory is full. Drop an item to pick up " + item + ".");
            }
        } else {
            System.out.print("Item not found in the room.");
        }
    }

    private static void dropItem(String item) {
        Room currentRoom = Main.getRooms().get(Main.getCurrentRoom());
        if (Player.getInventory().contains(item)) {
            Player.getInventory().remove(item);
            currentRoom.addItem(item);
            System.out.print(item + " is dropped in to: " + currentRoom.name());
        } else {
            System.out.print("Item not found in the inventory.");
        }
    }
}