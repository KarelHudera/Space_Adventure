import java.util.List;

public enum Rooms {
    LAUNCH_PAD("launch_pad", "You are at the launch pad.", List.of()),
    WHITE_ROOM("white_room", "You are in a white room.", List.of()),
    SERVER_ROOM("server_room", "You are in server room.", List.of("rocket_computer")),
    LABORATORY("laboratory", "You are in a laboratory.", List.of()),
    TECHNICAL_FACILITY("technical_facility", "You are in a technical facility", List.of("canister_with_fuel")),
    CONTROL_CENTER("control_center", "You are at the control center.", List.of("entry_card")),
    STORAGE_FACILITY("storage_facility", "You are in a storage facility", List.of());

    private final Room room;

    Rooms(String name, String description, List<String> items) {
        this.room = new Room(name, description, items);
    }

    public Room getRoom() {
        return room;
    }
}