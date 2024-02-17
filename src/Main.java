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
        currentRoom = "room1";
        gameFinished = false;
        rooms = new HashMap<>();
        rooms.put("room1", new Room("lmoa ngmi"));
        rooms.put("room2", new Room("paris people"));

        // =====
        System.out.println("Welcome to the SpaceAdventure!");

        System.out.println(Strings.ASCII_ART);

        System.out.println("Type 'goTo ROOMNAME' to move to a different room.");
        System.out.println("Type 'whereAmI' to check your current location.");
        System.out.println("Type 'exit' to end the game.");
        System.out.print("Press Enter to start the adventure...");
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

            processCommand(command);
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


    private static void processCommand(String command) {
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
                case goTo:
                    if (parts.length > 1) {
                        String roomName = parts[1];
                        goToRoom(roomName);
                    } else {
                        System.out.println(Strings.ERROR_COMMAND_MESSAGE);
                    }
                    break;
                case whereAmI:
                    whereAmI();
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
            currentRoom = roomName;
            System.out.println("You have entered the " + roomName + ".");
        } else {
            System.out.println("Invalid room name. Try again.");
        }
    }

    private static void whereAmI() {
        System.out.println(rooms.get(currentRoom).getDescription());
        //gameFinished = true;
    }

    private static void endGame() {
        System.out.println("Thank you for playing! Game Over.");
    }
}