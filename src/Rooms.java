import java.util.List;

public enum Rooms {
    LAUNCH_PAD("launch_pad",
            "You are at the launch pad. The rocket stands tall before you, awaiting final preparations. The sky is clear and the air buzzes with anticipation.",
            List.of("launch_key", "toolbox")),

    WHITE_ROOM("white_room",
            "You are in a pristine white room. The walls, floors, and ceiling are immaculately clean, creating a sterile environment. The room feels eerily quiet.",
            List.of("white_suit", "surgical_gloves")),

    SERVER_ROOM("server_room",
            "You are in the server room. The hum of countless servers fills the air, their lights blinking in a rhythmic pattern. Cables and hardware are meticulously arranged.",
            List.of("rocket_computer", "server_manual")),

    LABORATORY("laboratory",
            "You are in the laboratory. The scent of chemicals lingers in the air. Tables are cluttered with scientific instruments, glassware, and ongoing experiments.",
            List.of("test_tube", "beaker", "chemical_vial")),

    TECHNICAL_FACILITY("technical_facility",
            "You are in the technical facility. Tools and machinery line the walls, and various mechanical components are scattered around. The facility hums with energy.",
            List.of("canister_with_fuel", "wrench", "screwdriver")),

    CONTROL_CENTER("control_center",
            "You are at the control center. Multiple monitors display various data and metrics. The room is the nerve center of operations, with a commanding view of the launch pad.",
            List.of("entry_card", "control_panel_manual")),

    STORAGE_FACILITY("storage_facility",
            "You are in the storage facility. Shelves stocked with supplies and equipment tower over you. The room is filled with the scent of stored materials and the occasional sound of shifting items.",
            List.of("spare_parts", "emergency_kit"));

    private final Room room;

    Rooms(String name, String description, List<String> items) {
        this.room = new Room(name.toLowerCase(), description, items);
    }

    public Room getRoom() {
        return room;
    }
}