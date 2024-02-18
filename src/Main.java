import java.util.*;

public class Main {
    private static String currentRoom;
    private static HashMap<String, Room> rooms;
    private static boolean gameFinished;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeGame(scanner);
        playGame(scanner);
    }

    private static void initializeGame(Scanner scanner) {
        currentRoom = Rooms.LABORATORY.getRoom().getName();
        gameFinished = false;
        rooms = new HashMap<>();
        rooms.put(Rooms.LAUNCH_PAD.getRoom().getName(), Rooms.LAUNCH_PAD.getRoom());
        rooms.put(Rooms.WHITE_ROOM.getRoom().getName(), Rooms.WHITE_ROOM.getRoom());
        rooms.put(Rooms.SERVER_ROOM.getRoom().getName(), Rooms.SERVER_ROOM.getRoom());
        rooms.put(Rooms.LABORATORY.getRoom().getName(), Rooms.LABORATORY.getRoom());
        rooms.put(Rooms.TECHNICAL_FACILITY.getRoom().getName(), Rooms.TECHNICAL_FACILITY.getRoom());
        rooms.put(Rooms.CONTROL_CENTER.getRoom().getName(), Rooms.CONTROL_CENTER.getRoom());
        rooms.put(Rooms.STORAGE_FACILITY.getRoom().getName(), Rooms.STORAGE_FACILITY.getRoom());

        System.out.println(Strings.WELCOME_MESSAGE);
        System.out.println(Strings.ASCII_ART);
        System.out.println(Strings.HELP_MESAGE);
        scanner.nextLine();

        clearConsole();
    }

    private static void playGame(Scanner scanner) {

        while (!gameFinished) {
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
                    System.out.println(rooms.get(currentRoom).getDescription());
                    break;

                case showMap:
                    System.out.println(Strings.ASCII_MAP);
                    break;

                case search:
                    System.out.println(currentRoom + ": " + rooms.get(currentRoom).getItems());
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

    private static void endGame() {
        clearConsole();
        System.out.println(Strings.END_GAME_MESSAGE);
    }
}