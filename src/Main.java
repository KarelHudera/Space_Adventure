import java.util.*;

public class Main {
    private static String currentRoom;
    private static HashMap<String, Room> rooms;
    private static boolean gamePlay;
    private static final Integer inventorySize = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeGame(scanner);
        playGame(scanner);
    }

    private static void initializeGame(Scanner scanner) {
        gamePlay = true;

        currentRoom = Rooms.LABORATORY.name();
        List<String> initialInventory = new ArrayList<>();
        Player.setInventory(initialInventory);
        Player.setInventorySize(inventorySize);

        rooms = new HashMap<>();
        rooms.put(Rooms.LAUNCH_PAD.name(), Rooms.LAUNCH_PAD.getRoom());
        rooms.put(Rooms.WHITE_ROOM.name(), Rooms.WHITE_ROOM.getRoom());
        rooms.put(Rooms.SERVER_ROOM.name(), Rooms.SERVER_ROOM.getRoom());
        rooms.put(Rooms.LABORATORY.name(), Rooms.LABORATORY.getRoom());
        rooms.put(Rooms.TECHNICAL_FACILITY.name(), Rooms.TECHNICAL_FACILITY.getRoom());
        rooms.put(Rooms.CONTROL_CENTER.name(), Rooms.CONTROL_CENTER.getRoom());
        rooms.put(Rooms.STORAGE_FACILITY.name(), Rooms.STORAGE_FACILITY.getRoom());


        System.out.print(Strings.WELCOME_MESSAGE);
        System.out.print(Strings.ASCII_ART);
        System.out.print(Strings.INTRO_MESSAGE);
        System.out.print(Strings.HELP_MESAGE);
        scanner.nextLine();

        clearConsole();
    }

    private static void playGame(Scanner scanner) {

        while (gamePlay) {
            System.out.println("");
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            if (command.equals(Commands.exit.toString())) {
                System.out.print("Exiting the game. Goodbye!");
                break;
            }

            Logic.handleCommand(command);
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
            System.out.print("Failed to clear the console: " + e.getMessage());
        }
    }

    private static void endGame() {
        clearConsole();
        System.out.print(Strings.END_GAME_MESSAGE);
    }

    public static String getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(String currentRoom) {
        Main.currentRoom = currentRoom;
    }

    public static HashMap<String, Room> getRooms() {
        return rooms;
    }

    public static void setRooms(HashMap<String, Room> rooms) {
        Main.rooms = rooms;
    }

    public static void setGamePlay(boolean gamePlay) {
        Main.gamePlay = gamePlay;
    }

    public static boolean isGamePlay() {
        return gamePlay;
    }
}