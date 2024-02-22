import java.util.*;

public class Main {
    private static String currentRoom;
    private static HashMap<String, Room> rooms;
    private static boolean gamePlay;
    private static List<String> inventory;
    private static final Integer inventorySize = 3;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeGame(scanner);
        playGame(scanner);
    }

    private static void initializeGame(Scanner scanner) {
        currentRoom = Rooms.LABORATORY.getRoom().name();
        inventory = new ArrayList<>(inventorySize);
        gamePlay = true;
        rooms = new HashMap<>();
        rooms.put(Rooms.LAUNCH_PAD.getRoom().name(), Rooms.LAUNCH_PAD.getRoom());
        rooms.put(Rooms.WHITE_ROOM.getRoom().name(), Rooms.WHITE_ROOM.getRoom());
        rooms.put(Rooms.SERVER_ROOM.getRoom().name(), Rooms.SERVER_ROOM.getRoom());
        rooms.put(Rooms.LABORATORY.getRoom().name(), Rooms.LABORATORY.getRoom());
        rooms.put(Rooms.TECHNICAL_FACILITY.getRoom().name(), Rooms.TECHNICAL_FACILITY.getRoom());
        rooms.put(Rooms.CONTROL_CENTER.getRoom().name(), Rooms.CONTROL_CENTER.getRoom());
        rooms.put(Rooms.STORAGE_FACILITY.getRoom().name(), Rooms.STORAGE_FACILITY.getRoom());


        System.out.println(Strings.WELCOME_MESSAGE);
        System.out.println(Strings.ASCII_ART);
        System.out.println(Strings.INTRO_MESSAGE);
        System.out.println(Strings.HELP_MESAGE);
        scanner.nextLine();

        clearConsole();
    }

    private static void playGame(Scanner scanner) {

        while (gamePlay) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            if (command.equals(Commands.exit.toString())) {
                System.out.println("Exiting the game. Goodbye!");
                break;
            }

            handleCommand(command);
        }

        endGame();
        scanner.close();
    }

    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Failed to clear the console: " + e.getMessage());
        }
    }


    private static void handleCommand(String command) {
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
                    System.out.println(Strings.HELP_MESAGE);
                    break;

                case goTo:
                    if (parts.length > 1) {
                        String roomName = parts[1];
                        goToRoom(roomName);
                    } else {
                        System.out.println(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case whereAmI:
                    System.out.println(rooms.get(currentRoom).description());
                    break;

                case map:
                    System.out.println(Strings.ASCII_MAP);
                    break;

                case search:
                    System.out.println(currentRoom + ": " + rooms.get(currentRoom).items());
                    break;

                case pick:
                    if (parts.length > 1) {
                        String item = parts[1];
                        pickItem(item);
                    } else {
                        System.out.println(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;

                case inventory:
                    System.out.println(inventory);
                    break;

                case startRocket:
                    // TODO("end game")
                    break;

                default:
                    System.out.println(Strings.ERROR_COMMAND_MESSAGE);
            }
        } else {
            System.out.println(Strings.ERROR_COMMAND_MESSAGE);
        }
    }

    private static void goToRoom(String roomName) {
        if (rooms.containsKey(roomName)) {
            if (!roomName.equals(currentRoom)) {
                currentRoom = roomName;
                System.out.println("You have entered the " + roomName + ".");
            } else {
                System.out.println("You are already in " + roomName + ".");
            }
        } else {
            System.out.println("Invalid room name. Try again.");
        }
    }

    private static void pickItem(String item) {
        Room currentRoom = rooms.get(Main.currentRoom);
        if (currentRoom.items().contains(item)) {
            if (inventory.size() < inventorySize) {
                inventory.add(item);
                currentRoom.removeItem(item);
                System.out.println("Picked up " + item + ".");
            } else {
                System.out.println("Inventory is full. Drop an item to pick up " + item + ".");
            }
        } else {
            System.out.println("Item not found in the room.");
        }
    }

    private static void endGame() {
        clearConsole();
        System.out.println(Strings.END_GAME_MESSAGE);
    }
}